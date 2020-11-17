package swe3.demo;

import java.lang.reflect.Method;
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
    
    
    /** Returns a database column type equivalent for a field type value.
     * @param obj Object.
     * @return Database type representation of the value. */
    public Object toColumnType(Object obj)
    {
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
                
            }
            else
            {
                
            }
        }
        else { _set.invoke(obj, value); }
    }
}
