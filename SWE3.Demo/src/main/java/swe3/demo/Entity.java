package swe3.demo;

import java.lang.reflect.*;
import java.util.ArrayList;
import swe3.demo.annotations.*;



/** This class holds entity metadata. */
public final class Entity 
{
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // private members                                                                                                  //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Primary keys. */
    private Field[] _primaryKeys;
    
    /** Entity type. */
    private Class _entityType;
    
    /** Table name. */
    private String _tableName;
    
    /** Fields. */
    private Field[] _fields;
    
    /** External fields. */
    private Field[] _externals;
    
    /** Internal fields. */
    private Field[] _internals;
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // constructors                                                                                                     //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Creates a new instance of this class.
     * @param t Type. */
    public Entity(Class t)
    {
        entity tattr = (entity) t.getAnnotation(entity.class);
        if((tattr == null) || (tattr.tableName() == null) || (tattr.tableName().equals("")))
        {
            _tableName = t.getSimpleName().toUpperCase();
        }
        else { _tableName = tattr.tableName(); }
        
        _entityType = t;
        ArrayList<Field> fields = new ArrayList<>();
        ArrayList<Field> pks = new ArrayList<>();
        
        for(Method i: _getMethods(t))
        {
            if(i.getName().equals("getClass")) continue;
            if(i.getAnnotation(ignore.class) != null) continue;
            
            Field f = _getField(i, fields);
            if(f == null) continue;
            
            __FieldAnnotation fattr = new __FieldAnnotation(i);
            
            if((fattr.getColumnName() != null) && (!fattr.getColumnName().equals("")))
            {
                f.setColumnName(fattr.getColumnName());
            }
            if((fattr.getColumnType() != null) && (!fattr.getColumnType().equals(Void.class)))
            {
                f.setColumnType(fattr.getColumnType());
            }
            
            if(i.getParameters().length == 0)
            {
                f.setGetMethod(i);
                f.setFieldType(i.getReturnType());
            }
            else
            {
                f.setSetMethod(i);
                f.setFieldType(i.getParameters()[0].getType());
            }
            
            if(f.getColumnName() == null) { f.setColumnName(f.getName().toUpperCase()); }
            if((f.getColumnType() == null) || (f.getColumnType().equals(Void.class))) { f.setColumnType(f.getFieldType()); }
            
            if(fattr.isPrimaryKey())
            {
                f.setPrimaryKey(true);
                if(!pks.contains(f)) { pks.add(f); }
            }
            
            if(fattr.isForeignKey())
            {
                f.setForeignKey(true);
                
                f.setExternal(Iterable.class.isAssignableFrom(f.getFieldType()));
                f.setAssignmentTable(fattr.getAssignmentTable());
                f.setRemoteColumnName(fattr.getRemoteColumnName());
            }
        }        
        _fields = fields.toArray(new Field[0]);
        
        ArrayList<Field> ints = new ArrayList<>();
        ArrayList<Field> exts = new ArrayList<>();
        for(Field i: fields)
        {
            if(i.isExternal()) { exts.add(i); } else { ints.add(i); }
        }
        _internals = ints.toArray(new Field[0]);
        _externals = exts.toArray(new Field[0]);
        
        _primaryKeys = pks.toArray(new Field[0]);
    }
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // private methods                                                                                                  //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Gets all methods from a class.
     * @param t Type.
     * @return List of methods. */
    private ArrayList<Method> _getMethods(Class t)
    {
        ArrayList<Method> rval = new ArrayList<>();
        
        for(Method i: t.getMethods()) { if(!Modifier.isStatic(t.getModifiers())) rval.add(i); }
        for(Method i: t.getDeclaredMethods()) { if(!(Modifier.isStatic(i.getModifiers()) || Modifier.isPublic(i.getModifiers()))) rval.add(i); }
        
        return rval;
    }
    
    
    /** Gets a field for a method.
     * @param m Method.
     * @param fields Fields.
     * @return Field. */
    private Field _getField(Method m, ArrayList<Field> fields)
    {
        __FieldAnnotation fattr = new __FieldAnnotation(m);
        String name = null;
        
        if((fattr.isField()) && (fattr.getFieldName() != null) && (!fattr.getFieldName().equals("")))
        {
            name = fattr.getFieldName();
        }
        else if((m.getName().toLowerCase().startsWith("get")) || (m.getName().toLowerCase().startsWith("set")))
        {
            name = m.getName().substring(3);
        }
        else if(m.getName().toLowerCase().startsWith("is"))
        {
            name = m.getName().substring(2);
        }

        if(name == null) { return null; }
        
        for(Field i: fields)
        {
            if(i.getName().equals(name)) { return i; }
        }
        
        Field rval = new Field(this, name);
        fields.add(rval);
        
        return rval;
    }
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // public methods                                                                                                   //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Gets the primary keys.
     * @return Primary keys. */
    public Field[] getPrimaryKeys()
    {
        return _primaryKeys;
    }
    
    
    /** Gets the entity type.
     * @return Entity type. */
    public Class getEntityType()
    {
        return _entityType;
    }
    
    
    /** Gets the table name.
     * @return Table name. */
    public String getTableName()
    {
        return _tableName;
    }
    
    
    /** Gets the entity fields.
     * @return Fields. */
    public Field[] getFields()
    {
        return _fields;
    }
    
    
    
    /** Gets the external fields.
     * @return External fields. */
    public Field[] getExternals()
    {
        return _externals;
    }
    
    
    /** Gets the internal fields.
     * @return Internal fields. */
    public Field[] getInternals()
    {
        return _internals;
    }
    
    
    /** Gets the entity SQL.
     * @return SQL string. */
    public String getSQL()
    {
        return getSQL(null);
    }
    
    
    /** Gets the entity SQL.
     * @param prefix Prefix.
     * @return SQL string. */
    public String getSQL(String prefix)
    {
        if(prefix == null) { prefix = ""; }
        String rval = "SELECT ";
        for(int i = 0; i < _internals.length; i++)
        {
            if(i > 0) { rval += ", "; }
            rval += prefix.trim() + _internals[i].getColumnName();
        }
        rval += (" FROM " + _tableName);
        
        return rval;
    }
}
