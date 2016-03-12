package game.client;

import java.util.Scanner;

import game.client.rmi.StartClientRmi;
import game.client.socket.StartClientSocket;
import game.client.view.ClientConsoleGui;
import game.client.view.ClientLogger;
import game.client.view.ConnectionModeSelection;

/**
 * 
 * COMMUNICATION METHOD SELECTION
 * BY USER TO CONNECT TO SERVER
 * 
 * 	-THEN STARTS THE CORRECT CLASS
 * 
 * @author Leonardo
 *
 */
public class Manage {	
	
	private static Thread modeSelectionWindow;
	private static boolean isSocketMode;
	
	/**
	 * DEFAULT CONSTRUCTOR
	 * private for static classes
	 */
	private Manage(){
		
	}
	
	/**
	 * MAIN
	 * ASK TO USER FOR COMMUNICATION MODE
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args){

		//REQUEST FOR CONNECTION MODE
		//TRUE GUI - FALSE CONSOLE
		askConsoleConnectionMode(true);
	}
	
	/**
	 * Launch selected communication
	 */
	private static void launchMode(int commMode) throws Exception{
		
		//SHOW GUI CONSOLE
		Thread cci = new Thread(new ClientConsoleGui()); 
		cci.start();
		
		//MODE INVOCATION
		if(commMode==1){
			
			//set mode
			isSocketMode = false;
			
			try{
				
				//START CONNECTION AS **RMI**
				StartClientRmi startRmi = new StartClientRmi();
				//try to open connection
				startRmi.runClientRmi();
				
			}catch(Exception e){
				//generic error
				ClientLogger.exceptionClientLogger("# Main client thread connection error (rmi), it is possible that the server was unavailable #", e);
			}
			
		}else if(commMode==2){
			
			//set mode
			isSocketMode = true;
			
			try{
				
				//START CONNECTION AS **SOCKET**
				StartClientSocket startSocket = new StartClientSocket();
				//try to open connection
				startSocket.runClientSocket();
				
			}catch(Exception e){
				//generic error
				ClientLogger.exceptionClientLogger("# Main client thread connection error (socket), it is possible that the server was unavailable #", e);
			}
			
		}else{
			//INPUT MISMATCH
			//NO CONNECTION OR GAME HAS TO START
			Thread.currentThread().interrupt();
		}
	}
	
	/**
	 * Set a new connection mode input
	 * @param i
	 */
	public static void setMode(int i){
		try {
			
			//stop window thread
			modeSelectionWindow.interrupt();
			
			//launch with parameter
			launchMode(i);
			
		} catch (Exception e) {
			ClientLogger.exceptionClientLogger("# Main client thread initialization error, unable to launch or keep running the client #", e);
		}
	}
	
	/**
	 * LAUNCH REQUEST
	 * FOR CONNECTION MODE
	 * @return
	 */
	private static void askConsoleConnectionMode(boolean guiMode){

		if(guiMode){
			//GUI REQUEST
			modeSelectionWindow = new Thread(new ConnectionModeSelection());
			modeSelectionWindow.start();
		}else{
			//ask to user the communication method to use
			System.out.println(">THIS IS THE SHEEPLAND CLIENT - WELCOME<");
			System.out.println(">SELECT THE CLIENT-SERVER CONNECTION MODE:");
			System.out.println(" 1: rmi | 2: socket");
			@SuppressWarnings("resource")
			int in = new Scanner(System.in).nextInt();
			setMode(in==1 || in==2 ? in : 0);
		}
	}
	
	/**
	 * @return true if the server was activated in socket mode, false if in rmi
	 */
	public static boolean isSocketMode(){
		return isSocketMode;
	}

}