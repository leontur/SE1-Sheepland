package game.server.interfaces;

import game.server.model.Game;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface AddPlayerInterface extends Remote {
	
	///////////////////////////////////////////////////////////////////
	//CLIENT SET

	/**
	 * Get the counter id of the current player
	 * @return
	 * @throws RemoteException
	 */
	int getCurrentPlayerNumbers() throws RemoteException;
	
	/**
	 * Add a new player
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws InterruptedException
	 */
	void addNewPlayer() throws RemoteException, NotBoundException, InterruptedException;
	
	/**
	 * Set to game the player selective view
	 * @throws RemoteException
	 * @throws Exception
	 */
	void setClientView() throws RemoteException, Exception;

	/**
	 * True if the game runs or false if the game 
	 * doesen't run
	 * @return
	 * @throws RemoteException
	 */
	boolean isRunningGame() throws RemoteException;
	
	/**
	 * Get the number of players of the current game
	 * @return
	 * @throws RemoteException
	 */
	int getThisGamePlayersNumber() throws RemoteException;
	
	/**
	 * Set the number of players of the current game
	 * @param newnum
	 * @throws RemoteException
	 */
	void setThisGamePlayersNumber(int newnum) throws RemoteException;
	
	/**
	 * Increment the current players id and return the new id for the new client
	 * @return
	 * @throws RemoteException
	 */
	int getNewPlayerId() throws RemoteException;
	
	/**
	 * Set the interface of the game
	 * @param game
	 * @throws RemoteException
	 */
	void setGameInterface(Game game) throws RemoteException;
	
	////////////////////////////////////////////////////////////////////
	//GUI MANAGE GETTERS
	
	/**
	 * Get a new region busy
	 * @return
	 * @throws RemoteException
	 */
	List<int[]> getGuiRegionBusy() throws RemoteException;
	
	/**
	 * Get a new shepherd location for the player
	 * @return
	 * @throws RemoteException
	 */
	List<List<Integer>> getGuiPlayersShepherdLoc() throws RemoteException;
	
	/**
	 * Get the number of available dinars for the requested player
	 */
	int getGuiPlayerDinar(int player) throws RemoteException;
	
	/**
	 * Get the number of the initial card for the requested player
	 * @param player
	 * @return
	 * @throws RemoteException
	 */
	int getGuiPlayerInitialCard(int player) throws RemoteException;
	
	/**
	 * Get the number of the standard cards for the requested player
	 * @param player
	 * @return
	 * @throws RemoteException
	 */
	List<List<String>> getGuiPlayerStandardCards(int player) throws RemoteException;
	
	/**
	 * Get the location of the shepherd
	 * @return
	 * @throws RemoteException
	 */
	List<List<Integer>> getGuiShepherdLoc() throws RemoteException;
	
	/**
	 * Get the number of the remaining standard enclosure
	 * @return
	 * @throws RemoteException
	 */
	int getGuiStandardEnclosures() throws RemoteException;
	
	/**
	 * Get the number of the remaining final enclosure
	 * @return
	 * @throws RemoteException
	 */
	int getGuiFinalEnclosures() throws RemoteException;
	
	/**
	 * Get the status of occupation of all positions
	 * @return
	 * @throws RemoteException
	 */
	List<Integer> getGuiPositionEnclosureBusy() throws RemoteException;
	
	/**
	 * Get the number of the last dice
	 * @return
	 * @throws RemoteException
	 */
	int getGuiLastDiceNum() throws RemoteException;
	
	/**
	 * Get the number of the current player
	 * @return
	 * @throws RemoteException
	 */
	int getGuiCurrentPlayerNumber() throws RemoteException;
	
}
