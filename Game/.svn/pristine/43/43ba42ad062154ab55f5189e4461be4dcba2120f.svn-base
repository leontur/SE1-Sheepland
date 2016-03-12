package game.server.controller;

import java.rmi.RemoteException;
import java.util.List;

import game.server.interfaces.*;
import game.server.model.*;
import game.server.view.ViewerInterface;

/**
 * 
 * SINGLE TURN EXECUTOR CLASS
 * Managed by turn manage
 * 
 * @author Leonardo
 *
 */
public class TurnExecutor {
	
	/**
	 * CLASS VARS
	 * DB INTERFACE
	 */
	private static DatabaseInterface db = new Database();
	
	
	/**
	 * DEFAULT CONSTRUCTOR
	 * private for static classes
	 */
	private TurnExecutor(){
		
	}
	
	/**
	 * START A NEW TURN
	 * check if the passed player is the real player to do turn
	 * and request the turn logic
	 * finally notify other players that he is playing
	 */
	public static void playersTurn(int playerCounter, Game game, ViewerInterface clientView, int move, boolean isTest) throws RemoteException{
		//If id of the request is matching
		if(game.getCurrentPlayerCounter() == playerCounter){
			playTurn(game, clientView, move, isTest);
		}else{
			clientView.notifyIsDoingTurnAnotherPlayer();
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * TURN EXECUTOR ENGINE
	 * START AND MANAGE THE TURN FOR THE PASSED PLAYER
	 * (3 MOVES WITH TIMEOUT AND NOTIFIES)
	 * 
	 * @param game
	 * @param mainGameViewer
	 * @param move for test clause only
	 * @param isTest if true overrides some vars like the console input ones
	 * @throws RemoteException
	 */
	private static void playTurn(Game game, ViewerInterface mainGameViewer, int move, boolean isTest) throws RemoteException{
		
		//GETTING OBJECTS
		List<Position> allPositions = game.getGameAllPositions();
		List<Sheep> allSheeps = game.getGameAllSheeps();
		List<SheepBlack> allBSheeps = game.getGameAllBSheeps();
		List<List<StandardCard>> plotCardsPileByPlotType = game.getGamePlotCardsPileByPlotType();
		List<List<Region>> regionPileByPlotType = game.getGameRegionPileByPlotType();
		List<Region> allRegions = game.getGameAllRegions();
		List<City> allCities = game.getGameAllCities();
		List<Enclosure> allStandardEnclosures = game.getGameAllStandardEnclosures();
		List<FinalEnclosure> allFinalEnclosures = game.getGameAllFinalEnclosures();
		Dice dice = game.getGameDice();
		List<Player> players = game.getGamePlayers();
		Wolf wolf = game.getGameWolf();
				
		//CLASS VARS
		boolean showStatus = false;
		
		//TEMP VARS
		Shepherd shepherdToMove;
		Player currPlayer;
		int currPlayerCounter = game.getCurrentPlayerCounter();
		int selectedMove = 0;
		
		////////////////////////////////////////////////////////
		//START OF A TURN
		
		//GAME AUTO TURN
		//black sheep(s) auto move
		for(SheepBlack bs : allBSheeps){
			MoveBlackSheep.moveBlackSheepAutoJump(game, bs, allRegions, dice, mainGameViewer, false);
		}
		
		//TEST CLAUSE
		if(!isTest){
			//ENVIRONMENT REQUEST
			//ask for show status
			showStatus = mainGameViewer.askForGameStatus();
		}else{
			//TEST
			showStatus = false;
		}
		
		//view game status
		if(showStatus){
			showGameStatus(game, players, regionPileByPlotType, mainGameViewer, allCities, allRegions, wolf);
		}
		
		//PLAYER TURN
		//set current player
		currPlayer = players.get(currPlayerCounter);
		
		//select shepherd
		shepherdToMove = selectShepherdForTurn(currPlayer, game, mainGameViewer, isTest);
		
		//manual movement while
		while(game.getMoveDoneCount() < game.getMaxMoveCount()){
			
			//TEST CLAUSE
			if(!isTest){
				//USER
				//select move
				selectedMove = CheckInput.validateSelectedMove(players, currPlayerCounter, game.getMaxMoveCount()-game.getMoveDoneCount(), mainGameViewer);
			}else{
				//TEST
				//override
				selectedMove = move;
			}
			
			//check the sequence of moves in this turn
			if(CheckMove.moveSequenceCheck(game, selectedMove)){
				//can move only shepherd (due to game rules)
				mainGameViewer.notifyMoveOnlyShepherd();
			}else{
				//can move everything
				
				//recognize move type
				switch(selectedMove){
					//shepherd
					case 1:
						MoveShepherd.moveShepherd(game, currPlayer, shepherdToMove, allStandardEnclosures, allFinalEnclosures, allPositions, mainGameViewer, 0, false);
						break;
						
					//sheep
					case 2:
						//send request for sound execution
						mainGameViewer.playSoundOnClient("pecora");
						MoveSheep.moveSheep(game, shepherdToMove, allRegions, mainGameViewer, false);
						break;
						
					//card	
					case 3:
						MoveCards.buyCard(game, currPlayer, shepherdToMove, allRegions, plotCardsPileByPlotType, mainGameViewer, 0, false);
						break;
						
					//scalable (setted the shepherd move as default to avoid out of bound ex)
					default:
						MoveShepherd.moveShepherd(game, currPlayer, shepherdToMove, allStandardEnclosures, allFinalEnclosures, allPositions, mainGameViewer, 0, false);
						break;
				}
				//end switch
			}
			//end if
		}
		//end while
		//end available turn movements
		
		//send request for sound execution
		mainGameViewer.playSoundOnClient("lupo");
		
		//GAME AUTO TURN
		//wolf auto move
		MoveWolf.moveWolfAutoJump(game, wolf, allRegions, allSheeps, dice, mainGameViewer, false);
		
		//STOP THE WAITING FOR THIS TURN
		TurnManage.setSync(true);
		
		//END OF A TURN
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//EXECUTING METHODS
	
	/**
	 * Select the shepherd to use in the next turn
	 *  -for 2 players variant: ask
	 *  -for all other: auto (first available shepherd)
	 * 
	 * @param player
	 * @param mainGameViewer
	 * @return
	 */
	public static Shepherd selectShepherdForTurn(Player player, Game game, ViewerInterface mainGameViewer, boolean isTest){
		
		//TEST CLAUSE
		if(isTest){
			//return as standard game (first shepherd)
			return player.getPlayerShepherdsList().get(0);
		}
		
		int shepherdNum;
		if(game.getPlayersNumber() == db.getTotPlayerMinNumber()){
			//variant (2 players)
			shepherdNum = CheckInput.validateSelectedShepherd(player.getThisPlayerName(), mainGameViewer);
			return player.getPlayerShepherdsList().get(shepherdNum-1);
		}else{
			//standard game
			return player.getPlayerShepherdsList().get(player.getPlayerShepherdsList().size()-1);
		}
		
	}
	
	/**
	 * Show the current status of the game
	 * 
	 * @param players
	 * @param regionPileByPlotType
	 * @param allBSheeps
	 * @param mainGameViewer
	 */
	private static void showGameStatus(
			Game game,
			List<Player> players, 
			List<List<Region>> regionPileByPlotType, 
			ViewerInterface mainGameViewer, 
			List<City> allCities, 
			List<Region> allRegions,
			Wolf wolf
			){
		
		//show map status
		mainGameViewer.showMapStatus(regionPileByPlotType, game.getAllBoughtTypes(), allCities, wolf);
		//show region status
		mainGameViewer.showRegionStatus(allRegions, allCities, wolf);
		//show players status
		for(Player ply : players){
			mainGameViewer.showPlayerStatus(ply, regionPileByPlotType);
		}
	}

}