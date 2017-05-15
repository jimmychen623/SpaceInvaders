
public class ShipEntity extends Entity{
	/** The game in which the ship exists */
	private Game game;
	
	public ShipEntity(Game game, String ref, int x, int y) {
		super(ref, x, y);
		// TODO Auto-generated constructor stub
		this.game = game;
	}

	@Override
	public void collidedWith(Entity other) {
		// TODO Auto-generated method stub
		
	}

}
