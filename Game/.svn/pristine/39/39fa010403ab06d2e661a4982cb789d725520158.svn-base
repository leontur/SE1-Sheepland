package game.client.view.gui;

import game.client.view.ClientLogger;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * SELECTABLE OBJECT
 * Add the ability to select an object
 * 
 * @author Leonardo
 */
public class SelectableObject extends MovableObject {

	/**
	 * CLASS SERIAL VERSION ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * CLASS VARS
	 */
	
	/**
	 * ANIMATION VARS
	 */
	//vars
	private ImageIcon selectedImg;
	private boolean isSelected;
	
	//bounds
	//select area
	protected int boundX2i = 0;
	protected int boundY2i = boundX2i;
	protected int boundX2f = 55;
	protected int boundY2f = boundX2f;
	
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * CLASS CONSTRUCTOR
	 */
	public SelectableObject() {

		try{
			selectedImg = new ImageIcon(ImageIO.read(new File(dirPath + "selected.png")));
			selectedImg = new ImageIcon(selectedImg.getImage().getScaledInstance(boundX2f, boundY2f, Image.SCALE_AREA_AVERAGING));
		}catch(IOException e){
			ClientLogger.exceptionClientLogger("impossible to open the resource - IO EXCEPTION ", e);
		}

		setBounds(boundX2i, boundY2i, boundX2f, boundY2f);
		setIcon(selectedImg);
		
		isSelected = false;
	}
	
	/**
	 * Setter
	 * set the current object if is selected by user
	 * @param set
	 */
	public void setIsSelected(boolean set){
		if(isSelectable){
			//set
			this.isSelected = set;
			//request to redraw the object
			repaint();
		}
	}
	
	/**
	 * Getter
	 * @return true or false, if the current object is selected by user
	 */
	public boolean isSelected() {
		return isSelected;
	}
	
	/**
	 * SWING PAINT OVERRIDE
	 */
	@Override
	public void paint(Graphics grph) {
		
		//draw the select png as overlay
		if(isSelected){
			grph.drawImage(selectedImg.getImage(), boundX2i, boundY2i, boundX2f, boundY2f, null);
		}
		
		//draw the rest of the object
		super.paint(grph);
	}
	
}
