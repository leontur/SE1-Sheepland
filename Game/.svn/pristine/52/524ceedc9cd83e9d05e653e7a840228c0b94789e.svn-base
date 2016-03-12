package game.server.model;

/**
 * WOLF Class
 * NOTE: Additional rule
 * 
 * @author Leonardo
 */
public class Wolf extends Pawn {
	
	/**
	 * region target of wolf (actual location)
	 */
	private Region target;
	
	/**
	 * wolf identifier
	 */
	private int wolfIdentifier;
	
	/**
	 * obj identifier
	 */
	private static final String OBJ_IDENTIFIER = "Wolf";
	
	/**
	 * CONSTRUCTOR AND INITIALIZATION
	 */
	public Wolf(int id, Region reg) {
		setThisObjType(OBJ_IDENTIFIER);
		target = reg;
		wolfIdentifier = id;
	}
	public Wolf() {
		this(0, null);
	}
	
	/**
	 * Get Wolf target
	 * @return
	 */
	public Region getTarget(){
		return target; 
	}
	
	/**
	 * Set Wolf target
	 */
	public void setTarget(Region loc){
		target = loc;
	}
	
	/**
	 * Get Wolf id
	 */
	public int getWolfIdentifier(){
		return wolfIdentifier; 
	}
	
	/**
	 * Set Wolf id
	 */
	public void setWolfIdentifier(int newid){
		wolfIdentifier = newid; 
	}

}
