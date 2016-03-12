package game.client.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

/**
 * MOUSE LISTENER FOR 
 * CONNECTION MODE SELECTION
 * 
 * @author Leonardo
 *
 */
public class ConnectionModeSelectionListener implements MouseListener {

	private ConnectionModeSelection cms;
	private String function;
	
	public ConnectionModeSelectionListener(ConnectionModeSelection cms, String function){
		this.cms = cms;
		this.function = function;
	}
	
	/**
	 * Method that indicates when the mouse was clicked
	 */
	public void mouseClicked(MouseEvent e) {
		
		if(SwingUtilities.isLeftMouseButton(e)){
			
			if("connection".equals(function)){
				
				//start check of click
				cms.setCoordsConnection(e.getX(), e.getY());
			
			}else if("input".equals(function)){
								
				//start check of click
				cms.setCoordsInput(e.getX(), e.getY());
		    
			}
		}
	}

	public void mousePressed(MouseEvent e) {
		//not used
	}
	public void mouseEntered(MouseEvent e) {
		//not used
	}
	public void mouseExited(MouseEvent e) {
		//not used
	}
	public void mouseReleased(MouseEvent e) {
		//not used
	}
	
}
