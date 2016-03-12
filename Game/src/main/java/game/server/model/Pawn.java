package game.server.model;

/**
 * SCALABLE PAWN CLASS
 * SET THE PROPERTIES OF THE ALL SUB CLASSES
 * 
 * @author Leonardo
 *
 */
public class Pawn extends Land {
		
	/**
	 * MAIN PAWN MOVEMENT ABILITY BOOL
	 * Indicates if the object can move in the land
	 */
	private boolean canMove = true;
	
	/**
	 * METHODS
	 */
	public boolean getMovementAbility(){
		return canMove;
	}
	
	public void setMovementAbility(boolean s){
		canMove=s;
	}
	
}
