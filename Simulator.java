package platform.game;

import platform.game.level.Level;
import java.util.ArrayList;
import java.util.List;
import platform.util.Box;

import platform.util.Input;
import platform.util.Loader;
import platform.util.Output;
import platform.util.SortedCollection;
import platform.util.Sprite;
import platform.util.Vector;
import platform.util.View;

/**
 * Basic implementation of world, managing a complete collection of actors.
 */
public class Simulator implements World {

    private Loader loader;
    private Vector currentCenter; 
    private double currentRadius;
    private Vector expectedCenter;
    private double expectedRadius;
    private List<Actor> registered;
    private List<Actor> unregistered;
    private SortedCollection<Actor> actors;
    private Level next;
    private boolean	transition;

    
  
    /**
     * Create a new simulator.
     * @param loader associated loader, not null
     * @param args level arguments, not null
     */
	public Simulator(Loader loader, String[] args) {
        if (loader == null)
            throw new NullPointerException();
        this.loader = loader;
        currentCenter = Vector.ZERO; 
        currentRadius = 10.0;
        expectedRadius = 10.0; 
        expectedCenter = Vector.ZERO;
        registered = new ArrayList<Actor>();
        unregistered = new ArrayList<Actor>();
        actors = new SortedCollection<Actor>();
        next = null;
        transition = true;
	}
	
    /**
     * Simulate a single step of the simulation.
     * @param input input object to use, not null
     * @param output output object to use, not null
     */
	
	public void update(Input input, Output output) {
        
      // TO BE COMPLETED
		
		double factor = 0.015;
		currentCenter = currentCenter.mul(1.0 - factor).add(expectedCenter.mul(factor)); 
		currentRadius = currentRadius * (1.0 - factor) + expectedRadius * factor;
		
		View view = new View(input, output);
		
		view.setTarget(currentCenter, currentRadius);
		
		//PreUpdate
		for (Actor a : actors) 
			a.preUpdate(view);
		//Interact
		for (Actor actor : actors) 
			for (Actor other : actors)
				if (actor.getPriority() > other.getPriority()) 
					actor.interact(other);
		//Update
		for (Actor a : actors) 
			a.update(view);
		//Draw
		for (Actor a : actors.descending()) 
			a.draw(view, view);
		
		
		
		//Registered
		for (int i = 0; i < registered.size(); ++i) { 
			Actor actor = registered.get(i);
			if (!actors.contains(actor)) {
				actor.register(this);
				actors.add(actor); 
			}
		} 
		registered.clear();
		
		
			
		
		// Remove unregistered actors
		for (int i = 0; i < unregistered.size(); ++i) { 
			Actor actor = unregistered.get(i); 
			actor.unregister();
			actors.remove(actor);
			
		} 
		unregistered.clear();
		
		
		
		//PostUpdate
		for (Actor a : actors) 
			a.postUpdate(view);
		
		// si un acteur a mis transition à true pour demander le passage
		// à un autre niveau :
		if (transition) {
			if (next == null) {
				next = Level.createDefaultLevel () ;
		}
		// si un acteur a appelé setNextLevel , next ne sera pas null :
		Level level = next ;
		transition = false ;
		next = null ;
		actors.clear () ;
		registered.clear () ;
		// tous les anciens acteurs sont désenregistrés ,
		// y compris le Level précédent :
		unregistered.clear () ;
		register(level) ;
		}
	}
	
	
	
	

    @Override
    public Loader getLoader() {
        return loader;
    }

	@Override
	public void setView(Vector center, double radius) {
		// TODO Auto-generated method stub
		if (center == null)
			throw new NullPointerException();
			if (radius <= 0.0)
			throw new IllegalArgumentException("radius must be positive"); 
			expectedCenter = center; 
			expectedRadius = radius;
	}

	@Override
	public void register(Actor actor) {
		registered.add(actor); 
	}
	
	@Override
	public void unregister(Actor actor) {
		unregistered.add(actor); 
	}

	@Override
	public void nextLevel() {
		transition = true;		
	}

	@Override
	public void setNextLevel(Level level) {
		next=level;
		
	}
}
