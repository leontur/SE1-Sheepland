package game.server.model;

import static org.junit.Assert.*;
import game.server.model.Dinar;
import game.server.model.InitialCard;
import game.server.model.Player;
import game.server.model.Shepherd;
import game.server.model.StandardCard;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * MAIN PLAYER-TEST CLASS
 * 
 * @author Dario
 */

public class PlayerTest {
	
	//CLASS VARS
	public Player tester = new Player();
	// METHODS VARS
	public String testString = "test";
	public int testInt = 1;
	public int amount;
	public InitialCard initialCard = new InitialCard(); 
	public List<InitialCard> initialCardList = new ArrayList<InitialCard>();
	public StandardCard standardCard = new StandardCard();
	public List<StandardCard> standardCardList = new ArrayList<StandardCard>();
    public Shepherd shepherd = new Shepherd();
    public List<Shepherd> shepherdList = new ArrayList<Shepherd>();
    public List<Dinar> dinarList = new ArrayList<Dinar>();
	public Dinar dinar = new Dinar(); 
	
    /**
     * Check the name of current player
     */
    @Before
		public void getThisPlayerNameTest(){
			setPlayerNameTest();
			assertEquals(tester.getThisPlayerName(), testString);
		}
		private void setPlayerNameTest(){
			tester.setPlayerName(testString);
		}
		
		@Test
		public void setPlayerNameTest1(){
			this.getThisPlayerNameTest();
		}
		
		/**
		 * Check score of this player
		 */
		@Test
		public void getScoreTest(){
			tester.setScore(testInt);
			assertEquals(tester.getScore(), testInt);
		}
		
		@Test
		public void setscoreTest1(){
			this.getScoreTest();
		}
		
		/**
		 * Check the initial terrain card
		 */
		@Test
		public void getInitialPlotTest(){
			tester.setInitialPlot(initialCard);
			assertEquals(tester.getInitialCards(), initialCard);
		}
		
		@Test
		public void setInitialPlotTest1(){
			this.getInitialPlotTest();
		}
		
		/**
		 *  Get the complete list of cards owned by player
		 *  And add a card to owned cards list
		 */
		@Test
		public void getOwnedCardsTest(){
			standardCardList.clear();
			standardCardList.add(standardCard);
			tester.addCardToOwned(standardCard);
			assertEquals(tester.getOwnedCards(), standardCardList);
		}
		
		@Test
		public void addCardToOwnedTest1(){
			this.getOwnedCardsTest();
		}
		
		/**
		 * Check the last card owned by player
		 */
		@Test
		public void getLastOwnedCard(){
		standardCardList.clear();
		standardCardList.add(standardCard);
		tester.addCardToOwned(standardCard);
		assertEquals(tester.getLastOwnedCard(), standardCard);
		}
		
		/**
		 * Check the count terrain cards owned by players
		 */
		@Test
		public void getCountOfOwnedCards(){
			standardCardList.clear();
			standardCardList.add(standardCard);
			standardCardList.add(standardCard);
			tester.addCardToOwned(standardCard);
			tester.addCardToOwned(standardCard);
			assertEquals(tester.getCountOfOwnedCards(), 2);
		}
		
		/**
		 * Assign shepherds to players
		 */
		
		@Test
		public void assignShepherdToPlayerTest1(){
			Player player = new Player(); 
			player.assignShepherdToPlayer(shepherd);
			player.assignShepherdToPlayer(shepherd);
			assertEquals(player.getPlayerShepherdsList().size(), 2);
		}
		
		/**
		 * Check the count of shepherds per player
		 */
		@Test
		public void getShepherdsCountTest(){
			List<Shepherd> shepherd = new ArrayList<Shepherd>();
			Shepherd sh = new Shepherd();
			shepherd.add(sh);
			shepherd.add(sh);
			Player test = new Player(null, shepherd, null, null);
			assertEquals(test.getShephersCount(), 2);
		}
		
		/**
		 * Check and subtract to remaining dinars the passed amount
		 */
		@Test
		public void getRemainingDinarsTest(){
			dinarList.clear();
			dinarList.add(dinar);
			dinarList.add(dinar);
		    Player player = new Player("nomeTest", shepherdList, dinarList, initialCard);
		    int amount = player.getCountOfRemainingDinars();
			player.subtractDinars(amount);
			assertEquals(player.getRemainingDinars().size(), 0);
		}
		
		@Test 
		public void subtractDinarTest(){
			this.getRemainingDinarsTest();
		}
		
		/**
		 * Check count of player's remaining dinars
		 */
		@Test
		public void getCountOfRemainigDinarsTest(){
			List<Dinar> dinar = new ArrayList<Dinar>();
			Dinar din = new Dinar();
			dinar.add(din);
			dinar.add(din);
			Player test = new Player(null, null, dinar, null);
			assertEquals(test.getCountOfRemainingDinars(), 2);
		}
		
		/**
		 * Constructor test class
		 */
		@Before
		public void ConstructorTest(){
			Player test = new Player();
			if(test.getThisPlayerName() == null && test.getPlayerShepherdsList() == null && test.getRemainingDinars() == null && test.getInitialCards() == null){
				assertTrue(true);
			}
		}
		
		/**
		 * Check if player is initialized
		 */
		@Test
		public void isInitializedTest(){
			tester.setIsInitialized(true);
			assertTrue(tester.isInitialized());
			}
		
		@Test
		public void setIsInitializedTest(){
			tester.setIsInitialized(true);
			assertTrue(tester.isInitialized());
		}
		
		/**
		 * Constructor test class
		 */
		@Before
		public void ConstructorTestA(){
			String st = "test";
			List<Shepherd> shepherds = new ArrayList<Shepherd>();
			Shepherd sh = new Shepherd();
			shepherds.add(sh);
			shepherds.add(sh);
			List<Dinar> dinar = new ArrayList<Dinar>();
			Dinar din = new Dinar();
			dinar.add(din);
			dinar.add(din);
			InitialCard ic = new InitialCard();
			Player test = new Player(st, shepherds, dinar, ic);
			if(test.getThisPlayerName() == st && test.getPlayerShepherdsList() == shepherds && test.getRemainingDinars() == dinar && test.getInitialCards() == ic){
				assertTrue(true);
			}
		}
}
		
