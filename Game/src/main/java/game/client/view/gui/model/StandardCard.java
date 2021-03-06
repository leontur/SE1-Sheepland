package game.client.view.gui.model;

import game.client.view.ClientLogger;
import game.client.view.gui.StaticObject;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * STANDARD PLOT CARDS
 * OBJECT CLASS
 * 
 * INHERIT ABILITY FROM SUPERCLASSES
 * 
 * @author Leonardo
 */
public class StandardCard extends StaticObject {
	
	/**
	 * CLASS SERIAL VERSION ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * CLASS VARS
	 */
	private int standardCardNum;
	private String plottype;
	
	//scaling
	private int boundX = 50;
	private int boundY = boundX;

	/**
	 * CLASS CONTSTRUCTOR
	 */
	public StandardCard(int id, String type){
		
		ImageIcon img = null;
		
		//set id
		standardCardNum = id;
		
		//set type
		plottype = type;
		
		try{
			//get files
			img = new ImageIcon(ImageIO.read(new File(dirPathCards + type.toLowerCase() + Integer.toString(standardCardNum) + ".gif")));
		}catch (IOException e){
			ClientLogger.exceptionClientLogger("impossible to open the resource - IO EXCEPTION ", e);
		}
		
		setBounds(0, 0, boundX, boundY);
		setIcon(img);
	}
	
	/**
	 * Set the number of standard cards
	 * @param i
	 */
	public void setStandardCardsNum(int i){
		standardCardNum = i; 
	}
	
	/**
	 * Get the number of standard cards
	 * @return
	 */
	public int getStandardCardsNum(){
		return standardCardNum;
	}
	
	/**
	 * get the type of card
	 * @return
	 */
	public String getCardType(){
		return plottype;
	}
	
	/**
	 * Get the card value
	 * @return
	 */
	public int getCardValue(){
		return standardCardNum;
	}

}