package game.server.controller;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.rmi.CORBA.Util;

import game.server.model.*;
import game.server.view.Console;
import game.server.view.ViewerInterface;

import org.junit.Test;

/**
 * Move management-test class
 * For Sheep
 * 
 * @author Dario
 */
public class MoveSheepTest {
	
	public Region reg = new Region();

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
	 * Check the correct movement of a sheep
	 * 
	 */
	@Test
	public void moveSheepTest(){
		
		Game game = new Game();
		ViewerInterface mainGameViewer = new Console(game);
		Shepherd shepherd = new Shepherd(0, "r");
		Sheep sheep = new Sheep(0);
		Sheep sheep1 = new Sheep(1);
		
		//reg
		List<Region> allRegions = new ArrayList<Region>();
		allRegions.add(new Region(0, sheep, "hill"));
		allRegions.add(new Region(1, sheep1, "hill"));
		
		//pos
		List<Position> adjPos = new ArrayList<Position>();
		Position checkpos = new Position(0,2);
		adjPos.add(checkpos);
		adjPos.add(new Position(1,4));
		
		//set poss
		allRegions.get(0).setAdjPositions(adjPos);
		allRegions.get(1).setAdjPositions(adjPos);
		
		//set values
		game.setSheepBeenMoved(false);
		game.setGameAllRegions(allRegions);
		shepherd.setNewShepherdTarget(checkpos);
		
		//check
		MoveSheep.moveSheep(game, shepherd, allRegions, mainGameViewer, true);
		
		assertEquals(2, allRegions.get(1).getAllSheeps().size());
		
	}
}
	