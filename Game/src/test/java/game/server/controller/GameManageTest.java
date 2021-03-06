package game.server.controller;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import game.server.AddPlayer;
import game.server.interfaces.AddPlayerInterface;
import game.server.model.Game;
import game.server.view.Console;
import game.server.view.ViewerInterface;

import org.junit.Test;

/**
 * New game manage test class
 * 
 * @author Dario
 *
 */
public class GameManageTest {
	
	
	/**
	 * Check interface of the game 
	 * 
	 * @throws RemoteException
	 */
	@Test
	public void getGameInterfaceTest() throws RemoteException {
		Game rmigame = new Game();
		AddPlayerInterface addPlayerInterface = new AddPlayer(rmigame);
		GameManage tester = new GameManage(rmigame, addPlayerInterface, false, true);
		Game newGameInterface = new Game();
		tester.setGameInterface(newGameInterface);
		assertEquals(tester.getGameInterface(), newGameInterface);
	}
	
	@Test
	public void setGameInterfaceTest() throws RemoteException{
		getGameInterfaceTest();
	}
	
	/**
	 * Check synchronize
	 */
	@Test
	public void isSyncTest(){
		GameManage.setSync(true);
		assertTrue(GameManage.isSync());
		}
	
	@Test
	public void setSyncTest(){
		GameManage.setSync(true);
		assertTrue(GameManage.isSync());
	}
	
	/**
	 * Run on thread
	 * 
	 * @throws RemoteException
	 * @throws InterruptedException
	 */
	@Test
	public void runTest() throws RemoteException, InterruptedException{
		Game rmigame = new Game();
		AddPlayerInterface addPlayerInterface = new AddPlayer(rmigame);
		GameManage ngm = new GameManage(rmigame, addPlayerInterface, false, true);
		Thread manage = new Thread(ngm);
		manage.start();
		manage.join();
		assertTrue(rmigame.isRunningGame());
	}
	
	/**
	 * Run on thread
	 * 
	 * @throws RemoteException
	 */
	@Test
	public void runTestB() throws RemoteException {
		
		Game rmigame = new Game();
		AddPlayerInterface addPlayerInterface = new AddPlayer(rmigame);
		
		GameManage ngm = new GameManage(rmigame, addPlayerInterface, false, true);

		ngm.run();
		
		
		
		assertTrue(rmigame.isRunningGame());
	}
	
	/**
	 * Check the numbers of players
	 */
	@Test
	public void  setNumberOfPlayersTest(){
		
		Game game = new Game();
		ViewerInterface vw = new Console(game);
		boolean isTest = true;
		int playerNumber = 2;
		
		GameManage.setNumberOfPlayers(game, vw, isTest);
		
		assertEquals(playerNumber, game.getPlayersNumber());
		
	}
	
	
}
