package platform.game;

import platform.util.Box;
import platform.util.Loader;
import platform.util.Vector;

/**
 * Represents an environment populated by actors.
 */
public interface World {

    /** @return associated loader, not null */
	
	public void setView(Vector center, double radius);
    public Loader getLoader();
    
   
    public void register(Actor actor);

    public void unregister(Actor actor);
    
    public static Vector getGravity(){
    	Vector v = new Vector(0.0 ,-9.81);
    	return v;
    }
  
}


