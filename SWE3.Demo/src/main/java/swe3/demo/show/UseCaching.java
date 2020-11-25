package swe3.demo.show;

import swe3.demo.*;
import swe3.demo.test.*;



/** This class shows how to add and remove objects from lists. */
public class UseCaching
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
        
        World.disableCaching();
        Teacher t0 = World.getObject(Teacher.class, "T0"); 
        Teacher t1 = World.getObject(Teacher.class, "T0"); 
        Teacher t2 = World.getObject(Teacher.class, "T0"); 
        
        System.out.println("Caching disabled:");
        System.out.println("t0 => " + t0.getInstanceNumber());
        System.out.println("t1 => " + t1.getInstanceNumber());
        System.out.println("t2 => " + t2.getInstanceNumber());
        
        World.enableCaching();
        t0 = World.getObject(Teacher.class, "T0"); 
        t1 = World.getObject(Teacher.class, "T0"); 
        t2 = World.getObject(Teacher.class, "T0"); 
        
        System.out.println("Caching enabled:");
        System.out.println("t0 => " + t0.getInstanceNumber());
        System.out.println("t1 => " + t1.getInstanceNumber());
        System.out.println("t2 => " + t2.getInstanceNumber());
    }
}
