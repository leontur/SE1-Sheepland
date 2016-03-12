package game.server.model;

import java.util.Random;

/**
 * MAIN RANDOM DICE CLASS
 * 
 * @author Leonardo
 */
public class Dice extends Game {
	
	//VARS
	private static final String OBJ_IDENTIFIER = "Dice";
	
	//CONSTRUCTOR
	public Dice() {
		setThisObjType(OBJ_IDENTIFIER);
	}
	
	//METHODS
	/**
	 * Get the current object identifier
	 * @return
	 */
	public String getThisObjectType(){
		return OBJ_IDENTIFIER;
	}
	
	/**
	 * MAIN RANDOM METHOD
	 * RETURN A NUMBER FROM 1 AND 6
	 * LIKE A REAL DICE
	 * 
	 * @return
	 */
	public int getNewRandomNum(){
		
		Random r = new Random();
	    int res = 0;
	    
	    res = r.nextInt(6);
	    res++;
	    
		return res;
	}
	
}