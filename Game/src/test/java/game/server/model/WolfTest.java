package game.server.model;

import static org.junit.Assert.*;


import java.rmi.RemoteException;

import org.junit.Test;

/**
 * MAIN STANDARDCARD-TEST CLASS
 * 
 * @author Dario
 */

public class WolfTest {
	
	//CLASS VARS
	public Wolf tester = new Wolf();

	/**
	 * Control Wolf target
	 * @throws RemoteException
	 */
	@Test
	public void getTargetTest(){
		Region region = new Region();
		tester.setTarget(region);
		assertEquals(tester.getTarget(), region);
	}
	
	@Test
	public void setTargetTest(){
		getTargetTest();
	}

	/**
	 * Control wolf identifier
	 */
	@Test
	public void getWolfIdentifierTest(){
		int testInt = 1;
		tester.setWolfIdentifier(testInt);
		assertEquals(tester.getWolfIdentifier(), testInt);
	}
	
	@Test
	public void setWolfIdentifierTest(){
		getWolfIdentifierTest();
	}
	
	/**
	 * Constructor test
	 * @throws RemoteException 
	 */
	@Test
	public void ConstructorTest(){
		Wolf test = new Wolf();
		if(test.getObjType() == "Wolf" && test.getTarget() == null && test.getWolfIdentifier() == 0){
			assertTrue(true);
		}
	}
	
	/**
	 * Constructor test class
	 * @throws RemoteException 
	 */
	@Test
	public void ConstructorTestA(){
		Region re = new Region();
		Wolf test = new Wolf(10,re);
		if(test.getObjType() == "Wolf" && test.getTarget() == re && test.getWolfIdentifier() == 10){
			assertTrue(true);
		}
	}
}