package game.client.rmi;

import game.client.interfaces.*;
import game.client.view.*;
import game.server.interfaces.*;

import java.net.SocketException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 
 * CLIENT RMI
 * CONNECTION CLASS
 * 
 * @author Leonardo
 *
 */
public class StartClientRmi {
	
	/**
	 * CLASS VARS
	 */
	
	//PORT NUMBER FOR COMMUNICATION
	private static final int PORT = 1099;
		
	//PLAYER UNIQUE ID
	private static int clientId;
	
	/**
	 * INTERFACES IMPORT
	 */
	private static ClientConsoleInterface clientConsoleInterface;
	private static AddPlayerInterface addPlayerInterface;
	
	public StartClientRmi() throws RemoteException{
		clientConsoleInterface = new ClientConsole();
	}
	
	/**
	 * RMI CLASS MAIN METHOD
	 * create rmi registry, gets remote interfaces from server, add interfaces to server, log in as new client id
	 */
	public void runClientRmi() throws Exception {
		
		try {
			
			//notify
			clientConsoleInterface.showOnClient("Connecting to server..");
			
			//getting registry
			Registry registry = LocateRegistry.getRegistry(PORT);

			//GET ESSENTIAL INTERFACES FROM SERVER
			getInterfaces(registry);
			
			//get player ID
			getNewId();
			
			//SET viewer interface on registry for server remote call
			setInterfaces(registry);
			
			//saving player id
			logIn();
			
			// > RMI GAME RUNNING HERE <
			
		} catch (RemoteException e) {
			clientConsoleInterface.exceptionClientLogger("Remote Exception in StartClientRmi", e);
			throw new SocketException();
		}
		
	}
	
	/**
	 * Get the interfaces from server
	 * @param registry
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws AccessException 
	 */
	private void getInterfaces(Registry registry) throws AccessException, RemoteException, NotBoundException{
			
		//remote player initializer
		AddPlayerInterface addply = (AddPlayerInterface) registry.lookup("addplayer");
		addPlayerInterface = addply;
	}
	
	/**
	 * Set interfaces to server
	 * debug print: clientConsoleInterface.showAllObjectsOnRegistry(registry);
	 * @param registry
	 * @throws AlreadyBoundException 
	 * @throws RemoteException 
	 * @throws AccessException 
	 */
	private void setInterfaces(Registry registry) throws AccessException, RemoteException, AlreadyBoundException{
		
		//input viewer
		ClientConsoleInterface cci = new ClientConsole();
		registry.bind("clientconsole" + Integer.toString(clientId), cci);
	}
	
	/**
	 * Search in the registry if there are servers waiting for players.
	 * 	-publish on registry that a new player have logged in
	 * 	-save the player id as string on the registry
	 * @throws RemoteException 
	 * @throws AccessException 
	 * @throws NotBoundException 
	 * @throws InterruptedException 
	 */
	private void logIn() throws Exception{
		
		//let the server get this client interface
		addPlayerInterface.setClientView();
				
		//requests creation of this new id
		addPlayerInterface.addNewPlayer();
		
		//notify
		clientConsoleInterface.showOnClient("you have successfully logged in the server..");
	}
	
	/**
	 * Get a new id (sequential) for the current player
	 * @throws Exception 
	 */
	private void getNewId() throws Exception{
		//requests a new id from server
		clientId = addPlayerInterface.getNewPlayerId();
		
		//in case the return is -1 (no more space for this game) notify and shutdown client
		if(clientId==-1){
			clientConsoleInterface.showOnClient("the game is full, please wait for a new game and restart client");
			throw new Exception();
		}
	}
	
	/**
	 * GETTERS AND SETTERS
	 */
	
	/**
	 * Get the client identifier
	 * @return
	 */
	public static int getClientId(){
		return clientId;
	}
	
	/**
	 * Set the client identifier
	 * @param newid
	 */
	public static void setClientId(int newid){
		clientId = newid;
	}
	
	/**
	 * Add a player interface
	 * @return
	 */
	public static AddPlayerInterface getRemoteAddPlayerInterface(){
		return addPlayerInterface;
	}
	
	/**
	 * Client console interface
	 * @return
	 */
	public static ClientConsoleInterface getClientConsoleInterface(){
		return clientConsoleInterface;
	}
}
