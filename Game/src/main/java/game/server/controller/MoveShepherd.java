package game.server.controller;

import game.server.interfaces.*;
import game.server.model.*;
import game.server.view.*;

import java.util.List;
import java.util.logging.Level;

/**
 * MOVE MANAGEMENT CLASS
 * For Shepherd
 * 
 * @author Leonardo
 */
public class MoveShepherd {
	
	/**
	 * CLASS VARS
	 */
	private static DatabaseInterface db = new Database();

	/**
	 * DEFAULT CONSTRUCTOR
	 * private for static classes
	 */
	private MoveShepherd(){
		
	}
	
	/**
	 * Move the shepherd to a new position
	 */
	public static void moveShepherd(
			Game game, 
			Player player, 
			Shepherd shepherd, 
			List<Enclosure> allStandardEnclosures, 
			List<FinalEnclosure> allFinalEnclosures, 
			List<Position> allPositions, 
			ViewerInterface mainGameViewer,
			int tgtPos,
			boolean isTest
			){
		
		//var for target
		int targetPos = tgtPos;
		
		//TEST CLAUSE ONLY
		if(!isTest){
			//ask for target
			targetPos = CheckInput.validateSelectedPosition(mainGameViewer);
			targetPos--;
		}
		
		//get positions
		Position prevPos = shepherd.getCurrShepherdTarget();
		Position nextPos = allPositions.get(targetPos);
		
		//notify
		mainGameViewer.notifyCurrentPosition(shepherd.getCurrShepherdTarget().getPosIdentifier() + 1);
		
		//CHECK
		if(CheckMove.isPositionBusy(nextPos)){
			//pos busy
			//set as no move
			game.setShepherdBeenMoved(false);
			//notify
			mainGameViewer.notifyPositionBusy();
		}else{
			//pos free
			//do movement
			doMoveShepherd(game, player, shepherd, prevPos, nextPos, mainGameViewer);
		}
		
		//check if move ok
		if(game.isShepherdBeenMoved()){
			//set enclosure
			subEnclosure(game, shepherd, prevPos, nextPos, allStandardEnclosures, allFinalEnclosures);
		}
		
		//turn update
		turnUpdate(game, prevPos, nextPos, mainGameViewer);
	}
	
	/**
	 * SHEPHERD REAL MOVEMENT EXECUTOR
	 * @param game
	 * @param player
	 * @param shepherd
	 * @param prevPos
	 * @param nextPos
	 * @param mainGameViewer
	 */
	private static void doMoveShepherd(Game game, Player player, Shepherd shepherd, Position prevPos, Position nextPos, ViewerInterface mainGameViewer){
		
		//CHECK
		if(CheckMove.isPositionAdjacent(prevPos, nextPos)){
			//positions are adjacents
			//move shepherd
			shepherd.setNewShepherdTarget(nextPos);
			//notify
			mainGameViewer.notifyMoveCost(0);
			//set as move
			game.setShepherdBeenMoved(true);
		}else{
			//positions are not adj
			try{
				//decrease dinars
				player.subtractDinars(db.getShepherdMovementDinarsCost());
				//notify
				mainGameViewer.notifyMoveCost(db.getShepherdMovementDinarsCost());
				
				//if dinars were enough:
				
				//move shepherd
				shepherd.setNewShepherdTarget(nextPos);
				
				//set as move
				game.setShepherdBeenMoved(true);
				
			}catch(IndexOutOfBoundsException ex){
				//set as no move
				game.setShepherdBeenMoved(false);
				
				//notify
				mainGameViewer.notifyDinarsAreOut();
				mainGameViewer.notifyMoveAbort();
				
				//logger
				CustomLogger.logEx(MoveShepherd.class.getClass().getName(), "doMoveShepherd", "no more dinars: player " + player.getThisPlayerName(), Level.INFO, ex);
			}
		}
	}
	
	//ENCLOSURE SETTING (and decreasing)
	
	/**
	 * Subtract a enclosure
	 * @param game
	 * @param shepherd
	 * @param prevPos
	 * @param nextPos
	 * @param allStandardEnclosures
	 * @param allFinalEnclosures
	 */
	private static void subEnclosure(Game game, Shepherd shepherd, Position prevPos, Position nextPos, List<Enclosure> allStandardEnclosures, List<FinalEnclosure> allFinalEnclosures) {
		
		if(game.getRemainingStandardEnclosuresNum() > 0){
			//if available enclosure 
			//add shepherd obj
			nextPos.addObjOverPos(shepherd);
			//add enclosure and decrease
			prevPos.addObjOverPos(allStandardEnclosures.get(game.getRemainingStandardEnclosuresNum()-1));
			game.setRemainingStandardEnclosuresNum(game.getRemainingStandardEnclosuresNum()-1);
			
		}else{
			//if no more std enclosures: decrease final enclosure and start final turn
			//add shepherd obj
			nextPos.addObjOverPos(shepherd);
			//add enclosure and decrease
			prevPos.addObjOverPos(allFinalEnclosures.get(game.getRemainingFinalEnclosuresNum()-1));
			game.setRemainingFinalEnclosuresNum(game.getRemainingFinalEnclosuresNum()-1);
			//start final turn
			game.setFinalTurn(true);
		}
		
	}

	/**
	 * TURN VARS UPDATE
	 * @param game
	 * @param prevPos
	 * @param nextPos
	 * @param mainGameViewer
	 */
	private static void turnUpdate(Game game, Position prevPos, Position nextPos, ViewerInterface mainGameViewer) {
		if(game.isShepherdBeenMoved()){
			//if a move is done
			//count move
			game.increaseMoveDoneCountByOne();
			//notify
			mainGameViewer.notifyMoveSuccessShepherd(prevPos.getPosIdentifier(), nextPos.getPosIdentifier());
		}else{
			//if the move has aborted
			//no need to increase move count
			mainGameViewer.notifyMoveAbort();
		}
	}
	
	

	
}
