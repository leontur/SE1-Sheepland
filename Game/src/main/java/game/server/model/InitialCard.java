package game.server.model;

/**
 * MAIN PLOT CARD CLASS
 * Initial
 * 
 * Own the id and the type of the plot
 * 
 * @author Leonardo
 */
public class InitialCard extends Land {
	
	private String plottype;
	private int initialCardIdentifier;
	private static final String OBJ_IDENTIFIER = "InitialCard";

	/**
	 * CONSTRUCTOR
	 */
	public InitialCard(String ptp, int id){
		setThisObjType(OBJ_IDENTIFIER);
		initialCardIdentifier = id;
		plottype = ptp;
	}
	//Default constructor
	public InitialCard() {
		this(null, 0);
	}
	
	/**
	 * METHODS
	 */
	
	/**
	 * Get the current card identifier
	 * @return
	 */
	public int getInitialCardIdentifier(){
		return initialCardIdentifier;
	}
	
	/**
	 * Get the plot type
	 */
	public String getInitialCardPlotType(){
		return plottype;
	}
	
	/**
	 * Get the plot type for passed index
	 * @param id
	 */
	public String getPlotTypeById(int id){
		return db.getPlotTypes().get(id);
	}
	
	/**
	 * Set a new card identifier
	 * @param id
	 */
	public void setInitialCardIdentifier(int id){
		initialCardIdentifier = id;
	}
	
	/**
	 * Set a plot type
	 * @param typ
	 */
	public void setInitialCardPlotType(String typ){
		plottype = typ;
	}
	
}
