package swe3.demo.test;

import swe3.demo.*;
import swe3.demo.annotations.*;



/** This class represents a class in the school model. */
@entity(tableName = "CLASSES")
public class SClass 
{
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // protected members                                                                                                //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** ID. */
    protected String _id;
    
    /** Name. */
    protected String _name;
    
    /** Teacher. */
    protected Lazy<Teacher> _backTeacher;
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // public methods                                                                                                   //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Gets the class ID.
     * @return ID. */
    @field @pk
    public String getID()
    {
        return _id;
    }    
    
    /** Sets the class ID.
     * @param value ID. */
    @field @pk
    public void setID(String value)
    {
        _id = value;
    }
    
    
    /** Gets the class name.
     * @return Name. */
    @field
    public String getName()
    {
        return _name;
    }
    
    /** Sets the class name.
     * @param value Name. */
    @field
    public void setName(String value)
    {
        _name = value;
    }
    
    
    /** Gets the class teacher.
     * @return Teacher. */
    @fk(columnName = "KTEACHER", fieldName = "_backTeacher", columnType = Teacher.class)
    private Lazy<Teacher> _getBackTeacher()
    {
        return _backTeacher;
    }
    
    
    /** Sets the class teacher.
     * @param value Teacher. */
    @fk(fieldName = "_backTeacher")
    private void _setBackTeacher(Lazy<Teacher> value)
    {
        _backTeacher = value;
    }
    
    
    /** Gets the class teacher.
     * @return Teacher. */
    @ignore
    public Teacher getTeacher()
    {
        return _backTeacher.getValue();
    }
    
    
    /** Sets the class teacher.
     * @param value Teacher. */
    @ignore
    public void setTeacher(Teacher value)
    {
        _backTeacher.setValue(value);
    }
}
