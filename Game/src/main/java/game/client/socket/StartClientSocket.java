package game.client.socket;

import game.client.interfaces.ClientConsoleInterface;
import game.client.view.ClientConsole;
import game.client.view.ClientLogger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.rmi.RemoteException;

/**
 * 
 * CLIENT SOCKET
 * CONNECTION CLASS
 * 
 * @author Leonardo
 *
 */
public class StartClientSocket {
	
	//CONNECTION VARS
	//port
	private static final int PORT = 4000;
    //objects init
	private Socket socket = null;
	private ObjectInputStream inStream = null;
    	
	//PLAYER UNIQUE ID AND PORT
	private static int clientId;
	private static int clientPort;
	
	//INTERFACES
	private static ClientConsoleInterface clientConsoleInterface;
	
	/**
	 * CONSTRUCTOR
	 * allocate a new instance of the main input/output client console
	 * @throws RemoteException
	 */
	public StartClientSocket() throws RemoteException{
		clientConsoleInterface = new ClientConsole();
	}
	
	/**
	 * SOCKET CLASS MAIN METHOD
	 * create a socket, input/output stream from server, and log the client into server as new client id
	 * @throws RemoteException 
	 */
	public void runClientSocket() throws RemoteException {
		
		try {

			//notify
			clientConsoleInterface.showOnClient("Connecting to server..");
			
			//log in on the server
            getNewIdAndPort();
			
			//WAIT THE GO FROM SERVER (before starting connection)
			boolean go = waitGoFromServer();
			
			//notify
			clientConsoleInterface.showOnClient("the server has accepted connection on port: " + clientPort);
			
			//start game
			ManageClientSocket.setIsRunningGame(go);
			
			//print stream
			clientConsoleInterface.showOnClient("Connected to server..");
			
			//MAIN CLIENT RUNNING GAME
			//CREATE THE MAIN GAME MANAGE CLASS (in new thread)
			//	clientGame.join(); causes the freeze
			// 	> CLIENT GAME RUNNING HERE <
			ManageClientSocket mcs = new ManageClientSocket();
			Thread clientGame = new Thread(mcs);
			clientGame.start();
			
		} catch (Exception e) {
			
			//log
			clientConsoleInterface.exceptionClientLogger("Remote Exception in StartClientRmi", e);
			
			//notify
			clientConsoleInterface.showOnClient("..disconnected from server.\nGAME OVER");
			
			//break
			ManageClientSocket.setIsRunningGame(false);
			throw new RemoteException();
		}
	}

	/**
	 * Get a new id (sequential) for the current player on default server port
	 * requests a new id from server (on default 4000 port)
	 * @throws IOException 
	 */
	private void getNewIdAndPort() throws IOException {
		
		//stream
		ObjectOutputStream outStream = null;
		
		//get the localhost (automatically detect 127.0.0.1)
		InetAddress host = InetAddress.getLocalHost();
		
		//socket connection creation
        socket = new Socket(host.getHostName(), PORT);
        
        //creating writer to server
        outStream = new ObjectOutputStream(socket.getOutputStream());
        
        //write to server the login request
        outStream.writeObject("#port-login#");

        //read the server response
        inStream = new ObjectInputStream(socket.getInputStream());
        String msg = "";
		try {
			msg = (String) inStream.readObject();
		} catch (ClassNotFoundException e) {
			ClientLogger.silentExceptionClientLogger("not fount: string ", e);
		}
        
        //split of the arrived message for id and port  ->  [id]#[port]
        String[] parts = msg.split("#");
		int id = Integer.parseInt(parts[0]);
		int port = Integer.parseInt(parts[1]); 
                
        //debug
		clientConsoleInterface.showOnClient(">> port from server: " + port + " >> client id: " + id);

        //release resources
		inStream.close();
        outStream.close();
		socket.close();
        
		//save
		clientId = id;
		clientPort = port;
		
		//in case the return is -1 (no more space for this game) notify and shutdown client
		if(clientId==-1){
			clientConsoleInterface.showOnClient("the game is full, please wait for a new game and restart client");
			throw new IOException();
		}
	}
	
	/**
	 * Keep listening on the default port and wait the game start call from server
	 * msg pattern: #start-game#
	 * @throws IOException 
	 * @throws  
	 * 
	 * @throws Exception 
	 */
	private boolean waitGoFromServer() throws IOException {
		
		while(true){
			try{
				
				//get the localhost (automatically detect 127.0.0.1)
				InetAddress host = InetAddress.getLocalHost();
			
				//socket connection creation
		        socket = new Socket(host.getHostName(), PORT + clientId + 1);
		        
		        //read from server
		        inStream = new ObjectInputStream(socket.getInputStream());
		        @SuppressWarnings("unused")
				String msg = (String) inStream.readObject();
		
		        //debug
		        clientConsoleInterface.showOnClient(">> trying to start - client " + clientId);
		
		        //release resources
				inStream.close();
				
				//if connection success (else the try makes the while continue)
				return true;
			
				//next while -> --> --->
				
			} catch (SocketException e){
				ClientLogger.silentExceptionClientLogger("next check for go from server - socket", e);
			} catch (ClassNotFoundException e) {
				ClientLogger.silentExceptionClientLogger("next check for go from server - cast ex", e);
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				ClientLogger.silentExceptionClientLogger("impossible to wait", e);
			}
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	//GETTERS AND SETTERS

	/**
	 * @return the client id for the current game (static)
	 */
	public static int getClientId(){
		return clientId;
	}
	
	/**
	 * set the client id for the current game (static)
	 * @param newid
	 */
	public static void setClientId(int newid){
		clientId = newid;
	}
	
	/**
	 * @return the client port (static for the entire game session)
	 */
	public static int getClientPort(){
		return clientPort;
	}
	
	/**
	 * set the client port (static for the entire game session)
	 * @param newport
	 */
	public static void setClientPort(int newport){
		clientPort = newport;
	}
	
	/**
	 * @return the client console interface for the print or log of the client game
	 */
	public static ClientConsoleInterface getClientConsoleInterface(){
		return clientConsoleInterface;
	}
	
}
