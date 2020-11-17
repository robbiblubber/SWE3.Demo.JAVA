package swe3.demo.show;

import swe3.demo.*;
import swe3.demo.test.*;



/** This class shows how to create a single instance with an ID. */
public class CreateInstanceByPK 
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
        
        Teacher t = World.getObject(Teacher.class, "T0");
        System.out.println(t.getID() + " => " + t.getName());
    }
}
