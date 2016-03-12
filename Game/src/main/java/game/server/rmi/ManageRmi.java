package game.server.rmi;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import game.client.interfaces.ClientConsoleInterface;
import game.server.interfaces.*;
import game.server.model.Game;

/**
 * RMI MANAGEMENT CLASS
 * 
 * 
 * @author Leonardo
 *
 */
public class ManageRmi {
	
	private static final int PORT = 1099;
	private static boolean sync = false;

	/**
	 * DEFAULT CONSTRUCTOR
	 * private for static classes
	 */
	private ManageRmi() {
	}
	
	/**
	 * Set interfaces on the RMI registry for successive client's request
	 * note debug: new Game().getGameViewer().showAllObjectsOnRegistry(registry);
	 * @param game
	 * @throws AlreadyBoundException 
	 * @throws RemoteException 
	 * @throws AccessException 
	 */
	public static void publishInterfaces(AddPlayerInterface addPlayerInterface) throws Exception{
		
		//open registry
		Registry registry = LocateRegistry.getRegistry(PORT);
				
		//publishing 
		//PLAYER INIT interface
		registry.bind("addplayer", addPlayerInterface);
	}
	
	
	/**
	 * Get view interfaces from RMI clients
	 * @param gameInterface
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws InterruptedException
	 */
	public static void requestViewerFromClient(int player, Game game) throws Exception{
		
		//get registry
		Registry registry = LocateRegistry.getRegistry(PORT);
		
		//tag
		String tagfromclient = "clientconsole" + Integer.toString(player);
		
		//get
		ClientConsoleInterface viewa = (ClientConsoleInterface) registry.lookup(tagfromclient);
		
		//set
		game.addViewerToList(viewa);
	}
	

	/**
	 * set sync true or false (breaker var)
	 * @param newsync
	 */
	public static void setSync(boolean newsync){
		sync = newsync;
	}
	/**
	 * @return sync (breaker var)
	 */
	public static boolean isSync(){
		return sync;
	}
	
}