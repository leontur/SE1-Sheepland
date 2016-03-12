package game.server.model;

/**
 * SUB ENCLOSURE CLASS
 * For final enclosures, extends standard enclosures
 * 
 * @author Leonardo
 */
public class FinalEnclosure extends Enclosure {
	
	private int finEnclosureIdentifier;
	private static final String OBJ_IDENTIFIER = "FinalEnclosure";

	/**
	 * CONSTRUCTOR
	 */
	public FinalEnclosure(int encId) {
		setThisObjType(OBJ_IDENTIFIER);
		finEnclosureIdentifier = encId;
	}
	//Default constructor
	public FinalEnclosure() {
		this(0);
	}
	
	/**
	 * Get the current enclosure identifier
	 * @return
	 */
	public int getEnclosureIdentifier(){
		return finEnclosureIdentifier;
	}
	
	/**
	 * Set a new enclosure identifier
	 * @param id
	 */
	public void setEnclosureIdentifier(int id){
		finEnclosureIdentifier = id;
	}
	
}
