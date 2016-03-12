package game.server.model;

import static org.junit.Assert.*;

import game.server.model.Pawn;

import org.junit.Before;
import org.junit.Test;

/**
 * MAIN PAWN-TEST CLASS
 * 
 * @author Dario
 */

public class PawnTest {

	//CLASS VARS
	public Pawn tester = new Pawn();
	
	/**
	 * Check movement ability
	 */
	@Test
	public void getMovementAbilityTest() {
		assertTrue(tester.getMovementAbility());
	}
	
	/**
	 * Set movement ability
	 */
	@Before
	public void setMovementAbilityTest(){
		tester.setMovementAbility(true);
		assertTrue(tester.getMovementAbility());
	}
}
