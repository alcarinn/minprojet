package platform.game;
import platform.util.Box;
import platform.util.Input;
import platform.util.Output;
import platform.util.Sprite;
import platform.util.Vector;

public class Fireball extends Actor{
	
	private Vector position;
	private Vector velocity;
	public final double SIZE = 0.4;
	
	public Fireball(World world, Vector position, Vector velocity){
		super(world);
		this.position=position;
		this.velocity=velocity;
		if (position == null && velocity == null){
			throw new NullPointerException() ;
			}
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 666;
	}
	@Override
	public void update(Input input) {
		super.update(input);
		double delta = input.getDeltaTime() ;
		Vector acceleration = World.getGravity();
		velocity = velocity.add(acceleration.mul(delta)) ;
		position = position.add(velocity.mul(delta)) ;

	}

	 @Override
	public Box getBox() {
		// TODO Auto-generated method stub
		
		 return new Box(position , SIZE, SIZE) ;

	}
	 
	@Override
	public void draw(Input input, Output output) {
		// TODO Auto-generated method stub
		super.draw(input, output);
		Sprite sprite = getWorld().getLoader().getSprite("fireball");
		if (sprite == null){
            throw new NullPointerException();
	} else {
		output.drawSprite(sprite , getBox(), input.getTime()) ;	
	}
	}

	@Override
	public void interact(Actor other) {
		super.interact(other) ;
			if (other.isSolid()) {
				Vector delta = other.getBox().getCollision(position) ;
				if (delta != null) {
					position = position.add(delta) ;
					velocity = velocity.mirrored(delta) ;
				}
			}
			
	}
	
	@Override
	public boolean isSolid() {
		// TODO Auto-generated method stub
		return super.isSolid();
	}


}
