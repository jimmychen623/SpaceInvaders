
public class GiftEntity extends Entity{

	private boolean used = false;
	private Game game;
	
	
	public GiftEntity(Game game, String ref, int x, int y) {
		super(ref, 40, 80);
		this.game = game;
		dy = 0;
		dx = 0;
		x = 40;
		y = 80;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void collidedWith(Entity other) {
		// TODO Auto-generated method stub
		if(used){
			return;
		}
		if (other instanceof ShipEntity) {
			// remove the affected entities
			game.removeEntity(this);
			System.out.println("Gift collided and removed");
			ShipEntity curr = (ShipEntity)(other);
			curr.addAmmo();
			used = true;
			game.hasTakenAmmo = true;
			game.lastAmmoSpawn = System.currentTimeMillis();
		}
	}
	
	public boolean hasTakenAmmo() {
		return used;
	}
	
	public void move(long delta) {
		super.move(0);
		
	}

}
