
public class ShipEntity extends Entity{
	/** The game in which the ship exists */
	private Game game;
	private int ammo;
	public ShipEntity(Game game, String ref, int x, int y) {
		super(ref, x, y);
		// TODO Auto-generated constructor stub
		this.game = game;
		this.ammo = 5;
	}

	
	public void move(long delta) {
		// if we're moving left and have reached the left hand side
		// of the screen, don't move
		if (dx < 0 && x < 50) {
			return;
		}
		// same thing but for right hand side
		if (dx > 0 && x > 750) {
			return;
		}
		if(dy > 0 && y > 580) {
			return;
		}
		if(dy < 0 && y < 10) {
			return;
		}
		
		super.move(delta);
	}
	@Override
	public void collidedWith (Entity other) {
		// TODO Auto-generated method stub
		// if its an alien, notify the game that the player is dead
		if (other instanceof AlienEntity) {
			game.notifyDeath();
		}
	}
	
	public void addAmmo() {
		this.ammo +=100;
	}
	
	public boolean hasAmmo() {
		return this.ammo > 0;
	}
	
	public void decreaseAmmo() {
		this.ammo -= 1;
	}
	
	public int getAmmo() {
		return this.ammo;
	}

}
