package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Loader;
import platform.util.Vector;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.SortedCollection;

/**
 * Base class of all simulated actors, attached to a world.
 */
public abstract class Actor implements Comparable<Actor> {
	private World world;
	// TO BE COMPLETED

	public Actor(World world) {
		this.world = world;
	}

	public void preUpdate(Input input) {
	}

	public void update(Input input) {
	}

	public void postUpdate(Input input) {
	}

	public void draw(Input input, Output output) {
	}

	public abstract int getPriority();

	public int compareTo(Actor other) {
		if (this.getPriority() > other.getPriority()) {
			return -1;
		}
		if (this.getPriority() == other.getPriority()) {
			return 0;
		} else {
			return 1;
		}
	}

	public boolean isSolid() {
		return false;
	}

	public Box getBox() {
		return null;
	}

	public Vector getPosition() {
		Box box = getBox();
		if (box == null)
			return null;
		return box.getCenter();
	}

	public void interact(Actor other) {
	}

	public void register(World world) {
		this.world = world;
	}

	public void unregister() {
		world = null;
	}

	protected World getWorld() {
		return world;
	}

	protected Sprite getSprite(String name) {

		return world.getLoader().getSprite(name);

	}

}
