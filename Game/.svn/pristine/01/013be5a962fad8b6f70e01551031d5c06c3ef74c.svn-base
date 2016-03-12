package game.server.model;

/**
 * SUB PLOT CARD CLASS
 * Standard
 * 
 * Own the id and the value. Inherited the type of the plot from super class (initial).
 * 
 * @author Leonardo
 */
public class StandardCard extends InitialCard {
	
	/**
	 * VARS
	 */
	private int value;
	private int standardCardIdentifier;
	private static final String OBJ_IDENTIFIER = "StandardCard";

	
	/**
	 * CONSTRUCTOR
	 */
	public StandardCard(String ptp, int id, int val) {
		setThisObjType(OBJ_IDENTIFIER);
		standardCardIdentifier = id;
		value = val;
		setInitialCardPlotType(ptp);
	}
	//Default constructor
	public StandardCard() {
		this(null, 0, 0);
	}
	
	
	/**
	 * GETTER AND SETTER
	 */
	
	/**
	 * Get the total number of cards in a pile (from db)
	 */
	public int getTotCardPileNum(){
		return db.getStandardPlotCardsNum();
	}
	
	/**
	 * Get the current card identifier
	 * @return
	 */
	public int getCardIdentifier(){
		return standardCardIdentifier;
	}
	
	/**
	 * Get the current card value
	 * @return
	 */
	public int getCardValue(){
		return value;
	}
	
	/**
	 * Set a new card identifier
	 * @param id
	 */
	public void setCardIdentifier(int id){
		standardCardIdentifier = id;
	}
	
	/**
	 * Set a new card value
	 * @param cost
	 */
	public void setCardValue(int v){
		value = v;
	}
	
}
