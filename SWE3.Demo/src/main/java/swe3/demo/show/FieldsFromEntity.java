package swe3.demo.show;

import swe3.demo.*;
import swe3.demo.test.Teacher;



/** This implementation shows how to read data from an entity. */
public final class FieldsFromEntity 
{
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // public static methods                                                                                            //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Implements the demonstration. */
    public static void show()
    {
        Entity ent = World.__getEntity(Teacher.class);
        
        for(Field i: ent.getFields())
        {
            System.out.print(i.getName() + " => " + ent.getTableName() + "." + i.getColumnName());
            if(i.isPrimaryKey()) { System.out.print(" (pk)"); }
            if(i.isForeignKey()) { System.out.print(" (fk)"); }
            System.out.println();
        }
    }
}
