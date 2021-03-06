package game.server.model;

import static org.junit.Assert.*;
import game.server.model.Region;
import game.server.model.SheepBlack;

import java.rmi.RemoteException;

import org.junit.Test;

/**
 * MAIN SHEEPBLACK-TEST CLASS
 * 
 * @author Dario
 */

public class SheepBlackTest {
	
	//CLASS VARS
	public SheepBlack tester = new SheepBlack();	

	/**
	 * Check the black sheep target
	 * @throws RemoteException 
	 */
	@Test
	public void getTargetTest(){
		Region region = new Region();
		tester.setTarget(region);
		assertEquals(tester.getTarget(), region);
	 
	}

	@Test
	public void setTargetTest1(){
		getTargetTest();
	}
	
	/**
	 * Constructor test class
	 * @throws RemoteException 
	 */
	@Test
	public void ConstructorTest(){
		SheepBlack test = new SheepBlack();
		if(test.getObjType() == "BlackSheep" && test.getTarget() == null && test.getSheepIdentifier() == 0){
			assertTrue(true);
		}
	}
	
	/**
	 * Constructor test class
	 * @throws RemoteException 
	 */
	@Test
	public void ConstructorTestA(){
		City ci = new City();
		SheepBlack test = new SheepBlack(10,ci);
		if(test.getObjType() == "BlackSheep" && test.getTarget() == ci && test.getSheepIdentifier() == 10){
			assertTrue(true);
		}
		
	}
}



