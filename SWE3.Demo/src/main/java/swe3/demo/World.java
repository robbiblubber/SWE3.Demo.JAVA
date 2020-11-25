package swe3.demo;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;



/** This class grants access to the demo framework. */
public final class World 
{
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // private static members                                                                                           //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Entities. */
    private static final HashMap<Class, Entity> _entities = new HashMap<>();
    
    /** Database connection. */
    private static Connection _connection;
    
    /** Caches. */
    private static HashMap<Class, Cache> _caches = new HashMap<>();
    
    /** Empty cache. */
    private static NullCache _nullCache = new NullCache();
    
    /** Caching enabled flag. */
    private static boolean _cachingEnabled = true;
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // protected static methods                                                                                         //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Gets the cache for a type.
     * @param t Type.
     * @return Cache. */
    protected static Cache _getCache(Class t)
    {
        if(!_cachingEnabled) { return _nullCache; }
        
        if(!_caches.containsKey(t))
        {
            _caches.put(t, new Cache());
        }
        return _caches.get(t);
    }
    
    
    /** Creates an object from a database result set.
     * @param t Type.
     * @param re Result set.
     * @param objects Cached objects.
     * @return Object. */
    protected static Object _createObject(Class t, ResultSet re, Collection<Object> objects)
    {
        Object rval = _getCachedObject(t, re, objects);
        
        if(rval == null)
        {
            if(objects == null) { objects = new ArrayList<>(); }
            try 
            {
                objects.add(rval = t.getDeclaredConstructor().newInstance());
                
                for(Field i: __getEntity(t).getPrimaryKeys())
                {
                    i.setValue(rval, i.toFieldType(re.getObject(i.getColumnName())));
                }
                
                for(Field i: __getEntity(t).getFields())
                {
                    if(!i.isPrimaryKey())
                    {
                        i.setValue(rval, i.toFieldType(re.getObject(i.isExternal() ? i.getEntity().getPrimaryKeys()[0].getColumnName() : i.getColumnName())));
                    }
                }
                
                _getCache(t).set(__getEntity(rval).getPrimaryKeys()[0].getValue(rval), rval);
            } 
            catch (Exception ex) { return null; }
        }
        return rval;
    }
    
    
    /** Creates an instance by its primary keys.
     * @param t Type.
     * @param pks Primary keys.
     * @return Object. */
    protected static Object _createObject(Class t, Object[] pks) throws  Exception
    {
        return _createObject(t, pks, null);
    }
    
    
    /** Creates an instance by its primary keys.
     * @param t Type.
     * @param pks Primary keys.
     * @param objects Cached objects.
     * @return Object. */
    protected static Object _createObject(Class t, Object[] pks, Collection<Object> objects) throws Exception
    {
        Entity ent = __getEntity(t);
        
        String query = ent.getSQL() + " WHERE ";
        for(int i = 0; i < ent.getPrimaryKeys().length; i++)
        {
            if(i > 0) { query += " AND "; }
            query += (ent.getPrimaryKeys()[i].getColumnName() + " = ?");
        }
        
        PreparedStatement cmd = getConnection().prepareStatement(query);
        for(int i = 0; i < pks.length; i++)
        {
            cmd.setObject(i + 1, pks[i]);
        }
        
        Object rval = null;
        ResultSet re = cmd.executeQuery();
        if(re.next())
        {
            rval = _createObject(t, re, objects);
        }
        
        re.close();
        cmd.close();
        
        if(rval == null) { throw new Exception("No data."); }
        return rval;
    }
    
    
    /** Searches the cached objects for an object and returns it.
     * @param t Type.
     * @param re Result set.
     * @param objects Cached objects.
     * @return Returns the cached object that matches the current reader or NULL if no such object has been found. */
    protected static Object _getCachedObject(Class t, ResultSet re, Collection<Object> objects)
    {
        if(objects != null)
        {
            for(Object i: objects)
            {
                if(!i.getClass().equals(t)) continue;

                boolean found = true;
                for(Field k: __getEntity(t).getPrimaryKeys())
                {
                    try 
                    {
                        if(!k.getValue(i).equals(k.toFieldType(re.getObject(k.getColumnName())))) { found = false; break; }
                    } 
                    catch(Exception ex) { found = false; break; }
                }
                if(found) { return i; }
            }
        }
        
        try
        {
            Field pk = __getEntity(t).getPrimaryKeys()[0];
            return _getCache(t).get(pk.toFieldType(re.getObject(pk.getColumnName())));
        }
        catch(Exception ex) {}
        
        return null;
    }
    
    
    /** Fills a list.
     * @param t Type.
     * @param list List.
     * @param re Result set. */
    protected static void _fillList(Class t, Collection list, ResultSet re)
    {
        _fillList(t, list, re, null);
    }
    
    
    /** Fills a list.
     * @param t Type.
     * @param list List.
     * @param re Result set.
     * @param objects Cached objects. */
    protected static void _fillList(Class t, Collection list, ResultSet re, Collection objects)
    {
        try 
        {
            while(re.next()) 
            {
                list.add(_createObject(t, re, objects));
            }
        }
        catch (Exception ex) {}
    }
    
    
    /** Fills a list.
     * @param t Type.
     * @param list List.
     * @param sql SQL query.
     * @param parameters Parameters. */
    protected static void _fillList(Class t, Collection list, String sql, Object[] parameters)
    {
        _fillList(t, list, sql, parameters, null);
    }
    
    
    /** Fills a list.
     * @param t Type.
     * @param list List.
     * @param sql SQL query.
     * @param parameters Parameters.
     * @param objects Cached objects. */
    protected static void _fillList(Class t, Collection list, String sql, Object[] parameters, Collection<Object> objects)
    {
        try 
        {
            PreparedStatement cmd = getConnection().prepareStatement(sql);
            for(int i = 0; i < parameters.length; i++)
            {
                cmd.setObject(i + 1, parameters[i]);
            }
            
            ResultSet re = cmd.executeQuery();
            _fillList(t, list, re, objects);
            
            re.close();
            cmd.close();
        }
        catch (Exception ex) {}
    }
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // public static methods                                                                                            //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Gets an entity descriptor for an object.
     * @param o Object.
     * @return Entity. */
    public static Entity __getEntity(Object o)
    {
        Class t = ((o instanceof Class) ? (Class) o : o.getClass());
        
        if(!_entities.containsKey(t)) 
        {
            _entities.put(t, new Entity(t));
        }
        
        return _entities.get(t);
    }
    
    
    /** Gets the database connection.
     * @return Connection. */
    public static Connection getConnection()
    {
        return _connection;
    }
    
    
    /** Sets the database connection.
     * @param connection Connection. */
    public static void setConnection(Connection connection)
    {
        _connection = connection;
    }
    
    
    /** Gets if caching is enabled.
     * @return Returns TRUE if caching is enabled, otherwise returns FALSE. */
    public static boolean isCachingEnabled()
    {
        return _cachingEnabled;
    }
    
    
    /** Sets the caching enabled flag.
     * @param value Value. */
    public static void setCachingEnabled(boolean value)
    {
        _cachingEnabled = value;
    }
    
    
    /** Enables caching. */
    public static void enableCaching()
    {
        setCachingEnabled(true);
    }
    
    
    /** Disables caching. */
    public static void disableCaching()
    {
        setCachingEnabled(false);
    }
    
    
    /** Connects to a database.
     * @param url Connection URL. */
    public static void connect(String url) throws SQLException
    {
        _connection = DriverManager.getConnection(url);
    }
    
    
    /** Creates an instance by its primary keys.
     * @param <T> Type.
     * @param t Type class.
     * @param pks Primary keys.
     * @return Object. */
    public static <T> T getObject(Class<T> t, Object... pks)
    {
        try 
        {
            return (T) _createObject(t, pks);
        }
        catch (Exception ex) { return null; }
    }
    
    
    /** Returns an array of instances for an SQL query.
     * @param <T> Type.
     * @param t Type.
     * @param sql SQL query.
     * @return Instances. */
    public static <T> T[] fromSQL(Class<T> t, String sql)
    {
        ArrayList<T> rval = new ArrayList<>();
        
        try 
        {
            PreparedStatement cmd = getConnection().prepareStatement(sql);

            ResultSet re = cmd.executeQuery();
            _fillList(t, rval, re);

            re.close();
            cmd.close();
        }
        catch (Exception ex) {}
        
        
        return rval.toArray((T[]) Array.newInstance(t, 0));
    }
    
    
    /** Saves an object.
     * @param obj Object. */
    public static void save(Object obj) throws Exception
    {
        Entity ent = __getEntity(obj);
        
        String xstmnt = ("INSERT INTO " + ent.getTableName() + " (");
        String update    = "ON CONFLICT (";
        String insert = "";
        ArrayList<Object> pam1 = new ArrayList<>();
        ArrayList<Object> pam2 = new ArrayList<>();
        
        for(int i = 0; i < ent.getPrimaryKeys().length; i++)
        {
            if(i > 0) { update += ", "; }
            update += ent.getPrimaryKeys()[i].getColumnName();
        }
        update += ") DO UPDATE SET ";
        
        boolean first = true;
        for(int i = 0; i < ent.getInternals().length; i++)
        {
            if(i > 0) { xstmnt += ", "; insert += ","; }
            xstmnt += ent.getInternals()[i].getColumnName();
            insert += "?";
            pam1.add(ent.getInternals()[i].toColumnType(ent.getInternals()[i].getValue(obj)));
            
            if(!ent.getInternals()[i].isPrimaryKey())
            {
                if(first) { first = false; } else { update += ", "; }
                update += (ent.getInternals()[i].getColumnName() + " = ?");
                pam2.add(ent.getInternals()[i].toColumnType(ent.getInternals()[i].getValue(obj)));
            }
        }
        xstmnt += (") VALUES (" + insert + ") " + update);        
        pam1.addAll(pam2);
        
        PreparedStatement cmd = getConnection().prepareStatement(xstmnt);
        int n = 1;
        for(Object i: pam1) { cmd.setObject(n++, i); }
        
        cmd.execute();
        cmd.close();
        
        for(Field i: ent.getExternals()) { i.saveReferences(obj); }
        
        _getCache(obj.getClass()).set(ent.getPrimaryKeys()[0].getValue(obj), obj);
    }
    
    
    /** Deletes an object.
     * @param obj Object. */
    public static void delete(Object obj) throws Exception
    {
        Entity ent = __getEntity(obj);
        
        String xstmnt = ("DELETE FROM " + ent.getTableName() + " WHERE ");
        ArrayList<Object> params = new ArrayList<>();
        
        for(int i = 0; i < ent.getPrimaryKeys().length; i++)
        {
            if(i > 0) { xstmnt += " AND "; }
            xstmnt += (ent.getPrimaryKeys()[i].getColumnName() + " = ?");
            params.add(ent.getPrimaryKeys()[i].toColumnType(ent.getPrimaryKeys()[i].getValue(obj)));
        }
        
        PreparedStatement cmd = getConnection().prepareStatement(xstmnt);
        int n = 1;
        for(Object i: params) { cmd.setObject(n++, i); }
        
        cmd.execute();
        cmd.close();
        
        _getCache(obj.getClass()).delete(ent.getPrimaryKeys()[0].getValue(obj));
    }
}
