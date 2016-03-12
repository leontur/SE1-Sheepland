package game.server.model;


/**
 * Black Sheep Class
 * 
 * @author Leonardo
 */
public class SheepBlack extends Sheep {
	
	/**
	 * region target of sheep (actual location)
	 */
	private Region target;
	
	/**
	 * obj identifier
	 */
	private static final String OBJ_IDENTIFIER = "BlackSheep";
	
	/**
	 * CONSTRUCTOR AND INITIALIZATION
	 */
	public SheepBlack(int id, City city) {
		setThisObjType(OBJ_IDENTIFIER);
		target = city;
		sheepIdentifier = id;
	}
	public SheepBlack() {
		this(0, null);
	}
	
	/**
	 * Get black sheep target
	 * @return
	 */
	public Region getTarget(){
		return target; 
	}
	
	/**
	 * Set black sheep target
	 */
	public void setTarget(Region loc){
		target = loc;
	}
	
}
