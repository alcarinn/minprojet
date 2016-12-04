package platform.game;

import platform.game.level.Level;
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

	public static Vector getGravity() {
		return new Vector(0.0, -9.81);
	}

	// permet d'indiquer que la transition à un autre niveau
	// doit se faire :
	public void nextLevel();

	// permet de passer au niveau level :
	public void setNextLevel(Level level);
  
}


