package game.server.controller;

import game.server.interfaces.DatabaseInterface;
import game.server.model.Database;
import game.server.model.Game;
import game.server.model.Player;
import game.server.model.Region;
import game.server.model.Shepherd;
import game.server.model.StandardCard;
import game.server.view.ViewerInterface;

import java.util.List;
import java.util.logging.Level;

/**
 * MOVE MANAGEMENT CLASS
 * For Cards
 * 
 * @author Leonardo
 */
public class MoveCards {
	
	/**
	 * CLASS VARS
	 */
	private static DatabaseInterface db = new Database();
		
	/**
	 * DEFAULT CONSTRUCTOR
	 * private for static classes
	 */
	private MoveCards(){
		
	}
	
	/**
	 * Main terrain card purchase method
	 */
	public static void buyCard(
			Game game, 
			Player player, 
			Shepherd shepherd, 
			List<Region> allRegions, 
			List<List<StandardCard>> plotCardsPileByPlotType, 
			ViewerInterface mainGameViewer,
			int selectedTyp,
			boolean isTest
			){
		
		//retrieve regions around shepherd
		List<Region> regAroundShepherd = CheckMove.findRegionsWithSamePos(allRegions, shepherd.getCurrShepherdTarget());
		
		//get the selection
		int selectedType = selectedTyp;
		
		//TEST CLAUSE ONLY
		if(!isTest){
			//plot types
			selectedType = CheckInput.validateSelectedPlotTypeCard(game, regAroundShepherd, mainGameViewer);
		}
		
		//get name
		String plotType = db.getPlotTypes().get(selectedType);
		
		//call engine
		buyCardByType(game, player, plotType, plotCardsPileByPlotType.get(selectedType), selectedType, mainGameViewer);
		
	}
	
	//PURCHASE ENGINE
	//plotCardsPile is the type of the pile (like desert or grain..) selected by user
	
	/**
	 * Buy a card with different plot type
	 * @param game
	 * @param player
	 * @param plotType
	 * @param plotCardsPile
	 * @param typeindex
	 * @param mainGameViewer
	 */
	private static void buyCardByType(Game game, Player player, String plotType, List<StandardCard> plotCardsPile, int typeindex, ViewerInterface mainGameViewer){
		
		//error handling
		try{
			
			//dinars pay
			player.subtractDinars(game.getAllBoughtTypes()[typeindex]);
			
			//if not finished dinars:
			
			//create a temp card to assign
			StandardCard thiscard = plotCardsPile.get(db.getStandardPlotCardsNum() - game.getAllBoughtTypes()[typeindex] - 1);
			
			//assign card to owned by player
			player.addCardToOwned(thiscard);
			
			//increase bought types
			int[] tempBought = game.getAllBoughtTypes();
			tempBought[typeindex]++;
			game.setAllBoughtTypes(tempBought);
			
			//notify
			mainGameViewer.notifyCardBought(player.getLastOwnedCard().getCardIdentifier(), plotType);
			
			//update move count
			game.increaseMoveDoneCountByOne();
			
			//set as move
			game.setFieldBeenBought(true);
			
		}catch(IndexOutOfBoundsException ex){
			
			//set as no move
			game.setFieldBeenBought(false);
			
			//notify
			mainGameViewer.notifyDinarsAreOut();
			mainGameViewer.notifyMoveAbort();
			
			//logger
			CustomLogger.logEx(MoveCards.class.getClass().getName(), "buyCardByType", "no more dinars: player " + player.getThisPlayerName(), Level.INFO, ex);
		}
		
	}
	
}
