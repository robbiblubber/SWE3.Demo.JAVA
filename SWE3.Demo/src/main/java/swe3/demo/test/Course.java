package swe3.demo.test;

import swe3.demo.annotations.*;



/** This class represents a course in the school model. */
@entity(tableName = "COURSES")
public class Course 
{
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // protected members                                                                                                //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** ID. */
    protected String _id;
    
    /** Name. */
    protected String _name;
    
    /** Active flag. */
    protected boolean _active;
    
    /** Teacher. */
    protected Teacher _teacher;
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // public methods                                                                                                   //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Gets the course ID.
     * @return ID. */
    @field @pk
    public String getID()
    {
        return _id;
    }    
    
    /** Sets the course ID.
     * @param value ID. */
    @field @pk
    public void setID(String value)
    {
        _id = value;
    }
    
    
    /** Gets the course name.
     * @return Name. */
    @field
    public String getName()
    {
        return _name;
    }
    
    /** Sets the course name.
     * @param value Name. */
    @field
    public void setName(String value)
    {
        _name = value;
    }
    
    
    /** Gets the course teacher.
     * @return Teacher. */
    @fk(columnName = "KTEACHER")
    public Teacher getTeacher()
    {
        return _teacher;
    }
    
    
    /** Sets the course teacher.
     * @param value Teacher. */
    @fk
    public void setTeacher(Teacher value)
    {
        _teacher = value;
    }
}
