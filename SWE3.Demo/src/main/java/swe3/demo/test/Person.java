package swe3.demo.test;

import java.util.Calendar;
import swe3.demo.annotations.*;



/** This is a person implementation (from School example). */
public class Person 
{
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // protected members                                                                                                //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** ID. */
    protected String _id;
    
    /** Name. */
    protected String _name;
    
    /** First name. */
    protected String _firstName;
    
    /** Birth date. */
    protected Calendar _birthDate;
    
    /** Gender. */
    protected Gender _gender;
    
    
    
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
    
    
    /** Gets the person's first name.
     * @return First name. */
    @field
    public String getFirstName()
    {
        return _firstName;
    }    
    
    /** Sets the person's first name.
     * @param value First name. */
    @field
    public void setFirstName(String value)
    {
        _firstName = value;
    }
    
    
    /** Gets the person's birth date.
     * @return Birth date. */
    @field(columnName = "BDATE")
    public Calendar getBirthDate()
    {
        return _birthDate;
    }    
    
    /** Sets the person's birth date.
     * @param value Birth date. */
    @field
    public void setBirthDate(Calendar value)
    {
        _birthDate = value;
    }
    
    
    /** Gets the person's gender.
     * @return Gender. */
    @field
    public Gender getGender()
    {
        return _gender;
    }
    
    /** Sets the person's gender.
     * @param value Gender. */
    @field
    public void setGender(Gender value)
    {
        _gender = value;
    }
}
