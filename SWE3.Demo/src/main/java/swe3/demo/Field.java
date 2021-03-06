package swe3.demo;

import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;



/** This class holds field metadata. */
public class Field 
{
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // private members                                                                                                  //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Entity. */
    private Entity _entity;
    
    /** Field name. */
    private String _name;
    
    /** Get method. */
    private Method _get;
    
    /** Set method. */
    private Method _set;
    
    /** Column type. */
    private Class _columnType;
    
    /** Column name. */
    private String _columnName;
    
    /** Primary key flag. */
    private boolean _isPk = false;
    
    /** Foreign key flag. */
    private boolean _isFk = false;
    
    /** Field type. */
    private  Class _fieldType;
    
    /** Assignment table. */
    private String _assignmentTable = null;
    
    /** Remote column name. */
    private String _remoteColumnName = null;
    
    /** External flag. */
    private boolean _isExternal = false;
    
    /** m:n flag. */
    private boolean _isManyToMany = false;
        
    /** Nullable flag. */
    private boolean _isNullable = false;
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // constructors                                                                                                     //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Creates a new instance of this class.
     * @param entity Parent entity.
     * @param name Field name. */
    public Field(Entity entity, String name)
    {
        _entity = entity;
        _name = name;
    }
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // public methods                                                                                                   //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Gets the parent entity.
     * @return Entity. */
    public Entity getEntity()
    {
        return _entity;
    }
    
    
    /** Gets the field name.
     * @return Field name. */
    public String getName()
    {
        return _name;
    }
    
    
    /** Gets the get method.
     * @return Method. */
    public Method getGetMethod()
    {
        return _get;
    }
    
    /** Sets the set method.
     * @param value Method. */
    public void setGetMethod(Method value)
    {
        _get = value;
        _get.setAccessible(true);
    }
    
    
    /** Gets the set method.
     * @return Method. */
    public Method getSetMethod()
    {
        return _set;
    }
    
    /** Sets the set method.
     * @param value Method. */
    public void setSetMethod(Method value)
    {
        _set = value;
        _set.setAccessible(true);
    }
    
    
    /** Gets the column type.
     * @return Type. */
    public Class getColumnType()
    {
        return _columnType;
    }
    
    /** Sets the column type.
     * @param value Type. */
    public void setColumnType(Class value)
    {
        _columnType = value;
    }
    
    
    /** Gets the column name.
     * @return Column name. */
    public String getColumnName()
    {
        return _columnName;
    }
    
    /** Sets the column name.
     * @param value Column name. */
    public void setColumnName(String value)
    {
        _columnName = value;
    }
    
    
    /** Gets if the field is a primary key.
     * @return Returns TRUE if the field is a primary key, otherwise returns FALSE.. */
    public boolean isPrimaryKey()
    {
        return _isPk;
    }
    
    /** Sets if the field is a primary key.
     * @param value Primary key flag. */
    public void setPrimaryKey(boolean value)
    {
        _isPk = value;
    }
    
    
    /** Gets if the field is a foreign key.
     * @return Returns TRUE if the field is a foreign key, otherwise returns FALSE.. */
    public boolean isForeignKey()
    {
        return _isFk;
    }
    
    /** Sets if the field is a foreign key.
     * @param value Foreign key flag. */
    public void setForeignKey(boolean value)
    {
        _isFk = value;
    }
    
    
    /** Gets the column type.
     * @return Type. */
    public Class getFieldType()
    {
        return _fieldType;
    }
    
    /** Sets the column type.
     * @param value Type. */
    public void setFieldType(Class value)
    {
        _fieldType = value;
    }
    
    
    /** Gets the assignment table name.
     * @return Table name. */
    public String getAssignmentTable()
    {
        return _assignmentTable;
    }
    
    /** Sets the assignment table name.
     * @param value Table name. */
    public void setAssignmentTable(String value)
    {
        _assignmentTable = value;
    }
    
    
    /** Gets the remote column name.
     * @return Column name. */
    public String getRemoteColumnName()
    {
        return _remoteColumnName;
    }
    
    /** Sets the remote column name.
     * @param value Column name. */
    public void setRemoteColumnName(String value)
    {
        _remoteColumnName = value;
    }
    
    
    /** Gets if the field is not part of the table.
     * @return Returns TRUE if the field is external, otherwise returns FALSE.. */
    public boolean isExternal()
    {
        return _isExternal;
    }
    
    /** Sets if the field is not part of the table.
     * @param value External flag. */
    public void setExternal(boolean value)
    {
        _isExternal = value;
    }
    
    
    /** Gets if the field denotes a m:n relationship.
     * @return Returns TRUE if the field is a primary key, otherwise returns FALSE.. */
    public boolean isManyToMany()
    {
        return _isManyToMany;
    }
    
    /** Sets if the field denotes a m:n relationship.
     * @param value m_n flag. */
    public void setManyToMany(boolean value)
    {
        _isManyToMany = value;
    }
    
    
    /** Gets if the field is nullable.
     * @return Returns TRUE if the field is nullable, otherwise returns FALSE. */
    public boolean isNullable()
    {
        return _isNullable;
    }
    
    
    /** Sets if the field is nullable.
     * @param value Nullable flag. */
    public void setNullable(boolean value)
    {
        _isNullable = value;
    }
    
    
    /** Returns a database column type equivalent for a field type value.
     * @param obj Object.
     * @return Database type representation of the value. */
    public Object toColumnType(Object obj)
    {
        if(_isFk)
        {
            Field fk = World.__getEntity(_fieldType).getPrimaryKeys()[0];
            return fk.toColumnType(fk.getValue(obj));
        }
        
        if(_columnType.equals(Calendar.class))
        {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return f.format(((Calendar) obj).getTime());
        }
        
        if(_fieldType.equals(_columnType)) { return obj; }
        
        if(obj.getClass().equals(boolean.class))
        {
            if(_columnType.equals(int.class))   { return         (((boolean) obj) ? 1 : 0); }
            if(_columnType.equals(short.class)) { return (short) (((boolean) obj) ? 1 : 0); }
            if(_columnType.equals(long.class))  { return (long)  (((boolean) obj) ? 1 : 0); }
        }
        
        return obj;
    }
    
    
    /** Returns a field type equivalent for a database column type value.
     * @param obj Value.
     * @return Field type representation of the value. */
    public Object toFieldType(Object obj)
    {
        if(_isFk)
        { 
            if(_isExternal) return World.__getEntity(_columnType).getPrimaryKeys()[0].toColumnType(obj); 
            return _entity.getPrimaryKeys()[0].toColumnType(obj);
        }
        
        if(_fieldType.equals(boolean.class))
        {
            if(obj.getClass().equals(int.class))   { return (((int) obj)   != 0); }
            if(obj.getClass().equals(short.class)) { return (((short) obj) != 0); }
            if(obj.getClass().equals(long.class))  { return (((long) obj)  != 0); }
        }
        
        if(_fieldType.equals(short.class)) { return (short) obj; }
        if(_fieldType.equals(int.class))   { return (int)   obj; }
        if(_fieldType.equals(long.class))  { return (long)  obj; }
        
        if(_fieldType.isEnum())
        {
            if(obj instanceof String) { return Enum.valueOf(_fieldType, (String) obj); }
            return _fieldType.getEnumConstants()[(int) obj];
        }
        
        if(_fieldType.equals(Calendar.class)) 
        {
            Calendar rval = Calendar.getInstance();
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try 
            {
                rval.setTime(f.parse(obj.toString()));
                return rval;
            }
            catch (Exception ex) {}
        }
        
        return obj;
    }
    
    
    /** Gets the field value.
     * @param obj Object.
     * @return Value. */
    public Object getValue(Object obj)
    {
        try 
        {
            return _get.invoke(obj);
        } 
        catch (Exception ex) { return null; }
    }
    
    
    /** Sets the field value.
     * @param obj Object.
     * @param value Value. */
    public void setValue(Object obj, Object value) throws Exception
    {
        setValue(obj, value, null);
    }
    
    
    /** Sets the field value.
     * @param obj Object.
     * @param value Value.
     * @param objects Cached objects. */
    public void setValue(Object obj, Object value, Collection<Object> objects) throws Exception
    {
        if(_isFk)
        {
            if(_isExternal)
            {
                Entity rent = World.__getEntity(_columnType);
                String sql = rent.getSQL() + " WHERE " + _columnName + " = ?";
                Object[] parameters = new Object[] { _entity.getPrimaryKeys()[0].toFieldType(value) };
                Object rval;
                
                if(_isManyToMany)
                {
                    sql = rent.getSQL("T.") + " T WHERE EXISTS (SELECT * FROM " + _assignmentTable + " X " +
                                              "WHERE X." + _remoteColumnName + " = T." + rent.getPrimaryKeys()[0].getColumnName() + " AND " +
                                              "X." + _columnName + " = ?)";
                }
                
                if(LazyList.class.isAssignableFrom(_fieldType))
                {
                    rval = _fieldType.getDeclaredConstructor(_columnType.getClass(), String.class, Object[].class).newInstance(_columnType, sql, parameters);
                }
                else
                {
                    rval = _fieldType.getDeclaredConstructor().newInstance();
                    World._fillList(_columnType, (Collection) rval, sql, parameters, objects);
                }
                
                _set.invoke(obj, rval);
            }
            else
            {
                if(Lazy.class.isAssignableFrom(_fieldType))
                {
                    _set.invoke(obj, _fieldType.getDeclaredConstructor(_columnType.getClass(), Object[].class).newInstance(_columnType, new Object[] { value }));
                }
                else
                {
                    if(!value.equals(_fieldType)) { value = World._createObject(_fieldType, new Object[] { World.__getEntity(_fieldType).getPrimaryKeys()[0].toFieldType(value) }, objects); }
                    _set.invoke(obj, value);
                }
            }
        }
        else 
        {
            if(_columnType.equals(Calendar.class)) 
            {
                if(value.getClass().equals(String.class))
                {
                    Calendar rval = Calendar.getInstance();
                    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if(((String) value).length() == 10) { value = (((String) value) + " 12:00:00"); }
                    
                    try 
                    {
                        rval.setTime(f.parse(value.toString()));
                        value = rval;
                    }
                    catch (Exception ex) {}
                }
            }
            
            _set.invoke(obj, value); 
        }
    }
    
    
    /** Saves the references.
     * @param obj Object. */
    public void saveReferences(Object obj) throws Exception
    {
        if(!_isExternal) return;
        
        Entity innerEntity = World.__getEntity(_columnType);
        Object myPk = _entity.getPrimaryKeys()[0].toColumnType(_entity.getPrimaryKeys()[0].getValue(obj));
        
        if(_isManyToMany)
        {
            PreparedStatement cmd = World.getConnection().prepareStatement("DELETE FROM " + _assignmentTable + " WHERE " + _columnName + " = ?");
            cmd.setObject(1, myPk);
            cmd.execute();
            cmd.close();
            
            for(Object i: (Iterable) getValue(obj))
            {
                cmd = World.getConnection().prepareStatement("INSERT INTO " + _assignmentTable + "(" + _columnName + ", " + _remoteColumnName + ") VALUES (?, ?)");
                cmd.setObject(1, myPk);
                cmd.setObject(2, innerEntity.getPrimaryKeys()[0].toColumnType(innerEntity.getPrimaryKeys()[0].getValue(i)));
                cmd.execute();
                cmd.close();
            }
        }
        else
        {
            Field remoteField = innerEntity.getFieldForColumn(_columnName);
            
            if(remoteField.isNullable())
            {
                PreparedStatement cmd = World.getConnection().prepareStatement("UPDATE " + innerEntity.getTableName() + " SET " + _columnName + " = NULL WHERE " + _columnName + " = ?");
                cmd.setObject(1, myPk);
                cmd.execute();
                cmd.close();
            }
            
            for(Object i: (Iterable) getValue(obj))
            {
                PreparedStatement cmd = World.getConnection().prepareStatement("UPDATE " + innerEntity.getTableName() + " SET " + _columnName + " = ? WHERE " + innerEntity.getPrimaryKeys()[0].getColumnName() + " = ?");
                cmd.setObject(1, myPk);
                cmd.setObject(2, innerEntity.getPrimaryKeys()[0].toColumnType(innerEntity.getPrimaryKeys()[0].getValue(i)));
                cmd.execute();
                cmd.close();
            }
        }
    }
}
