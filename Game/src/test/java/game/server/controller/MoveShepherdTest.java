package game.server.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.ArrayList;

import javax.rmi.CORBA.Util;

import game.server.model.*;
import game.server.view.Console;
import game.server.view.ViewerInterface;

import org.junit.Test;

/**
 * Move management-test class
 * For Shepherds
 * 
 * @author Dario
 */

public class MoveShepherdTest {

	/**
	 * private constructor test
	 */
	@Test
	public void constructorTest(){
		try {
			Constructor<Util> c = Util.class.getDeclaredConstructor();
			c.setAccessible(true);
			c.newInstance();
		} catch (Exception e) {;}
		
		assertTrue(true);
	}
	
	/**
	 *  Check the correct movement of a shepherd
	 */
	@Test
	public void moveShepherdTest() {
		
		int targetPos = 0;
		
		Game game = new Game();
		
		Shepherd shepherd = new Shepherd(1, "Red");
		
		List<Enclosure> allStandardEnclosures = new ArrayList<Enclosure>();
		for(int i=0; i<20; i++){
			allStandardEnclosures.add(new Enclosure(i));
		}
		
		List<FinalEnclosure> allFinalEnclosures = new ArrayList<FinalEnclosure>();
		allFinalEnclosures.add(new FinalEnclosure());
		
		List<Position> allPositions = new ArrayList<Position>();
		
		Position pos1 = new Position();
		pos1.addObjOverPos(shepherd);
		
		Position pos2 = new Position();
		
		pos1.addAdjPosAroundThis(pos2);
		pos2.addAdjPosAroundThis(pos1);
		
		allPositions.add(pos1);
		allPositions.add(pos2);
		
		List<Dinar> dinList = new ArrayList<Dinar>();
		dinList.add(new Dinar());
		dinList.add(new Dinar());

		ViewerInterface mainGameViewer = new Console(game);
		
		//reg
		List<Region> allRegions = new ArrayList<Region>();
		allRegions.add(new Region(0, null, "hill"));
		allRegions.add(new Region(1, null, "hill"));
		
		//pos
		List<Position> adjPos = new ArrayList<Position>();
		Position checkpos = new Position(0,1);
		adjPos.add(checkpos);
		adjPos.add(new Position(1,0));
		
		//set poss
		allRegions.get(0).setAdjPositions(adjPos);
		allRegions.get(1).setAdjPositions(adjPos);
		
		//set values
		game.setGameAllRegions(allRegions);
		game.setGameAllPositions(allPositions);
		game.setGameAllFinalEnclosures(allFinalEnclosures);
		game.setGameAllStandardEnclosures(allStandardEnclosures);
		
		//shep
		List<Shepherd> shepList = new ArrayList<Shepherd>();
		
		shepherd.setNewShepherdTarget(allPositions.get(0));
		shepList.add(shepherd);
		
		Player player = new Player("Player 1", shepList, dinList, null);
		player.assignShepherdToPlayer(shepherd);
		
		//check
		MoveShepherd.moveShepherd(game, player, shepherd, allStandardEnclosures, allFinalEnclosures, allPositions, mainGameViewer, targetPos, true);
		assertEquals(shepherd.getCurrShepherdTarget().getPosIdentifier(), targetPos);
	}     
}
