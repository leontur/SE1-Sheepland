package game.server.model;

import static org.junit.Assert.*;
import game.server.model.Position;
import game.server.model.Shepherd;

import java.rmi.RemoteException;

import org.junit.Test;

/**
 * MAIN SHEPHERD-TEST CLASS
 * 
 * @author Dario
 */

public class ShepherdTest {
	
	//CLASS VARS
	public Shepherd tester = new Shepherd();
	
	/**
	 * Check this shepherd identifier
	 * @return 
	 */
	@Test
	public void getshepherdIdentifierTest() {
		assertNotNull(tester.getShepherdIdentifier());
		}
	
	/**
	 * Check for object's type
	 * @return
	 */
	@Test
	public void getThisObjTypeTest(){
		assertNotNull(tester.getThisObjType());
	}
	
	/**
	 * Check a new shepherd target
	 * @return 
	 * @throws RemoteException 
	 */
	@Test
	public void getCurrShepherdTargetTest(){
		Position position = new Position();
		tester.setNewShepherdTarget(position);
		assertEquals(tester.getCurrShepherdTarget(), position);
	}
	
	@Test
	public void setNewShepherdTargetTest1(){
		getCurrShepherdTargetTest();
	}
	
	/**
	 * Check the color of shepherds
	 * @return 
	 */
	@Test
	public void getCurrShepherdColor(){
		assertNull(tester.getCurrShepherdColor());
	}
	
	/**
	 * Constructor test class
	 * @throws RemoteException 
	 */
	@Test
	public void ConstructorTest(){
		Shepherd test = new Shepherd();
		if(test.getObjType() == "Shepherd" && test.getShepherdIdentifier() == 0 && test.getCurrShepherdColor() == null && test.getCurrShepherdTarget() == null){
			assertTrue(true);
		}
	}
	
	/**
	 * Constructor test class
	 * @throws RemoteException 
	 */
	@Test
	public void ConstructorTestA(){
		String col = "test";
		Shepherd test = new Shepherd(10,col);
		if(test.getObjType() == "Shepherd" && test.getShepherdIdentifier() == 10 && test.getCurrShepherdColor() == col && test.getCurrShepherdTarget() == null){
			assertTrue(true);
		}
	}
}
