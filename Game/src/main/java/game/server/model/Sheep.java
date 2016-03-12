package game.server.model;


/**
 * White Sheep Class
 * 
 * @author Leonardo
 */
public class Sheep extends Pawn {
		
	/**
	 * Sheep identifier
	 */
	protected int sheepIdentifier;
	
	/**
	 * Obj identifier
	 */
	private static final String OBJ_IDENTIFIER = "Sheep";
	
	/**
	 * CONSTRUCTOR AND INITIALIZATION
	 */
	public Sheep(int id) {
		setThisObjType(OBJ_IDENTIFIER);
		sheepIdentifier = id;
	}
	public Sheep() {
		this(0);
	}
		
	/**
	 * Get sheep identifier
	 * @return
	 */
	public int getSheepIdentifier(){
		return sheepIdentifier;
		}
	
}
