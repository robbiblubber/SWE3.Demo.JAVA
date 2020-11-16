package swe3.demo.test;

import java.util.ArrayList;
import java.util.Calendar;
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
}
