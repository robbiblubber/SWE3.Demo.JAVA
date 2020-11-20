package swe3.demo.test;

import java.util.*;
import swe3.demo.*;
import swe3.demo.annotations.*;



/** This is a teacher implementation (from School example). */
@entity(tableName = "TEACHERS")
public class Teacher extends Person
{
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // protected members                                                                                                //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Salary. */
    protected int _salary;
    
    /** Hire date. */
    protected Calendar _hireDate;
    
    /** Classes. */
    protected ArrayList<SClass> _classes;
    
    /** Courses. */
    protected LazyList<Course> _courses;
    
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // public methods                                                                                                   //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Gets the teacher's salary.
     * @return Salary. */
    @field
    public int getSalary()
    {
        return _salary;
    }
    
    /** Sets the teacher's salary.
     * @param value Salary. */
    @field
    public void setSalary(int value)
    {
        _salary = value;
    }
    
    
    /** Gets the teacher's hire date.
     * @return Hire date. */
    @field(columnName = "HDATE")
    public Calendar getHireDate()
    {
        return _hireDate;
    }    
    
    /** Sets the teacher's hire date.
     * @param value Hire date. */
    @field
    public void setHireDate(Calendar value)
    {
        _hireDate = value;
    }
    
    
    /** Gets the teacher's classes.
     * @return Classes. */
    @fk(columnName = "KTEACHER", columnType = SClass.class)
    public ArrayList<SClass> getClasses()
    {
        return _classes;
    }
    
    
    /** Sets the teacher's classes.
     * @param value Classes. */
    @fk(fieldName = "Classes")
    private void _setClasses(ArrayList<SClass> value)
    {
        _classes = value;
    }
    
    
    /** Gets the teacher's courses.
     * @return Classes. */
    @fk(columnName = "KTEACHER", columnType = Course.class)
    public LazyList<Course> getCourses()
    {
        return _courses;
    }
    
    
    /** Sets the teacher's classes.
     * @param value Classes. */
    @fk(fieldName = "Courses")
    private void _setCourses(LazyList<Course> value)
    {
        _courses = value;
    }
}
