package swe3.demo.show;

import swe3.demo.*;
import swe3.demo.test.*;



/** This implementation shows how to create framework objects from native SQL. */
public final class SQLToFrameworkObjects 
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
        
        for(Teacher i: World.fromSQL(Teacher.class, "SELECT * FROM TEACHERS WHERE ID != 'T1'"))
        {
            System.out.println(i.getID() + ": [" + i.getName() + "]");
        }
    }
}
