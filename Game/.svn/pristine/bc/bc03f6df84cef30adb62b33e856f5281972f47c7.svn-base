package game.server.model;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import game.server.model.Sheep;

import org.junit.Test;

/**
 * MAIN SHEEP-TEST CLASS
 * 
 * @author Dario
 */

public class SheepTest {
	//CLASS VARS
	public Sheep tester = new Sheep();

	/**
	 * Check sheep identifier
	 */
	@Test
	public void getSheepIdentifierTest() {
		assertNotNull(tester.getSheepIdentifier());
	}
	
	/**
	 * Constructor test class
	 * @throws RemoteException 
	 */
	@Test
	public void ConstructorTest(){
		Sheep test = new Sheep();
		if(test.getObjType() == "Sheep" && test.getSheepIdentifier() == 0){
			assertTrue(true);
		}
	}
	
	/**
	 * Constructor test class
	 * @throws RemoteException 
	 */
	@Test
	public void ConstructorTestA(){
		Sheep test = new Sheep(10);
		if(test.getObjType() == "Sheep" && test.getSheepIdentifier() == 10){
			assertTrue(true);
		}
	}
}
