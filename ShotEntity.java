
public class ShotEntity extends Entity {
	
	/** The speed at which the players shot moves */
	private double moveSpeed = 800;
	
	private Game game;
	/** True if this shot has been "used", i.e. its hit something */
	private boolean used = false;
	
	public ShotEntity(Game game, String ref, int x, int y, double xSpeed, double ySpeed) {
		super(ref, x, y);
		// TODO Auto-generated constructor stub
		this.game = game;
		dy = ySpeed;
		dx = xSpeed;
	}
	
	
	@Override
	public void collidedWith(Entity other) {
		// prevents double kills, if we've already hit something,
		
		if(used){
			return;
		}
		// if we've hit an alien, kill it!
		if (other instanceof AlienEntity) {
			// remove the affected entities
			game.removeEntity(this);
			AlienEntity curr = (AlienEntity)(other);
			curr.setHealth(curr.getHealth() -1);
			if(((AlienEntity)(other)).getHealth() <= 0) {
				game.removeEntity(other);
				game.notifyAlienKilled();
			}			
			// notify the game that the alien has been killed
			
			used = true;
		}
	}
	
	public void move(long delta) {
		// proceed with normal move
		super.move(delta);
		
		// if shot off the screen, remove it
		if (y < -100) {
			game.removeEntity(this);
		}
	}
	
	public double getMoveSpeed() {
		return moveSpeed;
	}
	
}
