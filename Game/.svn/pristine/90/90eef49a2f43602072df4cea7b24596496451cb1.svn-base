package game.client.view;

import game.client.Manage;
import game.client.view.gui.GuiInitializer;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 * LET THE CLIENT CHOOSING CONNECTION MODE
 * 	-RMI
 * 	-SOCKET
 * with a window on screen
 * 
 * @author Leonardo
 *
 */
public class ConnectionModeSelection implements Runnable {
	
	//frame
	private JFrame connFrame;
	//rmi rectangle area
	private int areaAX = 50;
	private int areaAY = 190;
	private int areaDX = 200;
	private int areaDY = 260;
	//socket rectangle area
	private int translationX = 270;
	private int translationY = 0;
	
	//frame
	private JFrame consoleFrame;
	//console rectangle area
	private int areaSX = 20;
	private int areaSY = 110;
	private int areaTX = 110;
	private int areaTY = 135;
	//gui rectangle area
	private int translationZX = 140;
	private int translationZY = 0;
	//background
	private JLabel backgroundLblConsole;
	
	//all windows title
	private String titleFrame = "Sheepland | Group 14 | Turchi - Rosolia";
	
	/**
	 * GUI REQUEST
	 * FOR CONNECTION MODE and CONSOLE/GUI INPUT
	 * @return
	 */
	public void run() {
		
		//FOR RMI AND SOCKET CONNECTION MODE
		try{
			
			JLabel backgroundLblConn;
			JLayeredPane layeredPaneConn;
			
			connFrame = new JFrame();
			
			//window definition
			connFrame.setResizable(false);
			connFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			connFrame.setTitle(titleFrame);
			connFrame.setSize(548, 320 + connFrame.getInsets().top);
			
			//layers			
			layeredPaneConn = new JLayeredPane();
			connFrame.setContentPane(layeredPaneConn);
			
			//background
			backgroundLblConn = new JLabel(new ImageIcon(ImageIO.read(new File("./resources/img/connection_selection.gif"))));
			backgroundLblConn.setBounds(0, 0, 542, 291);
			connFrame.add(backgroundLblConn);
			layeredPaneConn.setLayer(backgroundLblConn, 5);
			
			//listener
			layeredPaneConn.addMouseListener(new ConnectionModeSelectionListener(this, "connection"));
			
			//show
			connFrame.setVisible(true);
			
		}catch(Exception e){
			ClientLogger.exceptionClientLogger("impossible to open connection mode selection window", e);
		}
		
		//FOR CONSOLE OR GUI INPUT SELECTION
		try{
			
			JLayeredPane layeredPaneConsole;
			
			consoleFrame = new JFrame();
			
			//window definition
			consoleFrame.setResizable(false);
			consoleFrame.setTitle(titleFrame);
			consoleFrame.setSize(284, 187 + consoleFrame.getInsets().top);
			
			//layers			
			layeredPaneConsole = new JLayeredPane();
			consoleFrame.setContentPane(layeredPaneConsole);
			
			//background
			backgroundLblConsole = new JLabel(new ImageIcon(ImageIO.read(new File("./resources/img/console_selection_1.gif"))));
			backgroundLblConsole.setBounds(0, 0, 278, 158);
			consoleFrame.add(backgroundLblConsole);
			layeredPaneConsole.setLayer(backgroundLblConsole, 5);
			
			//listener
			layeredPaneConsole.addMouseListener(new ConnectionModeSelectionListener(this, "input"));
			
			//show
			consoleFrame.setVisible(true);
			
		}catch(Exception e){
			ClientLogger.exceptionClientLogger("impossible to open input mode selection window ", e);
		}
		
	}
	

	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//EXECUTORS
	
	/**
	 * Recognize input and start rmi or socket mode
	 * @param x
	 * @param y
	 */
	public void setCoordsConnection(int x, int y){
		
		int req = 0;
		
		//check coords 
		if(
			(x >= areaAX && x <= areaDX)
			&&
			(y >= areaAY && y <= areaDY)
			){
			
			//if click is in rmi area, set 1
			req = 1;
			
		}else if(
			(x >= areaAX+translationX && x <= areaDX+translationX)
			&&
			(y >= areaAY+translationY && y <= areaDY+translationY)
			){
			
			//if click is in socket area, set 2
			req = 2;
				
		}else{
			req = 0;
		}
		
		//SEND COMMAND MODE
		if(req!=0){
			
			//hide window
			connFrame.setVisible(false);
			
			//set to manage		
			Manage.setMode(req);
			
		}
	}
	
	/**
	 * Recognize input and set true or false the gui mode (or console mode)
	 * @param x
	 * @param y
	 */
	public void setCoordsInput(int x, int y){
		
		int req = 0;
		
		//check coords 
		if(
			(x >= areaSX && x <= areaTX)
			&&
			(y >= areaSY && y <= areaTY)
			){
			
			//if click is in CONSOLE area, set 2
			//SET VARIABLE	
			GuiInitializer.setIsConsoleInput(true);
			req = 2;
			
		}else if(
			(x >= areaSX+translationZX && x <= areaTX+translationZX)
			&&
			(y >= areaSY+translationZY && y <= areaTY+translationZY)
			){
			
			//if click is in GUI area, set 1
			//SET VARIABLE	
			GuiInitializer.setIsConsoleInput(false);
			req = 1;
				
		}else{
			req = 0;
		}
		
		//SEND COMMAND MODE
		if(req!=0){
											
			//set image of selected mode
			try {
				backgroundLblConsole.setIcon(new ImageIcon(ImageIO.read(new File("./resources/img/console_selection_" + Integer.toString(req) + ".gif"))));
			} catch (IOException e) {
				//hide window
				consoleFrame.setVisible(false);
				ClientLogger.exceptionClientLogger("impossible change img in input mode selection window ", e);
			}
			
		}
	}
	
}
