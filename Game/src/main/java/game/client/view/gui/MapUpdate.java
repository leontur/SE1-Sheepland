package game.client.view.gui;

import game.client.interfaces.ClientConsoleInterface;
import game.client.view.ClientLogger;
import game.client.view.gui.model.Enclosure;
import game.client.view.gui.model.FinalEnclosure;
import game.client.view.gui.model.InitialCard;
import game.client.view.gui.model.Sheep;
import game.client.view.gui.model.SheepBlack;
import game.client.view.gui.model.Sheepland;
import game.client.view.gui.model.Shepherd;
import game.client.view.gui.model.StandardCard;
import game.client.view.gui.model.Wolf;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * MOVEMENTS UPDATE CLASS
 * SET IN THE MAP 
 * THE UPDATED POSITION OF ALL OBJECTS
 * 
 * @author Leonardo
 *
 */
public class MapUpdate {

	/**
	 * CLASS VARS
	 */
	private static int animTime1 = 10000;
	private static int animTime2 = 15000;
	private static int animTime3 = 5000;
	private static DestinationDatabase ddb = new DestinationDatabase();
	private static boolean turnCanUpdate = true;
	private static int multipleSheepShift = 15;
	
	/**
	 * CONSTRUCTOR
	 * private for static classes
	 */
	private MapUpdate(){
		
	}
	
	
	/**
	 * UPDATE METHODS
	 */
	
	/**
	 * Check for new positions and move current sheep to the new one
	 * @param regionBusy
	 * @param allSheeps
	 * @param allBlackSheeps
	 * @param wolf
	 * @param sheepland
	 */
	public static void updateRegions(List<int[]> regionBusy, List<Sheep> allSheeps, List<SheepBlack> allBlackSheeps, Wolf wolf, Sheepland sheepland){
		
		//list index: region id
		//[0] sheep (num)
		//[1] black sheep (num)
		//[2] wolf (num)
		
		int i, j;
		int sheepCount=0, bsheepCount=0;
		
		//for each region
		for(i=0; i<regionBusy.size(); i++){
			
			//for each sheep
			for(j=0; j<regionBusy.get(i)[0]; j++, sheepCount++){
				allSheeps.get(sheepCount).moveTo(
						new Point(
								ddb.getRegionsCenterCoords().get(i)[0] + (multipleSheepShift * j), 
								ddb.getRegionsCenterCoords().get(i)[1]
										), animTime1);
			}
			
			//for each black sheep
			for(j=0; j<regionBusy.get(i)[1]; j++, bsheepCount++){
				allBlackSheeps.get(bsheepCount).moveTo(
						new Point(
								ddb.getRegionsCenterCoords().get(i)[0] + (multipleSheepShift * j), 
								ddb.getRegionsCenterCoords().get(i)[1]
										), animTime1);
			}
			
			//if wolf
			if(regionBusy.get(i)[2]!=0){
				wolf.moveTo(
						new Point(
								ddb.getRegionsCenterCoords().get(i)[0] - (multipleSheepShift), 
								ddb.getRegionsCenterCoords().get(i)[1] + 5
										), animTime1);
			}

		}
		
		//check if wolf ate sheep
		if(sheepCount<allSheeps.size()){
			//hide last
			sheepland.remove(allSheeps.get(allSheeps.size()-1));
			//remove last
			allSheeps.remove(allSheeps.size()-1);
		}
		
	}
	
	/**
	 * Check for new positions and move shepherds in new one
	 * @param playerShepherdPos
	 * @param allShepherds
	 * @param sheepland
	 */
	public static void updateSheperds(List<List<Integer>> playerShepherdPos, List<Shepherd> allShepherds, Sheepland sheepland){
		
		//main list index: player id
			//inside list: each entry is a new shepherd position 
			//(cover bough cases of 2 or many players)
		
		int i, j;
		int shepherdCount=0;
		
		//for each player
		for(i=0; i<playerShepherdPos.size(); i++){
			
			//for each shepherd
			for(j=0; j<playerShepherdPos.get(i).size(); j++, shepherdCount++){
				
				allShepherds.get(shepherdCount).moveTo(new Point(
						//x
						ddb.getPositionsCenterCoords().get(
								//get updated pos
								playerShepherdPos.get(i).get(j)
								)[0], 
						//y
						ddb.getPositionsCenterCoords().get(
								//get updated pos
								playerShepherdPos.get(i).get(j)
								)[1]),
						//time
						animTime2
						);
			}
		}
	}
	
	/**
	 * Set new image for counters of dinars
	 * @param playerDinars
	 * @param allDinars
	 * @param sheepland
	 */
	public static void updateDinars(int playerDinars, JLabel num1, JLabel num2, List<Image> numbers, Sheepland sheepland){
		//counters' update
		//playerDinars = available dinars for this player (client)
		int decine = playerDinars/10;
		int unita = playerDinars%10;
		num1.setIcon(new ImageIcon(numbers.get(decine)));
		num2.setIcon(new ImageIcon(numbers.get(unita)));
	}
	
	/**
	 * Remove all own initial cards 
	 * @param playerInitialCard
	 * @param allInitialCards
	 * @param sheepland
	 */
	public static void updateInitialCards(int playerInitialCard, List<InitialCard> allInitialCards, Sheepland sheepland){
		//playerInitialCard = the num(id) of the initial card owned
		//remove all cards but the owned
		for(int i=0; i<allInitialCards.size(); i++){
			if(i!=playerInitialCard){
				sheepland.remove(allInitialCards.get(i));
			}
		}
	}
	
	/**
	 * Shows the standard cards that there are in the personal dashboard
	 * @param playerStandardCard
	 * @param allStandardCards
	 * @param sheepland
	 */
	public static void updateStandardCards(List<List<String>> playerStandardCard, List<StandardCard> allStandardCards, Sheepland sheepland){
		//playerStandardCard = list of id of owned cards
		//index  0: card value   1: plot type
		
		//remove all cards but owned ones//
		
		//hide all cards
		for(StandardCard sc : allStandardCards){
			sc.setVisible(false);
		}

		List<StandardCard> saved = new ArrayList<StandardCard>();
		
		//cross check
		for(StandardCard sc : allStandardCards){
			
			for(List<String> rem : playerStandardCard){
				
				String localCardValue = Integer.toString(sc.getCardValue());
				String localCardType = sc.getCardType();
				
				String remoteCardValue = rem.get(0);
				String remoteCardType = rem.get(1);
				
				//if match
				if(remoteCardValue.equals(localCardValue) && remoteCardType.equals(localCardType)){
					
					//the card is owned (save from hide)
					saved.add(sc);
				}
			}
		}
		
		//show only owned cards
		for(StandardCard sc : saved){
			sc.setVisible(true);
		}
	}
	
	/**
	 * Set new image for counters of standard enclosures
	 * @param standardEnclosures
	 * @param num3
	 * @param num4
	 * @param numbers
	 * @param sheepland
	 */
	public static void updateStandardEnclosures(int standardEnclosures, JLabel num3, JLabel num4, List<Image> numbers, Sheepland sheepland){
		//counters' update
		//standardEnclosures = available std enclosures for game
		int decine = standardEnclosures/10;
		int unita = standardEnclosures%10;
		num3.setIcon(new ImageIcon(numbers.get(decine)));
		num4.setIcon(new ImageIcon(numbers.get(unita)));
	}
	
	/**
	 * Set new image for counters of final enclosures
	 * @param finalEnclosures
	 * @param num5
	 * @param num6
	 * @param numbers
	 * @param sheepland
	 */
	public static void updateFinalEnclosures(int finalEnclosures, JLabel num5, JLabel num6, List<Image> numbers, Sheepland sheepland){
		//counters' update
		//finalEnclosures = available fin enclosures for game
		int decine = finalEnclosures/10;
		int unita = finalEnclosures%10;
		num5.setIcon(new ImageIcon(numbers.get(decine)));
		num6.setIcon(new ImageIcon(numbers.get(unita)));
	}
	
	/**
	 * Set images on positions
	 * @param positionBusy
	 * @param allEnclosures
	 * @param allFinalEnclosures
	 * @param sheepland
	 */
	public static void updatePositions(List<Integer> positionBusy, List<Enclosure> allEnclosures, List<FinalEnclosure> allFinalEnclosures, Sheepland sheepland){
		//UPDATE IMAGES ON POSITIONS
		
		//positionBusy contains the status of occupation of all positions
		//index: is the position id
		//value: 0=free  1:stdencl  2:finencl

		//for each position (remote)
		search:
		for(int i=0; i<positionBusy.size(); i++){
			
			//if position contains a std enclosure
			if(positionBusy.get(i) == 1){
				
				//search if there is already an enclosure for this pos
				for(Enclosure en : allEnclosures){
					if(en.getCoordUse() == i){
						continue search;
					}
				}
				
				//search a free enclosure
				for(Enclosure en : allEnclosures){
				
					if(!en.isInUse()){
						//send this enclosure to pos coords
						en.moveTo(
								new Point(
								ddb.getPositionsCenterCoords().get(i)[0]+15,
								ddb.getPositionsCenterCoords().get(i)[1]+15
								), animTime3);
					
						//set as in use
						en.setIsInUse(true);
						en.setCoordUse(i);
						
						//go to next pos check
						break;
					}
				}
				
			}else if(positionBusy.get(i) == 2){
				
				//search if there is already an enclosure for this pos
				for(FinalEnclosure en : allFinalEnclosures){
					if(en.getCoordUse() == i){
						continue search;
					}
				}
				
				//search a free enclosure
				for(FinalEnclosure en : allFinalEnclosures){
				
					if(!en.isInUse()){
						//send this enclosure to pos coords
						en.moveTo(
								new Point(
								ddb.getPositionsCenterCoords().get(i)[0]+15,
								ddb.getPositionsCenterCoords().get(i)[1]+15
								), animTime3);
					
						//set as in use
						en.setIsInUse(true);
						en.setCoordUse(i);
						
						//go to next pos check
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Set the correct number for the player number
	 * @param newnum
	 * @param numPlayer
	 * @param numbers
	 * @param allShepherds
	 * @param totalPlayersNum
	 * @param currPlayerNum
	 */
	public static void updatePlayerNumber(int newnum, JLabel numPlayer, List<Image> numbers, List<Shepherd> allShepherds, int totalPlayersNum, int currPlayerNum){
		//update counter
		numPlayer.setIcon(new ImageIcon(numbers.get(newnum)));
	}	
	
	/**
	 * Update dice number (for real time update)
	 */
	public static void updateDiceOnTheGo(JLabel numDice, int diceNum, List<Image> numbers){
		//update counter
		numDice.setIcon(new ImageIcon(numbers.get(diceNum)));
	}
	
	/**
	 * Set when you move or when to wait
	 * @param playerNumber
	 * @param currPlayerNum
	 * @param moveDo
	 * @param moveWait
	 */
	public static void updateCurrPlayerNum(int playerNumber, int currPlayerNum, JLabel moveDo, JLabel moveWait){
		//show different banner for the current player move or wait
		if((playerNumber-1) == currPlayerNum){
			moveDo.setVisible(true);
			moveWait.setVisible(false);
		}else{
			moveDo.setVisible(false);
			moveWait.setVisible(true);
		}
	}
	
	/**
	 * SERVER COMMANDS RECEIVER
	 * REQUESTED FROM SERVER (CALLED FROM SHEEPLAND GUI)
	 * 
	 * check and manage the command arrived, and send an appropriate response:
	 * 	-request check
	 *  -move input manage
	 *  -move output manage (to server)
	 * 
	 * @param playerNumber
	 * @param currPlayerNum
	 * @param dice
	 * @param numDice
	 * @param diceNum
	 * @param gif7
	 * @param notify
	 * @param notifyImg
	 * @param numbers
	 * @param sheepland
	 * @param cci
	 */
	public static void updateTurnLogic(
			JLabel dice, 
			JLabel numDice, 
			int diceNum, 
			JLabel gif7,
			JLabel notify,
			List<Image> numbers, 
			Sheepland sheepland,
			ClientConsoleInterface cci,
			String command,
			String options
			){
		
		
		//override
		turnCanUpdate = true;

		//security (lock) thread implementation
		if(turnCanUpdate){
			
            try {
            	
            	//lock
            	turnCanUpdate = false;
            	            	
        		////////////////////////////////////////////////////
            	//RUNNABLE IN NEW THREAD
            	
            	TurnUpdate tu = new TurnUpdate(
					        			dice, 
					        			numDice, 
					        			diceNum, 
					        			gif7, 
					        			notify,
					        			numbers, 
					        			sheepland,
					        			command, 
					        			options
					        			);
            	Thread turnUpd = new Thread(tu);
            	turnUpd.start();
            	turnUpd.join();
            	
            	////////////////////////////////////////////////////
            	
            	//unlock
            	turnCanUpdate = true;
            	
            } catch (Exception e) {
                //handle error
            	ClientLogger.silentExceptionClientLogger("TurnLogicUpdate for Map", e);
            }
            
		}
	}
	
}
