package game.server.socket;

import game.server.AddPlayer;
import game.server.controller.AsyncTimer;
import game.server.controller.CustomLogger;
import game.server.controller.GameManage;
import game.server.interfaces.AddPlayerInterface;
import game.server.model.Game;
import game.server.socket.WaitPlayer;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;

/**
 * 
 * SERVER SOCKET
 * CONNECTION CLASS
 * 
 * GET PLAYERS AND START GAME IN NEW THREAD
 * 
 * @author Leonardo
 *
 */
public class StartSocket {
	
    //CREATING NEW GAME > MAIN CREATION of a new game object <
  	private Game socketgame = new Game();
  	
  	//players vars
  	private static int socketPlayers;
  	
  	//timeout
  	private static boolean waitForPlayer = true;
  	
  	/**
  	 * TIMEOUT FOR PLAYERS LOGIN
  	 * time given to a new game to search new players
  	 */
  	private static int APP_PLAYERS_LOGIN_TIMEOUT = 60000;

    /**
     * SOCKET CLASS MAIN METHOD 
     * @throws IOException 
     * @throws ClassNotFoundException 
     * @throws InterruptedException 
	 */
    public void runServerSocket() throws Exception {
        
        //ALLOCATING INTERFACES
		AddPlayerInterface addPlayerInterface = null;
		try {
			
			addPlayerInterface = new AddPlayer(socketgame);
			CustomLogger.logConfig("StartSocket", "runServerSocket", "interfaces published");
			
		} catch (RemoteException e) {
			CustomLogger.logEx("StartSocket", "runServerSocket", "addPlayerInterface - remote exception", Level.SEVERE, e);
		}
		
        //start timers
		AsyncTimer timer = new AsyncTimer();
		timer.asyncServiceTimeout(APP_PLAYERS_LOGIN_TIMEOUT, "StartSocket");
		timer.asyncServiceTimeout(APP_PLAYERS_LOGIN_TIMEOUT, "ManageSocket");
		
		//ADDING DEFAULT SERVER LISTENER IN PORT 4000
		ManageSocket.startServerListenerForGamers(socketgame);
		
		//wait time for player to sign in	
		while(waitForPlayer){
			WaitPlayer.waitPlayer(socketgame);
		}
		
		//check found players
		if(socketPlayers>=2){
		//if there are two or more players the game can start

			//SET IN GAME THE SOCKET MODE
			socketgame.setIsSocketMode(true);
			
			//START NEW GAME
			//IN NEW THREAD
			GameManage newGameManage = new GameManage(socketgame, addPlayerInterface, true, false);
			
			Thread newGameManageThread = new Thread(newGameManage);
						
			//starting the thread
			newGameManageThread.start();
			
			//notify
			socketgame.getGameViewer().printToConsole("New game was created and correctly started");
			
			//reset players number for a new game request
			socketPlayers=0;
			
		}else{
			//not enough players
			//reset player number
			socketPlayers=0;
			
			//the waiting is in timeout
			socketgame.getGameViewer().printToConsole("New game was not create due to players logging timeout, please restart server.");
			
			//notify all
			socketgame.getGameViewer().notifyAllClients("New game was not create due to players logging timeout, please restart client.");
			
			//stop server
			Thread.currentThread().interrupt();
		}
	    
    }
    
    /**
     * Set the number of players
     * @param newn
     */
    public static void setPlayersNumber(int newn){
    	socketPlayers=newn;
	}
	
    /**
     * Get the number of players
     * @return
     */
	public static int getPlayersNumber(){
		return socketPlayers;
	}
	
	/**
	 * Set true if wait for player 
	 */
	public static void setWaitForPlayerFalse(){
		waitForPlayer = false;
	}
	
	/**
	 * Timeout if the login of a new player exceeds the established time
	 * @return
	 */
	public static int getAddPlayerLoginTimeout(){
		return APP_PLAYERS_LOGIN_TIMEOUT;
	}
	
	/**
	 * Get started game
	 * @return
	 */
	public Game getStartedGame(){
		return socketgame;
	}
    
	
}
