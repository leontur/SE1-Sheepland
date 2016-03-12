package game.client.view.gui.model;

import game.client.view.ClientLogger;
import game.client.view.gui.StaticObject;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * DINAR
 * OBJECT CLASS
 * 
 * INHERIT ABILITY FROM SUPERCLASSES
 * 
 * @author Leonardo
 */
public class Dinar extends StaticObject {
	
	/**
	 * CLASS SERIAL VERSION ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * CLASS VARS
	 */
	private static int dinarNum;
	
	//scaling
	private int boundX = 50;
	private int boundY = boundX;

	/**
	 * CLASS CONTSTRUCTOR
	 */
	public Dinar(int id){
		
		ImageIcon img = null;
		
		//set id
		dinarNum = id;
		
		try{
			//get files
			img = new ImageIcon(ImageIO.read(new File(dirPath + "dinars.png")));
			//scaling
			img = new ImageIcon(img.getImage().getScaledInstance(boundX, boundY, Image.SCALE_AREA_AVERAGING));
		}catch (IOException e){
			ClientLogger.exceptionClientLogger("impossible to open the resource - IO EXCEPTION ", e);
		}
		
		setBounds(0, 0, boundX, boundY);
		setIcon(img);
	}
	public Dinar(){
		this(0);
	}
			
	/**
	 * Set the number of dinars
	 * @param i
	 */
	public void setDinarsNum(int i){
		dinarNum = i; 
	}
	
	/**
	 * Get the number of dinars
	 * @return
	 */
	public int getDinarsNum(){
		return dinarNum;
	}

}