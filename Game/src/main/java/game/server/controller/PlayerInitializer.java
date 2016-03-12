package game.server.controller;

import game.server.interfaces.*;
import game.server.model.*;
import game.server.view.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class PlayerInitializer {

	/**
	 * CLASS VAR
	 * DB IMPORT
	 */
	private static DatabaseInterface db = new Database();
	
	/**
	 * DEFAULT CONSTRUCTOR
	 * private for static classes
	 */
	private PlayerInitializer(){
		
	}
	
	/**
	 * Add a new player to the game main counter
	 */
	public static void addPlayer(Game game) {
		game.setPlayersNumber(getPlayersNumber(game)+1);
	}

	/**
	 * Return the current total players number
	 */
	public static int getPlayersNumber(Game game) {
		return game.getPlayersNumber();
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * REQUEST FOR NEW GAME
	 * PLAYER NUMBER AND NAMES
	 * FOR ONE PLAYER BY REQUEST
	 * 
	 * @param players
	 * @param allShepherds
	 * @param initialCardsPile
	 * @param allPositions
	 * @param allDinars
	 * @param mainGameViewer
	 * @throws RemoteException 
	 */
	public static void initPlayer(int playerNumber, Game game, boolean isTest, int[] testShepherdPos) throws RemoteException {
		
		//GETTING (server) VIEWER
		ViewerInterface mainGameViewer = game.getGameViewer();
		
		//GETTING OBJECTS FOR INITIALIZATION
		List<Player> players = game.getGamePlayers();
		List<Shepherd> allShepherds = game.getGameAllShepherds();
		List<InitialCard> initialCardsPile = game.getGameInitialCardsPile();
		List<Position> allPositions = game.getGameAllPositions();
		List<Dinar> allDinars = game.getGameAllDinars();
		
		//DECLARING TEMP OBJ FOR METHOD
		Player player;
		List<Shepherd> tempShepherd = new ArrayList<Shepherd>();
		List<Dinar> tempDinar = new ArrayList<Dinar>();
		
		//METHOD VARS
		int playerTot = game.getPlayersNumber();
		int initialDinars;
		int tempDinarCounter = 0;
		String playerName;
		int tempPos;
		
		//set player
		int i = playerNumber;
		
		//TEST CLAUSE
		if(isTest){
			//test mode
			playerName = "test" + Integer.toString(i);
		}else{
			//request name from user
			playerName = CheckInput.validateSelectedPlayerName(i, players, mainGameViewer);
		}
								
		if(playerTot==2){
			//CASE TWO PLAYERS (VARIANT)
			
			//set initial dinars value
			initialDinars = db.getDinarAssignVariantNum();

			//set initial position for two shepherds (j)
			for(int j=1; j<=2; j++){
			
				//TEST CLAUSE
				if(isTest){
					//test mode
					tempPos = testShepherdPos[j-1];
				}else{
					//ask new pos
					tempPos = CheckInput.validateSelectedPlayerShepherdPosition(j, playerName, allPositions, mainGameViewer);
				}
				
				//set position to shepherd
				//first shepherd (1) | second shepherd (1 + 4)
				allShepherds.get( j==1 ? i : i + db.getTotPlayerMaxNumber() ).setNewShepherdTarget(allPositions.get(tempPos-1));
				
				//assign 2 shepherd for each player
				//the second shepherd is distanced by the max player number
				tempShepherd.add(allShepherds.get( j==1 ? i : i + db.getTotPlayerMaxNumber() ));
				
				//set position as occupied by player i
				allPositions.get(tempPos-1).addObjOverPos(allShepherds.get( j==1 ? i : i + db.getTotPlayerMaxNumber() ));

			}
			
			//set initial dinars
			int dinarsAssign = (i+1) * initialDinars;
			tempDinarCounter = i * initialDinars;
			for(int k=tempDinarCounter; k<dinarsAssign; k++, tempDinarCounter++){
				tempDinar.add(allDinars.get(k));
			}
			
			//set the initial plot cards
			InitialCard playerInitialCard = initialCardsPile.get(i);
			
			/**
			 * CREATE AND ADD A NEW PLAYER
			 */
			player = new Player(playerName, tempShepherd, tempDinar, playerInitialCard);
			players.add(player);
			
		}else{
			//CASE >2 PLAYERS
			
			//set initial dinars value
			initialDinars = db.getDinarAssignNum();				
			
			//TEST CLAUSE
			if(isTest){
				//test mode
				tempPos = testShepherdPos[0];
			}else{
				//ask new pos
				tempPos = CheckInput.validateSelectedPlayerShepherdPosition(1, playerName, allPositions, mainGameViewer);
			}
			
			//assign 1 shepherd for each player
			tempShepherd.add(allShepherds.get(i));
			
			//set position to shepherd
			//first shepherd
			allShepherds.get(i).setNewShepherdTarget(allPositions.get(tempPos-1));
			
			//set position as occupied by player i
			allPositions.get(tempPos-1).addObjOverPos(allShepherds.get(i));	
			
			//set initial dinars
			int dinarsAssign = (i+1) * initialDinars;
			tempDinarCounter = i * initialDinars;
			for(int k=tempDinarCounter; k<dinarsAssign; k++, tempDinarCounter++){
				tempDinar.add(allDinars.get(k));
			}			
			
			//set the initial plot cards
			InitialCard playerInitialCard = initialCardsPile.get(i);
			
			/**
			 * CREATE AND ADD A NEW PLAYER
			 */
			player = new Player(playerName, tempShepherd, tempDinar, playerInitialCard);
			players.add(player);
			
		}
	}
	
}
