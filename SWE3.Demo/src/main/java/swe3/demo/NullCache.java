package swe3.demo;



/** This class implements an empty cache. */
public class NullCache extends Cache
{
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // constructors                                                                                                     //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /** Creates a new instance of this class. */
    protected NullCache()
    {}
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // public methods                                                                                                   //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /** Gets a cached object.
     * @param pk Primary key.
     * @return Returns the cached object or NULL if no object is cached for this key. */
    @Override
    public Object get(Object pk)
    {
        return null;
    }
    
    
    /** Sets a cached object.
     * @param pk Primary key.
     * @param value Object. */
    @Override
    public void set(Object pk, Object value)
    {}
    
    
    /** Gets if the cache contains an object.
     * @param pk Primary key.
     * @return Returns TRUE if the cache contains the object. */
    @Override
    public boolean contains(Object pk)
    {
        return false;
    }


    /** Deletes an object from the cache.
     * @param pk Primary key. */
    /// <param name="pk">Primary key.</param>
    @Override
    public void delete(Object pk)
    {}
}
