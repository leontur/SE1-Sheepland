package game.client.view.gui;

import javax.swing.JLabel;

/**
 * FATHER CLASS OF A GENERIC INTERACTIVE OBJECT
 * 
 * PROPERTIES
 * 	-view
 * 	-move
 * 	-select
 * 
 * @author Leonardo
 *
 */
public class InteractiveObject extends JLabel {

	/**
	 * CLASS SERIAL VERSION ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * CLASS VARS
	 */
	private boolean isActiveObject;
	protected boolean isSelectable = true;
	protected String dirPath = "./resources/img/";
	
	/**
	 * CLASS CONTSTRUCTOR
	 */
	public InteractiveObject(){
		isActiveObject = true;
	}
	
	/**
	 * CLASS METHODS
	 */
	
	/**
	 * True if the object is active
	 * or false otherwise
	 * @return
	 */
	
	public boolean isActiveObject(){
		return this.isActiveObject;
	}
	
	/**
	 * Set true if the object is active
	 * or set false otherwise
	 * @param status
	 */
	public void setIsActiveObject(boolean status){
		this.isActiveObject = status;
	}
		
	/**
	 * Set true if the object is selectable
	 * or set false otherwise
	 * @param status
	 */
	public void setIsSelectableObject(boolean status){
		this.isSelectable = status;
	}
	
	/**
	 * True if the object is selectable
	 * or false otherwise
	 * @return
	 */
	public boolean isSelectable(){
		return isSelectable;
	}
	
}
