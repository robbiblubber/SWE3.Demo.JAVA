package swe3.demo;



/** This class implements a lazy loading wrapper for framework objects. */
public class Lazy<T> implements ILazy
{
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // protected members                                                                                                //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Primary keys. */
    protected Object[] _pks;
    
    /** Value. */
    protected T _value;
    
    /** Initialized flag. */
    protected boolean _initialized = false;
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // constructors                                                                                                     //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Creates a new instance of this class.
     * @param pks Primary keys. */
    public Lazy(Object... pks)
    {
        _pks = pks;
    }
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // public methods                                                                                                   //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Gets the object value.
     * @return Value. */
    public T getValue()
    {
        if(!_initialized) { _value = World.<T>getObject(_pks); _initialized = true; }
        return _value;
    }
    
    /** Sets the object value.
     * @param value Value. */
    public void setValue(T value)
    {
        _value = value;
        _initialized = true;
    }
}
