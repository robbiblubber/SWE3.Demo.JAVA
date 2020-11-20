package swe3.demo.test;

import java.util.*;
import swe3.demo.*;
import swe3.demo.annotations.*;



/** This is a student implementation (from School example). */
@entity(tableName = "STUDENTS")
public class Student extends Person
{
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // protected members                                                                                                //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Salary. */
    protected int _grade;
    
    /** Hire date. */
    protected Calendar _hireDate;
    
    /** Courses. */
    protected LazyList<Course> _courses;
    
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // public methods                                                                                                   //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Gets the student's grade.
     * @return Grade. */
    @field
    public int getGrade()
    {
        return _grade;
    }    
    
    /** Sets the student's grade.
     * @param value Grade. */
    @field
    public void setGrade(int value)
    {
        _grade = value;
    }
    
    
    /** Gets the teacher's courses.
     * @return Classes. */
    @fk(assignmentTable = "STUDENT_COURSES", columnName = "KSTUDENT", remoteColumnName = "KCOURSE", columnType = Course.class)
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
