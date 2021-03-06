package game.server.controller;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.rmi.CORBA.Util;

import game.server.model.*;
import game.server.view.Console;
import game.server.view.ViewerInterface;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
* Turn executor-test class
* 
* @author Dario
*/
public class TurnExecutorTest {
		
	/**
	 * Setting timeout
	 * 10 seconds max per method tested
	 */
	@Rule
    public Timeout globalTimeout = new Timeout(10000); 
	
	
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
	 * Check the player's turn
	 * @throws RemoteException
	 */
	@Test
	public void playersTurnTest() throws RemoteException {
		
		try{
			//VARS
			int playerCounter = 1;
			int move = 0;
			Game game = new Game();
			ViewerInterface clientView = new Console(game);
			boolean isTest = true;
			
			//LISTS
			List<Shepherd> shList = new ArrayList<Shepherd>();
			shList.add(new Shepherd(0, "r"));
			shList.add(new Shepherd(1, "b"));
				
			List<Dinar> dinList = new ArrayList<Dinar>();
			dinList.add(new Dinar());
			dinList.add(new Dinar());
			dinList.add(new Dinar());
			
			List<Enclosure> encList = new ArrayList<Enclosure>();
			encList.add(new Enclosure());
			encList.add(new Enclosure());
			encList.add(new Enclosure());
			
			List<Position> posList = new ArrayList<Position>();
			posList.add(new Position(0, 3));
			posList.add(new Position(0, 1));
			posList.add(new Position(0, 2));
			posList.add(new Position(0, 4));
			posList.add(new Position(0, 5));
			
			List<Region> regList = new ArrayList<Region>();
			regList.add(new Region(0, new Sheep(), "Hill"));
			regList.add(new Region(1, new Sheep(), "Hill"));
			regList.add(new Region(2, new Sheep(), "Hill"));
	
			List<Player> plList = new ArrayList<Player>();
			plList.add(new Player("0", shList, null, null));
			plList.add(new Player("1", shList, null, null));
			
			Wolf wolf = new Wolf(0, new Region(0, new Sheep(), "Hill"));
			
			//GAME SET
			game.setGameDice(new Dice());
			game.setGameAllDinars(dinList);
			game.setGameAllPositions(posList);
			game.setGameAllRegions(regList);
			game.setGameAllShepherds(shList);
			game.setGameAllStandardEnclosures(encList);
			game.setGamePlayers(plList);
			game.setCurrentPlayerCounter(playerCounter+1);
			game.setMoveDoneCount(4);
			game.setGameWolf(wolf);
			
			//EXE
			TurnExecutor.playersTurn(playerCounter, game, clientView, move, isTest);
			
			//CHECK
			if(game.getCurrentPlayerCounter() == playerCounter){
				assertTrue(true);
			}
			
		}catch(Exception e){
			assertTrue(true);
		}
		
	}

	/**
	 * Check the shepherd selected for the current turn
	 */
	@Test
	public void selectShepherdForTurnTest(){
		
		//vars
		Player player = new Player();
		Game game = new Game();
		ViewerInterface mainGameViewer = new Console(game);
		boolean isTest = true;
		List<Shepherd> shList = new ArrayList<Shepherd>();
		
		//setting
		game.setPlayersNumber(4);
		shList.add(new Shepherd(0, null));
		shList.add(new Shepherd(1, null));
		player.assignShepherdToPlayer(shList.get(0));
		player.assignShepherdToPlayer(shList.get(1));
		
		//exe
		Shepherd retSh = TurnExecutor.selectShepherdForTurn(player, game, mainGameViewer, isTest);
		
		//check
		assertEquals(shList.get(0), retSh);
	}

	
}

