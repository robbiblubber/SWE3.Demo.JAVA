package swe3.demo.show;

import swe3.demo.*;
import swe3.demo.test.*;



/** This class shows how to add and remove objects from lists. */
public class SaveObjectsWithLists 
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
        
        Teacher t = World.getObject(Teacher.class, "T0");                       // 1:n
        Course c;
        t.getCourses().add(c = World.getObject(Course.class, "X2"));
        
        try 
        {
            World.save(t);
        } 
        catch (Exception ex) {}
        
        c.setTeacher(World.getObject(Teacher.class, "T2"));
        
        c.getStudents().add(World.getObject(Student.class, "Z0"));              // m:n
        c.getStudents().add(World.getObject(Student.class, "Z1"));
        
        try 
        {
            World.save(c);
        } 
        catch (Exception ex) {}
        
        c.getStudents().clear();
        try 
        {
            World.save(c);
        } 
        catch (Exception ex) {}
    }
}
