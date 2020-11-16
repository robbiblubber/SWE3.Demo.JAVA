package swe3.demo.test;

import swe3.demo.annotations.*;



/** This class represents a class in the school model. */
@entity(tableName = "CLASSES")
public class SClass 
{
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // protected members                                                                                                //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** ID. */
    protected String _id;
    
    /** Name. */
    protected String _name;
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // public methods                                                                                                   //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Gets the person's ID.
     * @return ID. */
    @field @pk
    public String getID()
    {
        return _id;
    }    
    
    /** Sets the person's ID.
     * @param value ID. */
    @field @pk
    public void setID(String value)
    {
        _id = value;
    }
    
    
    /** Gets the person's name.
     * @return Name. */
    @field
    public String getName()
    {
        return _name;
    }
    
    /** Sets the person's name.
     * @param value Name. */
    @field
    public void setName(String value)
    {
        _name = value;
    }
}
