package swe3.demo.show;

import swe3.demo.*;
import swe3.demo.test.*;



/** This class shows how to insert and update an object. */
public class InsertNewObjectAndUpdate 
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
        
        Course c = new Course();
        c.setID("X100");
        c.setName("A New Hope");
        c.setTeacher(World.getObject(Teacher.class, "T0"));
        try 
        {
            World.save(c);
        } 
        catch (Exception e) {}
        
        c.setTeacher(World.getObject(Teacher.class, "T2"));
        try 
        {
            World.save(c);
        } 
        catch (Exception e) {}
        
        try 
        {
            World.delete(c);
        } 
        catch (Exception e) {}
    }
}
