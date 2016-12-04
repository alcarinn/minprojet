package platform.game;

import platform.util.Box;
import platform.util.Input;
import platform.util.Sprite;
import platform.util.Vector;
import platform.util.Output;

/**
 * Simple solid actor that does nothing.
 */
public class Block extends Actor {
	
	private Box box;
	private Sprite sprite;
	
	public Block (Box box, Sprite sprite){
		
		this.box=box;
		this.sprite=sprite;
	}
	
	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void draw(Input input, Output output){
		super.draw(input, output);
		if (sprite == null){
	            throw new NullPointerException();
		} else {
			output.drawSprite(sprite, box);
		}
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
	}
	
	@Override
	public boolean isSolid() {
		super.isSolid();
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public Box getBox() {
		// TODO Auto-generated method stub
		return box;
	}
	
	public void interact(Actor other) {
		super.interact(other) ;
		
	}
	
	@Override
	public boolean hurt(Actor instigator, Damage type, double amount, Vector location) {
		// TODO Auto-generated method stub
		super.hurt(instigator, type, amount, location);
		return true;
	}
			
	}

	
	

