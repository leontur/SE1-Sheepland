package game.client.socket;

import game.client.view.ClientLogger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;


/**
 * CLIENT CLASS
 * OF GUI SOCKET UPDATER
 * 
 * SEND REQUEST TO SERVER AND RECEIVE A RESPONSE
 * 
 * PORTs USED FOR THE GUI ARE THE SAME AS THE CLIENT CONSOLE, BUT SHIFTED OF +1000
 * 
 * NOTE: static class
 * 
 * @author Leonardo
 *
 */
public class SocketClientGuiUpdater {

	
	//CONNECTION VARS
	private static Socket socket;
	private static ObjectOutputStream outStream;
	private static ObjectInputStream inStream;
	
	/**
	 * DEFAULT CONSTRUCTOR
	 * private for static classes
	 */
	private SocketClientGuiUpdater(){
		
	}
	
	/**
	 * Main method for gui update from server
	 * send a request, receive a stream, and
	 * return an object for the map update (if success)
	 * return null (if error occurs)
	 * 
	 * @param command
	 * @param clientPort
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static Object requestUpdate(String command, int clientPort) throws Exception{
		
		//send command
		boolean sent = sender(command, clientPort);
		
		//if send success
		if(sent){
			//receive object (ready to cast)
			Object updateObj = receiver(clientPort);
			return updateObj;
		}
		return null;	
	}
	
	
	/**
	 * send the command of request to server
	 * @return
	 * @throws IOException 
	 */
	private static boolean sender(String command, int clientPort) throws IOException{		
		
		try{
			
			//get the localhost (automatically detect 127.0.0.1)
			InetAddress host = InetAddress.getLocalHost();
			
			//socket connection creation
	        socket = new Socket(host.getHostName(), clientPort);
	        
	        //timeout
            socket.setSoTimeout(1500);
            
			//creating writer to server
	        outStream = new ObjectOutputStream(socket.getOutputStream());
	        
	        //write to server
	        outStream.writeObject(command);
	        
	        //close resources
	        outStream.close();
	        
		}catch(Exception e){
			ClientLogger.silentExceptionClientLogger("unable to send command request to server", e);
			return false;
		}
		return true;
	}
	
	/**
	 * receive the requested object from server
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private static Object receiver(int clientPort) throws IOException, ClassNotFoundException{

		try{
			
			//get the localhost (automatically detect 127.0.0.1)
			InetAddress host = InetAddress.getLocalHost();
			
			//socket connection creation
	        socket = new Socket(host.getHostName(), clientPort);
	        
	        //timeout
            socket.setSoTimeout(1500);
			
			//creating reader from server
	        inStream = new ObjectInputStream(socket.getInputStream());
	        
	        //read from server
	        Object obj = inStream.readObject();
	        
	        //close resources
	        inStream.close();
	        
	        return obj;
	        
		}catch(Exception e){
			ClientLogger.silentExceptionClientLogger("unable to receive the requested object from server", e);
			return null;
		}
			
	}
	
	
}
