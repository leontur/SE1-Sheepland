package game.client.view.gui.model;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import game.client.view.ClientLogger;
import game.client.view.gui.StaticObject;

/**
 * DICE
 * OBJECT CLASS
 * 
 * INHERIT ABILITY FROM SUPERCLASSES
 * 
 * @author Leonardo
 */
public class Dice extends StaticObject {
	
	/**
	 * CLASS SERIAL VERSION ID
	 */
	private static final long serialVersionUID = 1L;
		
	//scaling
	private int boundX = 80;
	private int boundY = boundX;
	
	/**
	 * CLASS CONTSTRUCTOR
	 */
	public Dice(){
		
		ImageIcon img = null;
		
		try{
			//import
			Image imgsrc = Toolkit.getDefaultToolkit().createImage(dirPathGif + "dice2.gif");	
			//image set
			img = new ImageIcon(imgsrc);
		}catch (Exception e){
			ClientLogger.exceptionClientLogger("impossible to open the resource - IO EXCEPTION ", e);
		}
		
		setBounds(0, 0, boundX, boundY);
		setIcon(img);
	}
	
	
}