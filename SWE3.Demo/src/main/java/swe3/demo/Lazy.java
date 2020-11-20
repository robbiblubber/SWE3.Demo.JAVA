package swe3.demo;



/** This class implements a lazy loading wrapper for framework objects. */
public class Lazy<T> implements ILazy
{
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // protected members                                                                                                //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Type. */
    protected Class<T> _t;
    
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
     * @param t Type.
     * @param pks Primary keys. */
    public Lazy(Class<T> t, Object... pks)
    {
        _t = t;
        _pks = pks;
    }
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // protected methods                                                                                                //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Gets the inner type.
     * @return Type. */
    protected Class _getInnerType()
    {
        return _t;
    }
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // public methods                                                                                                   //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Gets the object value.
     * @return Value. */
    public T getValue()
    {
        if(!_initialized) { _value = World.getObject(_t, _pks); _initialized = true; }
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
