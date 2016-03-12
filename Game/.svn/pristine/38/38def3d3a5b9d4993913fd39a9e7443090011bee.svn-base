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
 * SHEEP BLACK
 * OBJECT CLASS
 * 
 * INHERIT ABILITY FROM SUPERCLASSES
 * 
 * @author Leonardo
 */
public class SheepBlack extends SelectableObject {
	
	/**
	 * CLASS SERIAL VERSION ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * CLASS VARS
	 */
	//imgs for each direction
	private ImageIcon imgLeft, imgRight, imgUp;
	private static int sheepNum;
	
	//scaling
	private int boundX = 50;
	private int boundY = boundX;

	/**
	 * CLASS CONTSTRUCTOR
	 */
	public SheepBlack(int id){
		
		//set id
		sheepNum = id;
		
		try{
			//get files
			imgLeft = new ImageIcon(ImageIO.read(new File(dirPath + "bsheepL.png")));
			imgRight = new ImageIcon(ImageIO.read(new File(dirPath + "bsheepR.png")));
			imgUp = new ImageIcon(ImageIO.read(new File(dirPath + "bsheep.png")));
			//scaling
			imgLeft = new ImageIcon(imgLeft.getImage().getScaledInstance(boundX, boundY, Image.SCALE_AREA_AVERAGING));
			imgRight = new ImageIcon(imgRight.getImage().getScaledInstance(boundX, boundY, Image.SCALE_AREA_AVERAGING));
			imgUp = new ImageIcon(imgUp.getImage().getScaledInstance(boundX, boundY, Image.SCALE_AREA_AVERAGING));
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
			setIcon(imgLeft);
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
		int deY = animToPoint.y - getY();
		
		//cases
		if(Math.abs(deX) != Math.abs(deY)){
			if(deX > 0){
				setIcon(imgRight);
			}else{
				setIcon(imgLeft);	
			}
		}else{
			setIcon(imgUp);
		}
	}
	
	/**
	 * Set the number of black sheep 
	 * @param i
	 */
	public void setBSheepNum(int i){
		sheepNum = i; 
	}
	
	/**
	 * Get the number of black sheep
	 * @return
	 */
	public int getBSheepNum(){
		return sheepNum;
	}

}