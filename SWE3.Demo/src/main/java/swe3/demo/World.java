package swe3.demo;

import java.util.HashMap;



/** This class grants access to the demo framework. */
public final class World 
{
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // private static members                                                                                           //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Entities. */
    private static final HashMap<Class, Entity> _entities = new HashMap<>();
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // public static methods                                                                                            //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /** Gets an entity descriptor for an object.
     * @param o Object.
     * @return Entity. */
    public static Entity __getEntity(Object o)
    {
        Class t = ((o instanceof Class) ? (Class) o : o.getClass());
        
        if(!_entities.containsKey(t)) 
        {
            _entities.put(t, new Entity(t));
        }
        
        return _entities.get(t);
    }
    
    
    /** Creates an instance by its primary keys.
     * @param <T> Type.
     * @param pks Primary keys.
     * @return Object. */
    public static <T> T getObject(Object... pks)
    {
        // TODO: implement!
        return null;
    }
}
