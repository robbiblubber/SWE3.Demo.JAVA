package swe3.demo.show;

import swe3.demo.*;
import swe3.demo.test.*;



/** This class shows how to create an instance of an object with a foreign key. */
public final class CreateObjectWithFK 
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
        
        SClass c = World.getObject(SClass.class, "C1");
        
        System.out.print(c.getID() + ": [" + c.getName() + "]");
        System.out.println(" (Teacher: " + c.getTeacher().getName() + ")");
    }
}
