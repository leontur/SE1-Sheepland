package game.server.rmi;

import game.server.controller.AsyncTimer;
import game.server.controller.CustomLogger;
import game.server.model.Game;

/**
 * RMI
 * WAIT AND ADD
 * PLAYERS TO GAME
 * 
 * @author Leonardo
 */
public class WaitPlayer {
	
	/**
	 * TIMEOUT FOR THE CLIENT TO LOGIN
	 * time given to each client to reach the server
	 */
	private static int PLAYER_LOGIN_TIMEOUT = 30000;
	
	//wait time between player lookups
	private static int PLAYER_LOGIN_LOOKUP = 1000;
	
	//timeout var
	private static boolean sync;
	
	//counter var
	private static int counter = StartRmi.getAddPlayerLoginTimeout()/1000;
	
	/**
	 * DEFAULT CONSTRUCTOR
	 * private for static classes
	 */
	private WaitPlayer() {
	}
	
	/**
	 * Waiting method
	 * has an internal timer that fire a task every 2 seconds
	 * @throws InterruptedException 
	 */
	public static void waitPlayer(Game game) throws InterruptedException{
		
		//allocate all async timers (ASYNC RUN)
		AsyncTimer timer = new AsyncTimer();
		
		//start timer
		timer.asyncServiceTimeout(PLAYER_LOGIN_TIMEOUT, "WaitPlayerRmi");
	
		//timeout var
		sync = false;
		
		//SEARCH LOOP WITH TIMEOUT CHECK
		while(!sync){
			
			//wait between searches
			try {
				
				//NOTIFY ALL FOR THE COUNTER
				String msg = "rmi | waiting for new players | logged players: " 
							+ StartRmi.getPlayersNumber() + 
							" | starting new game in " + counter--  + " sec..";
				
				game.getGameViewer().printToConsole(msg);
				
				game.getGameViewer().clearConsole(true);
				game.getGameViewer().notifyAllClients(msg);
				
				Thread.sleep(PLAYER_LOGIN_LOOKUP);
				
			} catch (Exception e) {
				game.getGameViewer().printToConsole("Thread lookup error");
				CustomLogger.logInfoEx("WaitPlayer", "waitPlayer", "Thread lookup error", e);
			}
			
			//check for max players number (force break)
			if(StartRmi.getPlayersNumber()>=4){
				StartRmi.setWaitForPlayerFalse();
				break;
			}
		}
		
	}
	
	
	/**
	 * GETTERS AND SETTERS
	 */
	
	/**
	 * Set true if sync
	 * or set false otherwise
	 * @param newsync
	 */
	public static void setSync(boolean newsync){
		sync = newsync;
	}
	
	/**
	 * True if sync
	 * or false otherwise
	 * @return
	 */
	public static boolean isSync(){
		return sync;
	}
}
