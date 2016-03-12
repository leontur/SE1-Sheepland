package game.server.model;

import static org.junit.Assert.*;
import game.server.model.Game;
import game.server.model.Position;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

/**
 * MAIN POSITION-TEST CLASS
 * 
 * @author Dario
 */

public class PositionTest {
	
	//CLASS VARS
	public Position tester = new Position();
	//METHODS VARS
	public int testInt = 1;
	public int testInt1 = 1;
	public Game game = new Game();
	public List<Game> gameList = new ArrayList<Game>();
	public Position position = new Position();
	public List<Position> positionList = new ArrayList<Position>();
	
	/**
	 * Check the current position identifier and a new identifier
	 * For this position
	 */
	@Test
	public void getPosIdentifierTest() {
		setPosIdentifierTest();
		assertEquals(tester.getPosIdentifier(), testInt);
	}
	private void setPosIdentifierTest(){
		tester.setPosIdentifier(testInt);
	}
	
	@Test
	public void setPosIdentifierTest1(){
		this.getPosIdentifierTest();
	}
	
	/**
	 * Check the current position's viewer value 
	 * And a new view value for this position
	 */
	@Test 
	public void getPosViewValTest(){
		tester.setPosViewVal(testInt1);
		assertEquals(tester.getPosViewVal(), testInt1);
	}
	
	@Test
	public void setPosViewValTest1(){
		getPosViewValTest();
	}
	
	/**
	 * Check the object over this position and add a new object
	 * Over the position
	 */
	@Test
	public void getAllObjOverPosTest(){
		gameList.clear();
		gameList.add(game);
		tester.addObjOverPos(game);
		assertEquals(tester.getAllObjOverPos(), gameList);
	}
	
	@Test
	public void addObjOverPosTestA(){
		this.getAllObjOverPosTest();
	}
	
	/**
	 * Check the last object over this position
	 */
	@Test
	public void getLastObjOverPos(){
		gameList.clear();
		gameList.add(game);
		tester.addObjOverPos(game);
		assertEquals(tester.getLastObjOverPos(), game);
	}
	
	/**
	 * Check the count for position occupation
	 */
	@Test
	public void posOccupationCountTest(){
		gameList.clear();
		tester.addObjOverPos(game);
		tester.addObjOverPos(game);
		assertEquals(tester.posOccupationCount(), 2);
	}
	
	/**
	 *  Remove the last object from this position
	 */
	@After
	public void subLastObjFromPosTest(){
		
		Position postest = new Position();
		postest.addObjOverPos(new Game());
		postest.subLastObjFromPos();
		assertEquals(postest.getAllObjOverPos().size(), 0);
    }
	
	/**
	 * Get the current adjacent position around this position
	 * And add new adjacent position around this position
	 */
	@Test
	public void getAllAdjPosAroundThisTest(){
		Position position = new Position();
		positionList.clear();
		positionList.add(position);
		tester.addAdjPosAroundThis(position);
		assertEquals(tester.getAllAdjPosAroundThis(),positionList);
	}
	
	@Test
	public void  addAdjPosaroundTest1(){
		getAllAdjPosAroundThisTest();
	}
	
	/**
	 * Check the position occupation status
	 */
	@Test
	public void isObjOverPosTestA(){
		Position test = new Position();
		test.addObjOverPos(new Game());
		assertTrue(test.isObjOverPos());
	}
	
	/**
	 * Check the position occupation status
	 */
	@Test
	public void isObjOverPosTestB(){
		Position test = new Position();
		assertFalse(test.isObjOverPos());
	}
	
	/**
	 * Check if the position is occupied by a standard enclosure 
	 */
	@Test
	public void isPosOccupiedByStdEnclosureTest(){
		Position position = new Position();
		position.addObjOverPos(new Enclosure(0));
		assertTrue(position.isPosOccupiedByStdEnclosure());
	}
	
	/**
	 * Check if the position is occupied by a standard enclosure 
	 */
	@Test
	public void isPosOccupiedByStdEnclosureTestB(){
		Position position = new Position();
		assertFalse(position.isPosOccupiedByStdEnclosure());
	}
	
	/**
	 * Check if the position is occupied by a standard enclosure 
	 */
	@Test
	public void isPosOccupiedByFinEnclosureTest(){
		Position position = new Position();
		position.addObjOverPos(new FinalEnclosure(0));
		assertTrue(position.isPosOccupiedByFinEnclosure());
	}
	
	/**
	 * Check if the position is occupied by a standard enclosure 
	 */
	@Test
	public void isPosOccupiedByFinEnclosureTestB(){
		Position position = new Position();
		assertFalse(position.isPosOccupiedByFinEnclosure());
	}
	
	/**
	 * Constructor test class 
	 */
	@Test
	public void ConstructorTest(){
		Position test = new Position();
		if(test.getObjType() == "Position" && test.getPosViewVal() == 0 && test.getPosIdentifier() == 0){
			assertTrue(true);
		}
	}
	
	/**
	 * Constructor test class
	 */
	@Test
	public void ConstructorTestA(){
		Position test = new Position(10,10);
		if(test.getObjType() == "Position" && test.getPosViewVal() == 10 && test.getPosIdentifier() == 10){
			assertTrue(true);
		}
	}
}



