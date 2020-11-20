package swe3.demo;

import java.util.*;



/** This class implements a lazy loading wrapper for framework object lists.
 * @param <T> Type. */
public class LazyList<T> implements List<T>, ILazy
{    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // protected members                                                                                                //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** List values. */
    protected List<T> _internalItems = null;
    
    /** SQL. */
    protected String _sql;
    
    /** Parameters. */
    protected Object[] _params;
    
    /** Type. */
    protected Class<T> _t;
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // constructors                                                                                                     //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Creates a new instance of this class.
     * @param t Type.
     * @param sql SQL query.
     * @param parameters Parameters. */
    public LazyList(Class<T> t, String sql, Object[] parameters)
    {
        _t = t;
        _sql = sql;
        _params = parameters;
    }
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // protected methods                                                                                                //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Gets the items of this list.
     * @return Items. */
    protected List<T> _getItems()
    {
        if(_internalItems == null)
        {
            _internalItems = new ArrayList<>();
            World._fillList(_t, _internalItems, _sql, _params);
        }
        
        return _internalItems;
    }
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // [interface] List<T>                                                                                              //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Returns the number of items in the list.
     * @return List size. */
    @Override
    public int size() 
    {
        return _getItems().size();
    }

    
    /** Returns if the list is empty.
     * @return Returns TRUE if the list is empty, otherwise returns FALSE. */
    @Override
    public boolean isEmpty() 
    {
        return _getItems().isEmpty();
    }

    
    /** Returns if the list contains an object.
     * @param o Object.
     * @return Returns TRUE if the list contains the object, otherwise returns FALSE. */
    @Override
    public boolean contains(Object o) 
    {
        return _getItems().contains(o);
    }

    
    /** Returns an iterator for the list.
     * @return Iterator. */
    @Override
    public Iterator<T> iterator() 
    {
        return _getItems().iterator();
    }

    
    /** Returns an array for the list.
     * @return Array. */
    @Override
    public Object[] toArray() 
    {
        return _getItems().toArray();
    }

    
    /** Returns an array for the list.
     * @param <T> Type.
     * @param a Typed array.
     * @return Array. */
    @Override
    public <T> T[] toArray(T[] a) 
    {
        return _getItems().toArray(a);
    }

    
    /** Adds an element to the list.
     * @param e Element.
     * @return Returns TRUE if successful, otherwise returns FALSE. */
    @Override
    public boolean add(T e) 
    {
        return _getItems().add(e);
    }
    
    
    /** Removes an object from the list.
     * @param o Object.
     * @return Returns TRUE if successful, otherwise returns FALSE. */
    @Override
    public boolean remove(Object o) 
    {
        return _getItems().remove(o);
    }
    
    /** Returns if the list contains all of the given items.
     * @param c Items.
     * @return Returns TRUE if all items are contained, otherwise returns FALSE. */
    @Override
    public boolean containsAll(Collection<?> c) 
    {
        return _getItems().containsAll(c);
    }

    
    /** Adds all given items to the list.
     * @param c Items.
     * @return Returns TRUE if successful, otherwise returns FALSE. */
    @Override
    public boolean addAll(Collection<? extends T> c) 
    {
        return _getItems().addAll(c);
    }

    
    /** Adds all given items to the list at a specific position.
     * @param index Index.
     * @param c Items.
     * @return Returns TRUE if successful, otherwise returns FALSE. */
    @Override
    public boolean addAll(int index, Collection<? extends T> c) 
    {
        return _getItems().addAll(index, c);
    }

    
    /** Removes all given items from the list.
     * @param c Items.
     * @return Returns TRUE if successful, otherwise returns FALSE. */
    @Override
    public boolean removeAll(Collection<?> c) 
    {
        return _getItems().removeAll(c);
    }

    
    /** Removes all items from the list that are not contained in the given collection.
     * @param c Collection.
     * @return Returns TRUE if successful, otherwise returns FALSE. */
    @Override
    public boolean retainAll(Collection<?> c) 
    {
        return _getItems().retainAll(c);
    }

    
    /** Clears the list. */
    @Override
    public void clear() 
    {
        _getItems().clear();
    }

    
    /** Gets the item at a given position.
     * @param index Index.
     * @return Item. */
    @Override
    public T get(int index) 
    {
        return _getItems().get(index);
    }

    
    /** Sets the item st a given position.
     * @param index Index.
     * @param element Item.
     * @return Item. */
    @Override
    public T set(int index, T element) 
    {
        return _getItems().set(index, element);
    }

    
    /** Adds an item to the list.
     * @param index Index.
     * @param element Item. */
    @Override
    public void add(int index, T element) 
    {
        _getItems().add(index, element);
    }

    
    /** Removes an item from the list.
     * @param index Index.
     * @return Item. */
    @Override
    public T remove(int index) 
    {
        return _getItems().remove(index);
    }

    /** Gets the index of an item.
     * @param o Item.
     * @return Index. */
    @Override
    public int indexOf(Object o) 
    {
        return _getItems().indexOf(o);
    }

    
    /** Gets the last index of an item.
     * @param o Item.
     * @return Index. */
    @Override
    public int lastIndexOf(Object o) 
    {
        return _getItems().lastIndexOf(o);
    }

    
    /** Gets a list iterator for this list.
     * @return List iterator. */
    @Override
    public ListIterator<T> listIterator() 
    {
        return _getItems().listIterator();
    }

    
    /** Gets a list iterator for this list.
     * @param index Index.
     * @return List iterator. */
    @Override
    public ListIterator<T> listIterator(int index) 
    {
        return _getItems().listIterator(index);
    }

    
    /** Gets a sublist of this list.
     * @param fromIndex Start index.
     * @param toIndex End index.
     * @return Sublist. */
    @Override
    public List<T> subList(int fromIndex, int toIndex) 
    {
        return _getItems().subList(fromIndex, toIndex);
    }
}
