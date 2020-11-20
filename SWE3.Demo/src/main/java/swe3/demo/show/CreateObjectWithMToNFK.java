package swe3.demo.show;

import swe3.demo.*;
import swe3.demo.test.*;



/** This class shows how to create an instance with lists from m:n foreign keys. */
public class CreateObjectWithMToNFK 
{
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // public static methods                                                                                            //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Implements the demonstration. */
    public static void show()
    {
        try 
        {
            World.connect("jdbc:sqlite:test/test.sqlite");
        } 
        catch (Exception ex) {}
        
        Course c = World.getObject(Course.class, "X0");
        System.out.println(c.getID()+ ": [" + c.getName() + "]");
        for(Student i: c.getStudents())                                         // eager loading
        {
            System.out.println("    " + i.getName() + " (student)");
        }
        
        Student s = World.getObject(Student.class, "Z2");
        System.out.println(c.getID()+ ": [" + c.getName() + "]");
        for(Course i: s.getCourses())                                           // lazy loading
        {
            System.out.println("    " + i.getName() + " (course)");
        }
    }
}
