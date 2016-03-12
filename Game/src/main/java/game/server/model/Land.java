package game.server.model;

/**
 * MAIN LAND (ISLAND) CLASS
 * Contains as sub classes all the objects on the deck
 * 
 * SCALABLE to more than only one (island)
 * 
 * @author Leonardo
 */
public class Land extends Game {
	
	/**
	 * ISLAND IDENTIFIER
	 */
	private int landIdentifier;
	
	/**
	 * CONSTRUCTOR
	 */
	public Land(int id) {
		landIdentifier = id;
	}
	public Land() {
		this(0);
	}
	
	
	/**
	 * GETTER AND SETTER
	 */
	
	/**
	 * Set a new land identifier
	 * @param id
	 */
	public void setNewLandIdentifier(int id){
		landIdentifier = id;
	}
	
	/**
	 * Get a land identifier
	 * @return
	 */
	public int getThisLandIdentifier(){
		return landIdentifier;
	}
	
}
