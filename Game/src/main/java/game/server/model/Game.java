package game.server.model;

import game.client.interfaces.ClientConsoleInterface;
import game.server.interfaces.*;
import game.server.view.*;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * MAIN GAME CLASS
 * 
 * @author Leonardo
 *
 */
public class Game {
	
	/**
	 * CLASS VARS
	 */
	protected DatabaseInterface db = new Database();
	
	/**
	 * CONNECTION VARS
	 */
	private boolean runningGame;
	private boolean allPlayersInitialized;
	private boolean allViewsRequested;
	private boolean isSocketMode;
	
	/**
	 * TURN VARS
	 */
	//move count
	private final int maxMovementCount = db.getMaxMovementCount();
	private int moveDoneCount = 0;
	
	//movement types
	private boolean shepherdHaveBeenMoved; 
	private boolean sheepHaveBeenMoved;
	private boolean blackSheepHaveBeenMoved;
	private boolean cardHaveBeenBought;
	private boolean wolfHaveBeenMoved;
	
	//final phase
	private boolean finalTurn = false;
	
	//remaining enclosures
	private int remainingStandardEnclosures = db.getEnclosureNum();
	private int remainingFinalEnclosures = db.getFinalEnclosureNum();
	
	//card counters
	private int[] boughtTypes = new int[db.getPlotTypesNum()];
	
	//current player counter
	private int currPlayerCounter = 0;
	private int lastDiceNum;	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//START OBJECT VARS
	
	/**
	 * Object type allocation
	 */
	private String objTYPE;	
	
	/**
	 * Main players total number for this game
	 */
	private static int playersNumber;
	
	/**
	 * Positions (all)
	 * Main list of all positions of the game
	 */
	private static List<Position> allPositions = new ArrayList<Position>();
	
	/**
	 * Sheep (all)
	 * Main list of all sheeps of the game
	 */
	private static List<Sheep> allSheeps = new ArrayList<Sheep>();
	
	/**
	 * Sheeps Black (all)
	 * Main list of all black sheeps of the game
	 */
	private static List<SheepBlack> allBSheeps = new ArrayList<SheepBlack>();
	
	/**
	 * Initial cards
	 * Main list of initial cards pile of the game 
	 */
	private static List<InitialCard> initialCardsPile = new ArrayList<InitialCard>();
	
	/**
	 * Pile of standard cards by plot type
	 * Main list of plot Cards By Plot Type of the game
	 */
	private static List<List<StandardCard>> plotCardsPileByPlotType = new ArrayList<List<StandardCard>>();
	
	/**
	 * Regions by plot type
	 * Main list of regions by plot cards of the game
	 */
	private static List<List<Region>> regionPileByPlotType = new ArrayList<List<Region>>();
	
	/**
	 * Regions (all)
	 * Main list of all regions of the game
	 */
	private static List<Region> allRegions = new ArrayList<Region>();
	
	/**
	 * Cities (all)
	 * Main list of all cities of the game
	 */
	private static List<City> allCities = new ArrayList<City>();
	
	/**
	 * Shepherds (all)
	 * Main list of all shepherds of the game
	 */
	private static List<Shepherd> allShepherds = new ArrayList<Shepherd>();

	/**
	 * Dinars (all)
	 * Main list of all dinars of the game
	 */
	private static List<Dinar> allDinars = new ArrayList<Dinar>();
	
	/**
	 * Enclosures (std) (all)
	 * Main list of all standard enclosure of the game
	 */
	private static List<Enclosure> allStandardEnclosures = new ArrayList<Enclosure>();
	
	/**
	 * Final Enclosures (all)
	 * Main list of all final enclosure if the game
	 */
	private static List<FinalEnclosure> allFinalEnclosures = new ArrayList<FinalEnclosure>();
	
	/**
	 * Dice (one)
	 * Dice of the game
	 */
	private static Dice dice;
	
	/**
	 * Wolf (one)
	 * Wolf of the game
	 */
	private static Wolf wolf;
		
	/**
	 * Players (all)
	 * Main list of players of the game
	 */
	private static List<Player> players = new ArrayList<Player>();
	
	/**
	 * Allocate a game viewer (for both rmi and socket)
	 */
	private ViewerInterface mainGameViewer = new Console(this);
	
	/**
	 * Create list for future clients viewer storage (initialized from rmi using a setter) (RMI)
	 */
	private List<ClientConsoleInterface> viewerList = new ArrayList<ClientConsoleInterface>();
	
	/**
	 * Create list for future clients viewer storage (initialized from manage socket) (SOCKET)
	 */
	private List<ServerSocket> socketViewerList = new ArrayList<ServerSocket>();
	
	//END VARS
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//START OF METHODS
	
	/**
	 * Get all game positions
	 */
	public List<Position> getGameAllPositions(){
		return allPositions;
	}
	
	/**
	 * Get all sheep in the game
	 */
	public List<Sheep> getGameAllSheeps(){
		return allSheeps;
	}
	
	/**
	 * Get all Black sheep in the game
	 */
	public List<SheepBlack> getGameAllBSheeps(){
		return allBSheeps;
	}
	
	/**
	 * Get initial cards' pile
	 */
	public List<InitialCard> getGameInitialCardsPile(){
		return initialCardsPile;
	}
	
	/**
	 * Get plot cards pile by plot type
	 */
	public List<List<StandardCard>> getGamePlotCardsPileByPlotType(){
		return plotCardsPileByPlotType;
	}
	
	/**
	 * Get region pile by plot type
	 */
	public List<List<Region>> getGameRegionPileByPlotType(){
		return regionPileByPlotType;
	}
	
	/**
	 * Get all regions
	 */
	public List<Region> getGameAllRegions(){
		return allRegions;
	}
	
	/**
	 * Get all cities
	 */
	public List<City> getGameAllCities(){
		return allCities;
	}
	
	/**
	 * Get all shepherds
	 */
	public List<Shepherd> getGameAllShepherds(){
		return allShepherds;
	}
	
	/**
	 * Get all dinars 
	 */
	public List<Dinar> getGameAllDinars(){
		return allDinars;
	}
	
	/**
	 * Get all standard enclosures
	 */
	public List<Enclosure> getGameAllStandardEnclosures(){
		return allStandardEnclosures;
	}
	
	/**
	 * Get all final enclosures
	 */
	public List<FinalEnclosure> getGameAllFinalEnclosures(){
		return allFinalEnclosures;
	}
	
	/**
	 * Get dice
	 */
	public Dice getGameDice(){
		return dice;
	}
	
	/**
	 * Get wolf
	 */
	public Wolf getGameWolf(){
		return wolf;
	}
	
	/**
	 * Get players
	 */
	public List<Player> getGamePlayers(){
		return players;
	}
	
	 /**
     * Get viewer
     */
	public ViewerInterface getGameViewer(){
		return mainGameViewer;
	}
	
	/**
	 * OBJECTS SETTERS
	 */	
	
	/**
	 * Set all game positions
	 */
	public void setGameAllPositions(List<Position> newlist){
		allPositions = newlist;
	}
	
	/**
	 * Set all sheep in the game
	 */
	public void setGameAllSheeps(List<Sheep> newlist){
		allSheeps = newlist;
	}
	
	/**
	 * Set all Black sheep in the game
	 */
	public void setGameAllallBSheeps(List<SheepBlack> newlist){
		allBSheeps = newlist;
	}
	
	/**
	 * Set initial cards' pile
	 */
	public void setGameInitialCardsPile(List<InitialCard> newlist){
		initialCardsPile = newlist;
	}
	
	/**
	 * Set plot cards pile by plot type
	 */
	public void setGamePlotCardsPileByPlotType(List<List<StandardCard>> newlist){
		plotCardsPileByPlotType = newlist;
	}
	
	/**
	 * Set region pile by plot type
	 */
	public void setGameRegionPileByPlotType(List<List<Region>> newlist){
		regionPileByPlotType = newlist;
	}
	
	/**
	 * Set all regions
	 */
	public void setGameAllRegions(List<Region> newlist){
		allRegions = newlist;
	}
	
	/**
	 * Set all cities
	 */
	public void setGameAllCities(List<City> newlist){
		allCities = newlist;
	}
	
	/**
	 * Set all shepherds
	 */
	public void setGameAllShepherds(List<Shepherd> newlist){
		allShepherds = newlist;
	}
	
	/**
	 * Set all dinars 
	 */
	public void setGameAllDinars(List<Dinar> newlist){
		allDinars = newlist;
	}
	
	/**
	 * Set all standard enclosures
	 */
	public void setGameAllStandardEnclosures(List<Enclosure> newlist){
		allStandardEnclosures = newlist;
	}
	
	/**
	 * Set all final enclosures
	 */
	public void setGameAllFinalEnclosures(List<FinalEnclosure> newlist){
		allFinalEnclosures = newlist;
	}
	
	/**
	 * Set dice
	 */
	public void setGameDice(Dice newdice){
		dice = newdice;
	}
	
	/**
	 * Set wolf
	 */
	public void setGameWolf(Wolf newwolf){
		wolf = newwolf;
	}
	
	/**
	 * Set players
	 */
	public void setGamePlayers(List<Player> newlist){
		players = newlist;
	}
	
	/**
     * Set viewer
     */
	public void setGameViewer(ViewerInterface newview){
		mainGameViewer = newview;
	}
	
	//////////////////////////////////////////////////////////////////////////////
	
	/**
	 * SETTERS
	 */
	
	/**
	 * Set a new string as a object type
	 * @param name
	 */
	public void setThisObjType(String name){
		objTYPE = name;
	}
	
	/**
	 * Set main players number counter
	 * @param number
	 */
	public void setPlayersNumber(int number){
		playersNumber = number;
	}
	
	/**
	 * GETTERS
	 */
	
	/**
	 * Get main players number counter
	 * @param number
	 */
	public int getPlayersNumber(){
		return playersNumber;
	}
	
	/**
	 * Get a new string as a object type
	 * @param name
	 */
	public String getObjType(){
		return objTYPE;
	}
	
	//END OF METHODS
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//START OF SECONDARY METHODS (RMI-SOCKET)
	
	/**
	 * Set if the game runs
	 */
	public void setRunningGame(boolean newstatus){
		runningGame = newstatus;
	}
	
	/**
	 * True if the game runs or false if the game 
	 * doesen't run
	 */
	public boolean isRunningGame(){
		return runningGame;
	}
	
	/**
	 * Set all players initialized
	 * @param newstatus
	 */
	public void setAllPlayersInitialized(boolean newstatus){
		allPlayersInitialized = newstatus;
	}
	
	/**
	 * True if the players are all initialized or false if even one 
	 * is not
	 * @return
	 */
	public boolean isAllPlayersInitialized(){
		return allPlayersInitialized;
	}
	
	//FOR RMI ONLY
	/**
	 * Set a viewer list
	 * @param newstatus
	 */
	public void setViewerList(List<ClientConsoleInterface> newstatus){
		viewerList = newstatus;
	}
	
	/**
	 * Add a viewer list
	 * @param newclient
	 */
	public void addViewerToList(ClientConsoleInterface newclient){
		viewerList.add(newclient);
	}
	
	/**
	 * Get a viewer list
	 * @return
	 */
	public List<ClientConsoleInterface> getViewerList(){
		return viewerList;
	}
	
	//FOR BOTH RMI AND SOCKET
	
	/**
	 * Set all requested views
	 * @param newstatus
	 */
	public void setAllViewsRequested(boolean newstatus){
		allViewsRequested = newstatus;
	}
	
	/**
	 * Get all requested views
	 * @return
	 */
	public boolean getAllViewsRequested(){
		return allViewsRequested;
	}
	
	/**
	 * Set the socket mode, on or off
	 * @param mode
	 */
	public void setIsSocketMode(boolean mode){
		isSocketMode = mode;
	}
	
	/**
	 * True if is the socket is running or false if its
	 * not running
	 * @return
	 */
	public boolean isSocketMode(){
		return isSocketMode;
	}
	
	//FOR SOCKET ONLY
	
	/**
	 * Add a viewer to a socket list
	 * @param newserver
	 */
	public void addViewerToSocketList(ServerSocket newserver){
		socketViewerList.add(newserver);
	}
	public List<ServerSocket> getSocketViewerList(){
		return socketViewerList;
	}
	
	//END OF SECONDARY METHODS
	/////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	// START OF GAME STATUS METHODS
	
	/**
	 * Clear the history of movement of a turn (called for a new turn)
	 */
	public void clearMoves(){
		moveDoneCount = 0;
		sheepHaveBeenMoved = false;
		blackSheepHaveBeenMoved = false;
		cardHaveBeenBought = false;
		shepherdHaveBeenMoved = false;
		wolfHaveBeenMoved = false;
	}
	
	
	/**
	 * CHEKERS - GETTERS - SETTERS
	 */
	
	//CHECKERS
	/**
	 * True if a shepherd has been moved 
	 * false otherwise
	 * @return
	 */
	public boolean isShepherdBeenMoved() {
		return shepherdHaveBeenMoved;
	}
	
	/**
	 * True if a card has been bought
	 * false otherwise
	 * @return
	 */
	public boolean isCardBeenBought() {
		return cardHaveBeenBought;
	}
	
	/**
	 * True if a sheep has been moved 
	 * false otherwise
	 * @return
	 */
	public boolean isSheepBeenMoved() {
		return sheepHaveBeenMoved;
	}
	
	/**
	 * True if a black sheep has been moved 
	 * false otherwise
	 * @return
	 */
	public boolean isBSheepBeenMoved() {
		return blackSheepHaveBeenMoved;
	}
	
	/**
	 * True if is the final turn
	 * false otherwise
	 * @return
	 */
	public boolean isFinalTurn() {
		return finalTurn;
	}
	
	/**
	 * True if the wolf has been moved 
	 * false otherwise
	 * @return
	 */
	public boolean isWolfBeenMoved() {
		return wolfHaveBeenMoved;
	}
	
	//GETTER
	
	/**
	 * Get the number of standard enclosures left
	 * @return
	 */
	public int getRemainingStandardEnclosuresNum() {
		return remainingStandardEnclosures;
	}
	
	/**
	 * Get the number of final enclosures left
	 * @return
	 */
	public int getRemainingFinalEnclosuresNum() {
		return remainingFinalEnclosures;
	}
	
	/**
	 * Get the count of maximum number of moves
	 * @return
	 */
	public int getMaxMoveCount() {
		return maxMovementCount;
	}
	
	/**
	 * Get all the types of bought cards
	 * @return
	 */
	public int[] getAllBoughtTypes() {
		return boughtTypes;
	}

	/**
	 * Get the count of done moves
	 * @return
	 */
	public int getMoveDoneCount() {
		return moveDoneCount;
	}
	
	/**
	 * Get the counter of the current player
	 * @return
	 */
	public int getCurrentPlayerCounter(){
		return currPlayerCounter;	
	}
	
	/**
	 * Get the number of last dice thrown
	 */
	public int getLastDiceNum(){
		return lastDiceNum;
	}
	
	//SETTER
	
	/**
	 * Set the number of standard enclosures left
	 * @return
	 */
	public void setRemainingStandardEnclosuresNum(int num) {
		remainingStandardEnclosures = num;
	}

	/**
	 * Set the number of final enclosures left
	 * @return
	 */
	public void setRemainingFinalEnclosuresNum(int num) {
		remainingFinalEnclosures = num;
	}
	
	/**
	 * Get if shepherd has been moved
	 * @param bool
	 */
	public void setShepherdBeenMoved(boolean bool) {
		shepherdHaveBeenMoved = bool;
	}

	/**
	 * Set if a card has been bought
	 * @param bool
	 */
	public void setFieldBeenBought(boolean bool) {
		cardHaveBeenBought = bool;
	}
	
	/**
	 * Set if sheep has been moved
	 * @param bool
	 */
	public void setSheepBeenMoved(boolean bool) {
		sheepHaveBeenMoved = bool;
	}

	/**
	 * Set if black sheep has been moved
	 * @param bool
	 */
	public void setBSheepBeenMoved(boolean bool) {
		blackSheepHaveBeenMoved = bool;
	}
	
	/**
	 * Set if is the final turn of the game
	 * @param bool
	 */
	public void setFinalTurn(boolean bool) {
		finalTurn = bool;
	}
	
	/**
	 * Set if wolf has been moved
	 * @param bool
	 */
	public void setWolfBeenMoved(boolean bool) {
		wolfHaveBeenMoved = bool;
	}
	
	/**
	 * Set all the types of bought cards
	 * @param count
	 */
	public void setAllBoughtTypes(int[] numbers) {
		//security clone
		if(numbers != null){
			boughtTypes = Arrays.copyOf(numbers, numbers.length);
		}
	}
	
	/**
	 * Set the count of done moves 
	 * @param count
	 */
	public void setMoveDoneCount(int count) {
		moveDoneCount = count;
	}
	
	/**
	 * Increase the count of moves by one
	 */
	public void increaseMoveDoneCountByOne() {
		moveDoneCount++;
	}
	
	/**
	 * Set the counter of the current player
	 * @param id
	 */
	public void setCurrentPlayerCounter(int id){
		currPlayerCounter = id;	
	}
	
	/**
	 * Set the number of last dice thrown
	 * @param n
	 */
	public void setLastDiceNum(int n){
		lastDiceNum = n;
	}
	
	//END OF SECONDARY METHODS (RMI)
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
}
