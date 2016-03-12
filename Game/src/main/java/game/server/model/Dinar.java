package game.server.model;


/**
 * MAIN DINARS OBJECT CLASS
 * 
 * @author Leonardo
 */
public class Dinar extends Game {
	
	private int dinarIdentifier;
	private static final String OBJ_IDENTIFIER = "Dinar";

	/**
	 * CONSTRUCTOR
	 */
	public Dinar(int dinarId){
		setThisObjType(OBJ_IDENTIFIER);
		this.setIdentifier(dinarId);
	}
	//Default constructor
	public Dinar(){
		this(0);
	}
	
	/**
	 * Set this coin identifier
	 * @param id
	 */
	public void setIdentifier(int id){
		dinarIdentifier = id;
	}
	
	/**
	 * Get this coin identifier
	 * @return 
	 */
	public int getIdentifier(){
		return dinarIdentifier;
	}

	
	
}
