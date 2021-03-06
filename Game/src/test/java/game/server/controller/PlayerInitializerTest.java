package game.server.controller;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import game.server.model.*;

import org.junit.Test;

/**
* Player Initializer-test class
* 
* @author Dario
*/
public class PlayerInitializerTest {

	 /**
	  * Check the numbers of platers
	  */
	@Test
	public void getPlayerNumberTest() {
		Game game = new Game();
		game.setPlayersNumber(0);
		PlayerInitializer.addPlayer(game);
		assertEquals(PlayerInitializer.getPlayersNumber(game), 1);
		}

	/**
	 * Add a new player
	 */
	@Test
	public void addPlayerTest(){
		getPlayerNumberTest();
	}
	
	/**
	 * Initialized player
	 * @throws RemoteException
	 */
	@Test
	public void initPlayerTestA() throws RemoteException{
		
		//case 2 players
		
		//declaration
		Game game = new Game();
		game.setPlayersNumber(2);
		
		//creation
		List<Player> players = new ArrayList<Player>();
		List<Shepherd> allShepherds = new ArrayList<Shepherd>();
		List<InitialCard> initialCardsPile = new ArrayList<InitialCard>();
		List<Position> allPositions = new ArrayList<Position>();
		List<Dinar> allDinars = new ArrayList<Dinar>();
		
		//add
		allShepherds.add(new Shepherd(0, "r"));
		allShepherds.add(new Shepherd(1, "r"));
		allShepherds.add(new Shepherd(2, "b"));
		allShepherds.add(new Shepherd(3, "b"));
		allShepherds.add(new Shepherd(4, "b"));
		allShepherds.add(new Shepherd(6, "b"));
		
		allPositions.add(new Position(0,0));
		allPositions.add(new Position(1,0));
		allPositions.add(new Position(2,0));
		allPositions.add(new Position(3,0));
		
		for(int i=0; i<60; i++){
			allDinars.add(new Dinar(i));
		}
		
		for(int i=0; i<6; i++){
			initialCardsPile.add(new InitialCard("type", i));
		}
		
		//set
		game.setGamePlayers(players);
		game.setGameAllShepherds(allShepherds);
		game.setGameAllPositions(allPositions);
		game.setGameAllDinars(allDinars);
		game.setGameInitialCardsPile(initialCardsPile);
		
		//check vars
		int[] overrideShepherdPos = new int[2];
		overrideShepherdPos[0] = 1;
		overrideShepherdPos[1] = 2;
		
		//init
		PlayerInitializer.initPlayer(0, game, true, overrideShepherdPos);
		
		//check
		assertTrue(game.getGamePlayers().size()>0);	
	}
}
