package game.server.view;

import game.server.model.*;

import java.rmi.registry.Registry;
import java.util.List;

/**
 * 
 * USER INTERACTION MAIN INTERFACE
 * 
 * Interface can be:
 *  -textual
 *  -gui
 * 
 * @author Leonardo
 *
 */
public interface ViewerInterface {
	
	/////////////////////////////////////////////////////////////////////////////////////
	//START CONSOLE
	
	/**
	 * Set a new game interface for view list request
	 * @param game
	 */
	void setNewGameIf(Game game);
	
	/**
	 * set true if is socket, else false for rmi
	 * @param mode
	 */
	void setIsSocketMode(boolean mode);
	
	/**
	 * Send a notify to all clients
	 * @param message
	 */
	void notifyAllClients(String message);
	
	/**
	 * Main string OUT TO CONSOLE
	 * @param message
	 */
	void printToConsole(String message);
	
	/**
	 * Check for remote clients (if are alive or disconnected)
	 * return true if the client is connected, else false
	 * @param clientid
	 * @return
	 */
	boolean isClientAlive(int clientid);
	
	/**
	 * Clear console
	 * @param toAll
	 */
	void clearConsole(boolean toAll);
	
	//PLAYER
	
	/**
	 * Ask user the number of every player
	 * @return
	 */
	int askPlayersNumber();
	
	/**
	 * Ask user the player name
	 * @param playerNumber
	 * @return
	 */
	String askPlayerName(int playerNumber);
	
	//SHEPHERD
	
	/**
	 * Ask user for the new shepherd position
	 * @param shepherd
	 * @param playerName
	 * @return
	 */
	int askForNewShepherdPosition(int shepherd, String playerName);
	
	/**
	 * Ask user which shepherd is using in the current turn
	 * @param playerName
	 * @return
	 */
	int askWhichShepherd(String playerName);
	
	//MOVE
	
	/**
	 * Ask user what is his next move
	 * @param playerName
	 * @param remMoves
	 * @return
	 */
	int askForNewMoveType(String playerName, int remMoves);
	
	/**
	 * Ask user to move the target
	 * @return
	 */
	int askForNewMoveTarget();
	
	/**
	 * Ask user to move the sheep
	 * and return true if he say yes
	 * or false otherwise
	 * @param reg
	 * @param newreg
	 * @return
	 */
	boolean askForSheepToMove(int reg, int newreg);
	
	/**
	 * Ask user to move the black sheep
	 * and return true if he say yes
	 * or false otherwise
	 * @param reg
	 * @param newreg
	 * @return
	 */
	boolean askForBSheepToMove(int reg, int newreg);
	
	/**
	 * Ask user to know the status of the game
	 * and return true if he say yes
	 * or false otherwise
	 * @return
	 */
	boolean askForGameStatus();
	
	//MESSAGE OR NOTIFY
	
	/**
	 * Show a notify if is committed a not allowed move
	 */
	void notifyNotAllowedMoveReAsk();
	
	/**
	 * Show a notify with the number of player if he committed an invalid selection
	 */
	void notifyInvalidPlayersNumberSelection();
	
	/**
	 * Show a notify with the cost of the move
	 * @param price
	 */
	void notifyMoveCost(int price);
	
	/**
	 * Show a notify if there are not adjacent position
	 */
	void notifyNoAdjPosition();
	
	/**
	 * Show a notify if the dinars are not enough for what you want to do
	 */
	void notifyDinarsAreOut();
	
	/**
	 * Show a notify if the position is busy
	 */
	void notifyPositionBusy();
	
	/**
	 * Show a notify for the black sheep to move 
	 * @param status
	 */
	void notifyBSheepAutoMove(boolean status);
	
	/**
	 * Show a notify if the only move allowed is move the shepherd
	 */
	void notifyMoveOnlyShepherd();
	
	/**
	 * Show a notify to see the current position
	 * @param pos
	 */
	void notifyCurrentPosition(int pos);
	
	/**
	 * 
	 * @param pos
	 * @param newpos
	 */
	void notifyMoveSuccessShepherd(int pos, int newpos);
	
	/**
	 * Show a notify with the previous and next position of the shepherd
	 * @param sheep
	 * @param reg
	 * @param newreg
	 */
	void notifyMoveSuccessSheep(int sheep, int reg, int newreg);
	
	/**
	 * Show a notify with the previous and next position of the black sheep
	 * @param bsheep
	 * @param reg
	 * @param newreg
	 */
	void notifyMoveSuccessBSheep(int bsheep, int reg, int newreg);
	
	/**
	 * Show a notify if in the current turn the player have already moved a sheep
	 */
	void notifyMoveAbortSheepAlreadyMoved();
	
	/**
	 * Show a notify if the black sheep is unable to move
	 * @param posBusyBy
	 */
	void notifyMoveAbortBSheepAutoJump(String posBusyBy);

	/**
	 * Show a notify if is not possible do a certain move
	 */
	void notifyMoveAbort();
	
	/**
	 * Show a notify if there are not sheep to move
	 */
	void notifySheepsAreOut();
	
	/**
	 * 
	 * @param cardid
	 * @param plottype
	 */
	void notifyCardBought(int cardid, String plottype);
	
	/**
	 * Show a notify to bought a card
	 */
	void notifyNewTurn();
	
	
	//ENVIRONMENT
	
	/**
	 * Show a welcome message
	 */
	void showWelcomeMessage();
	
	/**
	 * 
	 * @param ply
	 */
	void showClientWelcome(int ply);
	
	/**
	 * Show a notify to initialization the players
	 * @param regions
	 */
	void showSheepsAroundShepherd(List<Region> regions);
	
	/**
	 * Show a notify of the available plot types around the shepherd if there are
	 * @param regions
	 * @param boughtTypes
	 * @return
	 */
	int  showPlotTypesAroundShepherd(List<Region> regions, int[] boughtTypes);
	
	/**
	 * Show a notify to report to the players the cost of the available card and the map status
	 * @param regionPileByPlotType
	 * @param boughtTypes
	 * @param allCities
	 * @param wolf
	 */
	void showMapStatus(List<List<Region>> regionPileByPlotType, int[] boughtTypes, List<City> allCities, Wolf wolf);
	
	/**
	 * Show a notify to report to the players the regions status
	 * @param allRegions
	 * @param allCities
	 * @param wolf
	 */
	void showRegionStatus(List<Region> allRegions, List<City> allCities, Wolf wolf);
	
	/**
	 * 
	 * @param player
	 * @param regionPileByPlotType
	 */
	void showPlayerStatus(Player player, List<List<Region>> regionPileByPlotType);
	
	/**
	 * Show a notify to report its status to the players
	 * @param player
	 * @param allBSheeps
	 * @param regionPileByPlotType
	 */
	void showPlayerScore(Player player, List<SheepBlack> allBSheeps, List<List<Region>> regionPileByPlotType);
	
	/**
	 * Show the random number of the dice
	 * @param rnd
	 */
	void showNewRandomDice(int rnd);
	
	//FINAL COUNTERS
	
	/**
	 * Show a notify to report to the players who is the winner
	 * @param game
	 * @param sortedlist
	 */
	void notifyForWinner(Game game, List<List<Integer>> sortedlist);
	
	//ADDITIONAL RULES
	//WOLF
	
	/**
	 * Notify the movement of the wolf
	 * @param status
	 */
	void notifyWolfAutoMove(boolean status);
	
	/**
	 * Show a notify to report that the wolf has been moved between two regions
	 * @param reg
	 * @param newreg
	 */
	void notifyMoveSuccessWolf(int reg, int newreg);
	
	/**
	 * Show a notify to report that the wolf has been auto-moved
	 * @param posBusyBy
	 */
	void notifyMoveOverrideWolfAutoJump(String posBusyBy);
	
	/**
	 * Show a notify to report that the wolf has not been auto-moved
	 * @param posBusyBy
	 */
	void notifyMoveAbortOverrideWolfAutoJump(String posBusyBy);
	
	/**
	 * Show a notify to report if the wolf eats a sheep o less
	 * @param status
	 */
	void notifyWolfAutoEat(boolean status);
	
	//ONLINE GAME
	//CLIENT - SERVER - RMI - SOCKET
		
	/**
	 * Show all object on registry
	 * @param registry
	 */
	void showAllObjectsOnRegistry(Registry registry);
	
	/**
	 * 
	 */
	void notifyIsDoingTurnAnotherPlayer();
	
	/**
	 * Show a notify to report to the player if is the turn of another player
	 * @param nameTrack
	 */
	void playSoundOnClient(String nameTrack);
	
	//END CONSOLE
	/////////////////////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////////////////////
	//START GUI

	//SHOW THE GAME BOARD MAP
	/**
	 * SHOWS THE MAIN MAP IN A WINDOW
	 * @param mode
	 * @param debug
	 * @return
	 */
	boolean showMap(int mode, boolean debug);
	
	//END GUI
	/////////////////////////////////////////////////////////////////////////////////////
	
}
