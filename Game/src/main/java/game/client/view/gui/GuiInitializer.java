package game.client.view.gui;

import game.client.view.ClientLogger;
import game.client.view.gui.model.Sheepland;

import javax.swing.SwingUtilities;

/**
 * GUI INITIALIZER MAIN CLASS
 * CREATED AND REQUESTED AS NEW THREAD
 * 
 * @author Leonardo
 */
public class GuiInitializer implements Runnable {
		
	private static Sheepland sheepland;
	
	/**
	 * Set true if want to enable commands send using console.
	 * Set false for managing the game with the mouse and gui.
	 */
	private static boolean isConsoleInput = false;
	
	/**
	 * Define the total number of players for this current game
	 */
	private static int totalPlayersNum;
	
	/**
	 * Define the connection mode in which the client was started
	 */
	protected static boolean isSocketMode;
	
	/**
	 * CONSTRUCTOR
	 * receive and set the total number of players for this starting game
	 * receive and set the rmi or socket mode
	 */
	public GuiInitializer(int num, boolean mode){
		totalPlayersNum = num;
		isSocketMode = mode;
	}
	
	
	/**
	 * RUNNABLE MAIN METHOD. 
	 * EXECUTE ALL THE GUI IN NEW THREAD.
	 */
	public void run() {

		//Execute the operations involving the GUI on the graphics thread
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initializeIt();
			}
		});
	}
	
	/**
	 * Requested in new thread
	 * Init the main game window and set visible map
	 */
	private void initializeIt(){
		
		try{
			
			//create a new instance
			sheepland = new Sheepland();
			
			//populate
			sheepland.populateAll();
	
			//start moves async services (gif)
			initGifMovements();
			
			//show
			sheepland.setVisible(true);
								
			//start the main auto UPDATES service (every 1,5 sec)
			AutoUpdateEngine aue = new AutoUpdateEngine();
			aue.asyncUpdateService(1500, sheepland, isSocketMode);
			
		}catch (Exception e){
			//log error
			ClientLogger.exceptionClientLogger("An error was occurred in the game's gui, please restart client. \nError: ", e);
		}
	}
	
	/**
	 * Gif auto movements initializer
	 */
	private void initGifMovements(){
		try {
			
			new AutoMoveEngine().asyncMoveService(12000, 200,"Sheepland", "gif4", false, null, sheepland, 80);
			new AutoMoveEngine().asyncMoveService(35000, 800,"Sheepland", "gif1", false, null, sheepland, 200);
			new AutoMoveEngine().asyncMoveService(25000, 1100,"Sheepland", "gif3a", false, null, sheepland, 10);
			
		} catch (InterruptedException e) {
			ClientLogger.silentExceptionClientLogger("impossible to init gifs movements", e);
		}
	}
	
	/**
	 * Get the Sheepland Gui
	 * @return
	 */
	public static Sheepland getSheeplandGui(){
		return sheepland;
	}
	
	/**
	 * True if want to enable commands send using console or
	 * false for managing the game with the mouse and gui.
	 * @return
	 */
	public static boolean isConsoleInput(){
		return isConsoleInput;
	}

	/**
	 * Set true if want to enable commands send using console.
	 * Set false for managing the game with the mouse and gui.
	 * @param mode
	 */
	public static void setIsConsoleInput(boolean mode){
		isConsoleInput = mode;
	}
	
	/**
	 * Get the total number of players for this current game
	 * @return
	 */
	public static int getTotalPlayersNum(){
		return totalPlayersNum;
	}
}