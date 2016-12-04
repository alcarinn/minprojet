package platform.game;

import java.awt.event.KeyEvent;

import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class Player extends Actor{
	private Vector position;
	private Vector velocity;
	private final double SIZE = 0.5;
	private boolean colliding;
	
	public Player(Vector position, Vector velocity){
		if (position == null && velocity == null){
			throw new NullPointerException() ;
			}
		this.position=position;
		this.velocity=velocity;
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 42;
	}
	
	@Override
	public Box getBox() {
		// TODO Auto-generated method stub
		return new Box(position, SIZE, SIZE);
	}
	
	@Override
	public Vector getPosition() {
		// TODO Auto-generated method stub
		return super.getPosition();
	}
	
	@Override
	public boolean isSolid() {
		// TODO Auto-generated method stub
		return super.isSolid();
	}
	
	@Override
	public void draw(Input input, Output output) {
		// TODO Auto-generated method stub
		super.draw(input, output);
		Sprite sprite = getSprite("blocker.happy");
		if (sprite == null){
            throw new NullPointerException();
		} else {
			output.drawSprite(sprite, getBox()) ;	
		}
	}
	
	@Override
	public void interact(Actor other) {
		super.interact(other);
		if (other.isSolid()) {
			Vector delta = other.getBox().getCollision(getBox());
			
			if (delta != null) {
				position = position.add(delta);
				//frottements sur les murs
				if (delta.getX() != 0.0){
					velocity = new Vector(0.0, velocity.getY());
				}
				if (delta.getY() != 0.0){
					velocity = new Vector(velocity.getX(), 0.0);
					colliding=true;
				}
			}
		}
	}
	
	@Override
	public void preUpdate(Input input) {
		// TODO Auto-generated method stub
		super.preUpdate(input);
		colliding = false;
	}
	
	@Override
	public void update(Input input) {
		super.update(input);
		double delta = input.getDeltaTime() ;
		
		
		double maxSpeed = 4.0 ;
		if (colliding) {
			double scale = Math.pow (0.001 , input.getDeltaTime ()) ;
			velocity = velocity.mul(scale) ;
		}
		
		if (input.getKeyboardButton(KeyEvent.VK_RIGHT).isDown ()) {
			if (velocity.getX() < maxSpeed) {
				double increase = 60.0 * input.getDeltaTime () ;
				double speed = velocity.getX() + increase ;
				if (speed > maxSpeed) {
					speed = maxSpeed ;
				}
				velocity = new Vector(speed , velocity.getY()) ;
			}
		}
		if (input.getKeyboardButton(KeyEvent.VK_LEFT).isDown ()) {
			if (-velocity.getX() < maxSpeed) {
				double increase = 60.0 * input.getDeltaTime () ;
				double speed =-velocity.getX() + increase ;
				if (speed > maxSpeed) {
					speed = maxSpeed ;
				}
				velocity = new Vector(-speed , velocity.getY()) ;
			}
		}
		
		if (input.getKeyboardButton(KeyEvent.VK_UP).isPressed () && colliding){
			velocity = new Vector(velocity.getX(), 7.0) ;
		}
		
		if (input.getKeyboardButton(KeyEvent.VK_SPACE).isPressed ()){
			getWorld().register(new Fireball(position, velocity.add(velocity.resized (2.0))));
		}
		
		Vector acceleration = World.getGravity();
		velocity = velocity.add(acceleration.mul(delta)) ;
		position = position.add(velocity.mul(delta)) ;
		

	}
	
	@Override
	public void postUpdate(Input input) {
		// TODO Auto-generated method stub
		super.postUpdate(input);
		getWorld().setView(position , 8.0);
	}
}

