package game.client.view.gui.model;

import game.client.view.ClientLogger;
import game.client.view.gui.SelectableObject;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * SHEPHERD
 * OBJECT CLASS
 * 
 * INHERIT ABILITY FROM SUPERCLASSES
 * 
 * @author Leonardo
 */
public class Shepherd extends SelectableObject {
	
	/**
	 * CLASS SERIAL VERSION ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * CLASS VARS
	 */
	//imgs for each direction
	private ImageIcon imgLeft, imgRight;
	private int shepherdNum;
	private int playerAssign;
	
	//scaling
	private int boundX = 50;
	private int boundY = 60;

	/**
	 * CLASS CONTSTRUCTOR
	 */
	public Shepherd(int id, int imgColor, int player){
		
		//set id
		shepherdNum = id;
		playerAssign = player;
		
		try{
			//get files
			imgLeft = new ImageIcon(ImageIO.read(new File(dirPath + "shepherd" + Integer.toString(imgColor+1) + "L.png")));
			imgRight = new ImageIcon(ImageIO.read(new File(dirPath + "shepherd" + Integer.toString(imgColor+1) + "R.png")));
			//scaling
			imgLeft = new ImageIcon(imgLeft.getImage().getScaledInstance(boundX, boundY, Image.SCALE_AREA_AVERAGING));
			imgRight = new ImageIcon(imgRight.getImage().getScaledInstance(boundX, boundY, Image.SCALE_AREA_AVERAGING));
		}catch (IOException e){
			ClientLogger.exceptionClientLogger("impossible to open the resource - IO EXCEPTION ", e);
		}
		
		//initial orientation
		setRandomInitialOrientation();
	}
	
	//Random orientation engine
	//each image has 0.25 probability to being chosen
	private void setRandomInitialOrientation(){
		double casualDirection = Math.random();
		if(casualDirection < 0.5) {
			setIcon(imgRight);
		} else {
			setIcon(imgRight);
		}
	}
	
	/**
	 * Override of moveTo (super), to set the oriented image
	 */
	@Override
	public void moveTo(Point animToPoint, int animTimeMillisec) {
		
		//call super method
		super.moveTo(animToPoint, animTimeMillisec);

		//find orientation
		//search difference between start and end point
		int deX = animToPoint.x - getX();

		//case
		if(deX > 0){
			setIcon(imgRight);
		}else{
			setIcon(imgLeft);	
		}
	}
	
	public void setShepherdNum(int i){
		shepherdNum = i; 
	}
	public int getShepherdNum(){
		return shepherdNum;
	}

	public int getPlayerAssign(){
		return playerAssign;
	}
	public void setPlayerAssign(int p){
		playerAssign = p;
	}
}