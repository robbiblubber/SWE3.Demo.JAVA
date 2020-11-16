package swe3.demo;

import java.lang.reflect.Method;



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
}
