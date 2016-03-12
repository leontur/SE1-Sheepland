package game.server.model;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import game.server.interfaces.DatabaseInterface;
import game.server.model.StandardCard;

import org.junit.Test;

/**
 * MAIN STANDARDCARD-TEST CLASS
 * 
 * @author Dario
 */

public class StandardCardTest {
	
	//CLASS VARS
	public StandardCard tester = new StandardCard();

	/**
	 * Check the total number of cards 
	 * @throws RemoteException 
	 */
	@Test
	public void getTotCardPileNumTest(){
		DatabaseInterface db = new Database();
		assertEquals(tester.getTotCardPileNum(), db.getStandardPlotCardsNum());
	}
	
	/**
	 * Check the current card value
	 * @return 
	 */
	@Test
	public void getCardValueTest(){
		int testInt = 1;
		tester.setCardValue(testInt);
		assertEquals(tester.getCardValue(), testInt);
	}
	
	@Test
	public void setCardValuetest1(){
		getCardValueTest();
	}
	
	/**
	 * Check the current card identifier
	 * @return 
	 */
	@Test 
	public void getCardIdentifierTest(){
		int testInt1= 1;
		tester.setCardIdentifier(testInt1);
		assertEquals(tester.getCardIdentifier(),testInt1);
	}
	
	@Test
	public void setCardIdentifierTest1(){
		getCardIdentifierTest();
	}
	
	/**
	 * Constructor test class
	 * @throws RemoteException 
	 */
	@Test 
	public void ConstructorTest(){
		StandardCard test = new StandardCard();
		if(test.getObjType() == "StandardCard" && test.getCardIdentifier() == 0 && test.getCardValue() == 0 && test.getInitialCardPlotType() == null){
			assertTrue(true);
		}
	}
	
	/**
	 * Constructor test class
	 * @throws RemoteException 
	 */
	@Test 
	public void ConstructorTestA(){
		String ptp = "test";
		StandardCard test = new StandardCard(ptp, 10, 10);
		if(test.getObjType() == "StandardCard" && test.getCardIdentifier() == 10 && test.getCardValue() == 10 && test.getInitialCardPlotType() == ptp){
			assertTrue(true);
		}
	}
	
	

}
