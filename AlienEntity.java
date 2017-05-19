
/**
 * An entity which represents one of our space invader aliens.

 */
public class AlienEntity extends Entity {
	/** The initial speed at which the alien moves horizontally */
	private double moveSpeed = 30;
	/** The game in which the entity exists */
	private Game game;
	private int health = 2;
	
	/**
	 * Create a new alien entity
	 * 
	 * @param game The game in which this entity is being created
	 * @param ref The sprite which should be displayed for this alien
	 * @param x The intial x location of this alien
	 * @param y The intial y location of this alien
	 */
	public AlienEntity(Game game,String ref,int x,int y) {
		super(ref,x,y);
		
		this.game = game;
		dx = ((Math.random()+ 0.8) / 2) * moveSpeed;
		dx *= Math.floor(Math.random()*2) == 1 ? 1 : -1;
		dy = ((Math.random() + 0.8) / 2) * moveSpeed;
		dy *= Math.floor(Math.random()*2) == 1 ? 1 : -1;
		
				
	}

	/**
	 * Request that this alien moved based on time elapsed
	 * 
	 * @param delta The time that has elapsed since last move
	 */
	public void move(long delta) {
		// if we have reached the left hand side of the screen and
		// are moving left then request a logic update 
		if ((dx < 0) && (x < 10)) {
			game.updateLogic();
		}
		// if we have reached the right hand side of 
		// the screen and are moving right, request a logic update
		if ((dx > 0) && (x > 750)) {
			game.updateLogic();
		}
		
		if(dy > 0 && y > 580) {
			game.updateLogic();
		}
		if(dy < 0 && y < 5) {
			game.updateLogic();
		}
		
		// proceed with normal move
		super.move(delta);
	}
	
	/**
	 * Update the game logic related to aliens
	 */
	public void doLogic() {
		
		if ((dx < 0) && (x < 10)) {
			dx = -dx;
		}
		// if we have reached the right hand side of 
		// the screen and are moving right, request a logic update
		if ((dx > 0) && (x > 750)) {
			dx = -dx;
		}
		
		if(dy > 0 && y > 580) {
			dy = -dy;
		}
		if(dy < 0 && y < 5) {
			dy = -dy;
		}
	}
	
	/**
	 * Notification that this alien has collided with another entity
	 * 
	 * @param other The other entity
	 */
	public void collidedWith(Entity other) {
		
	}
	
	/**
	 * @return health The health of the alient
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * @param health
	 * Sets the health of this alien
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	public double getMoveSpeed() {
		return moveSpeed;
	}
	
	public void setMoveSpeed(double moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
	
	
}