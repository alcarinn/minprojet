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
	
	private Box boxBlock;
	private Sprite spriteBlock;
	
	public Block (World world, Box boxBlock, Sprite spriteBlock){
		super(world);
		this.boxBlock=boxBlock;
		this.spriteBlock=spriteBlock;
	}
	
	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void draw(Input input, Output output){
		super.draw(input, output);
		if (spriteBlock == null){
	            throw new NullPointerException();
		} else {
			output.drawSprite(spriteBlock, boxBlock);
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
		return super.getBox();
	}
	
	public void interact(Actor other) {
		super.interact(other) ;
		
	}
			
			
	}

	
	

