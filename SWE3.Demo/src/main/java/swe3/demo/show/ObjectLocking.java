package swe3.demo.show;

import swe3.demo.*;
import swe3.demo.test.*;



/** This class shows locking of an object. */
public class ObjectLocking
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
        
        Teacher t = World.getObject(Teacher.class, "T2");
        
        try 
        {
            World.lock(t);
            t.setSalary(t.getSalary() + 18);
            World.save(t);
            World.releaseLock(t);
        }
        catch (Exception ex) {}
        
        
    }
}
