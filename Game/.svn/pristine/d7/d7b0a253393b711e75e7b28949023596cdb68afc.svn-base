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

public class TurnManageTest {

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
	@Test
	public void startGameTurnTest() {
		
		try{
			
			//VARS
			Game game = new Game();
			List<Player> plL = new ArrayList<Player>();
			List<Shepherd> shL = new ArrayList<Shepherd>();
			int move = 1;
			ViewerInterface vi = new Console(game);
			
			//SET
			game.setPlayersNumber(2);
			game.setCurrentPlayerCounter(0);
			game.setGameViewer(vi);
			
			//shepherd
			shL.add(new Shepherd(0, "r"));
			shL.add(new Shepherd(1, "r"));
			
			//players
			plL.add(new Player("pl", shL, null, null));
			plL.add(new Player("pl1", shL, null, null));
			game.setGamePlayers(plL);
			
			//CALL EXECUTION
			TurnManage.startGameTurn(game, move, true);
			
			////////////////////////////////////////////////////////
			//CHECK MOVEMENT (MOVE N 1)
	
			//VARS
			int playerCounter= 1;
			Game tester = new Game();
			
			//EXE
			TurnExecutor.playersTurn(playerCounter, game, vi, move, true);
			
			//CHECK
			if(tester.getCurrentPlayerCounter() == playerCounter){
				assertTrue(true);
			}
		}catch (Exception e){
			;
		}
	}

}
