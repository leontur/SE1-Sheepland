package game.server.model;

import java.util.List;

/**
 * MAIN CITY CLASS
 * 
 * @author Leonardo
 */
public class City extends Region {
	
	//OBJ IDENTIFIER
	private static final String OBJ_IDENTIFIER = "City";
	
	//CITY IDENTIFIER
	private int cityIdentifier;
		
	/**
	 * CONSTRUCTOR
	 * AND INITIALIZATION
	 */
	public City(int regid, int cityid, SheepBlack newBsheep) {
		//Initialization is all in this constructor
		setThisObjType(OBJ_IDENTIFIER);
		//setting city name as plottype
		setInitialCardPlotType("Sheepsburg");
		//inherited - sequential after regions
		setRegionIdentifier(regid);
		//private id of the city - sequential from 0
		this.setCityIdentifier(cityid);
		//clear all white sheeps
		getAllSheeps().clear();
		//ad the first black sheep
		sheepBCount.add(newBsheep);
	}
	public City() {
		this(0, 0, null);
	}

	/**
	 * METHODS
	 */
	
	/**
	 * Retrieve current city's adjacent positions
	 * @return
	 */
	public List<Position> getAdjPositions(){
		return adjPos;
	}
	
	/**
	 * Set current city's new adjacent positions
	 * @param posns
	 */
	public void setAdjPositions(List<Position> posns){
		adjPos = posns;
	}
	
	/**
	 * Set a new (sequential) identifier for this City
	 * @param newid
	 */
	public void setCityIdentifier(int newid){
		cityIdentifier = newid;
	}

	/**
	 * Get the current City identifier
	 * @return
	 */
	public int getCityIdentifier(){
		return cityIdentifier;
	}
	
	/**
	 * Get the current City name
	 * @return
	 */
	public String getCityName(){
		return getInitialCardPlotType();
	}
	
}
