package swe3.demo;

import java.util.HashMap;



/** This class provides a cache. */
public class Cache 
{
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // protected memvers                                                                                                //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /** Actual cache. */
    protected HashMap<Object, Object> _Cache;




    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // constructors                                                                                                     //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /** Creates a new instance of this class. */
    protected Cache()
    {
        _Cache = new HashMap<>();
    }
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // public methods                                                                                                   //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /** Gets a cached object.
     * @param pk Primary key.
     * @return Returns the cached object or NULL if no object is cached for this key. */
    public Object get(Object pk)
    {
        return _Cache.get(pk);
    }
    
    
    /** Sets a cached object.
     * @param pk Primary key.
     * @param value Object. */
    public void set(Object pk, Object value)
    {
        _Cache.put(pk, value);
    }
    
    
    /** Gets if the cache contains an object.
     * @param pk Primary key.
     * @return Returns TRUE if the cache contains the object. */
    public boolean contains(Object pk)
    {
        return _Cache.containsKey(pk);
    }


    /** Deletes an object from the cache.
     * @param pk Primary key. */
    /// <param name="pk">Primary key.</param>
    public void delete(Object pk)
    {
        _Cache.remove(pk);
    }
}
