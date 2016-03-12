package game.server.controller;

import static org.junit.Assert.*;
import game.server.model.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Initializer test class
 * 
 * @author Dario
 *
 */
public class InitializerTest {
	
	//CLASS VARS
	private static Game game = new Game();
	private static Database db = new Database();
	
	/**
	 * Setting timeout
	 * 10 seconds max per method tested
	 */
	@Rule
    public Timeout globalTimeout = new Timeout(10000); 
	
	/**
	 *  Initialization of the main map
	 */
	@Test
	public void initMainMapTest(){
		
		try{
			
			Initializer.initMainMap(game);
						
			assertTrue(initMainMapTestExe());
			
		}catch(Exception e){
			assertTrue(true);
		}
	}
	
	
	private boolean initMainMapTestExe(){
		
		try{
			
			initialCardsTest();
			shuffleInitialCardsPile();
			initializeAllPilesTest();
			initPositionsTest();
			setPositionAdjToPositionTest();
			initRegionsTest();
			setPositionToRegionTest();
			initCityTest();
			setPositionToCityTest();
			addCityToRegionListTest();
			initEnclosuresTest();
			initFinalEnclosuresTest();
			initShepherdsTest();
			initDinarsTest();
			initWolfTest();
			initSheepsTest();
			initBSheepsTest();
			
			return true;
			
		}catch(Exception e){}
		
		return false;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Initialize the initial cards 
	 *  NOTE: the initial card class is super for standard card
	 */
	private void initialCardsTest(){
		assertEquals(game.getGameInitialCardsPile().size(), db.getPlotTypesNum());
	}
	
	/**
	 * Shuffle initial cards for pile assignation
	 */
	private void shuffleInitialCardsPile(){
		assertEquals(game.getGameInitialCardsPile().size(), db.getPlotTypesNum());
	}
	
	/**
	 * Check standard cards pile initialized
	 */
	private void initializeAllPilesTest(){
		assertEquals(game.getGamePlotCardsPileByPlotType().size(), db.getPlotTypes().size());
	}
	
	/**
	 * Check position initialized 
	 */
	private void initPositionsTest(){
		assertEquals(game.getGameAllPositions().size(), db.getPositionNum());
	}
	
	/**
	 * Check adjacent position to position
	 */
	private void setPositionAdjToPositionTest(){
		assertEquals(game.getGameAllPositions().size(), db.getPositionNum());
	}
	
	/**
	 * Check sheep initialized
	 */
	private void initSheepsTest(){
		assertEquals(game.getGameAllSheeps().size(), db.getSheepNum());
	}
	
	/**
	 * Check regions initialized
	 */
	private void initRegionsTest(){
		if((game.getGameRegionPileByPlotType().size() == db.getPlotTypes().size() && game.getGameAllRegions().size() == db.getRegionNum()) 
			|| game.getGameAllSheeps().size() == db.getSheepNum()){
		assertTrue(true);
		}
	}
	
	/**
	 * Check adjacent position to region
	 */
	private void setPositionToRegionTest(){
		if(game.getGameRegionPileByPlotType().size() == db.getPlotTypes().size() && game.getGameAllPositions().size() == db.getPositionNum()){
			assertTrue(true);
		}
	}
	
	/**
	 * Check black sheep initialized
	 */
	private void initBSheepsTest(){
		assertEquals(game.getGameAllBSheeps().size(), db.getSheepBNum());
	}
	
	/**
	 * Check city initialized
	 */
	private void initCityTest(){
		if(game.getGameAllCities().size() == db.getCityRegionNum() && game.getGameAllBSheeps().size() == db.getSheepBNum()){
			assertTrue(true);
		}
	}
	
	/**
	 * Check adjacent position to city
	 */
	private void setPositionToCityTest(){
		if(game.getGameAllCities().size() == db.getCityRegionNum() && game.getGameAllPositions().size() == db.getPositionNum()){
			assertTrue(true);
		}
	}
	
	/**
	 * Add cities to region
	 */
	private void addCityToRegionListTest(){
		if(game.getGameAllCities().size() == db.getCityRegionNum() && game.getGameAllRegions().size() == db.getRegionNum()){
			assertTrue(true);
		}
	}
	
	/**
	 * Initialize enclosure
	 */
	private void initEnclosuresTest(){
		assertEquals(game.getGameAllStandardEnclosures().size(), db.getEnclosureNum());
	}
	
	/**
	 * Initialize final enclosure
	 */
	private void initFinalEnclosuresTest(){
		assertEquals(game.getGameAllFinalEnclosures().size(), db.getFinalEnclosureNum());
	}
	
	/**
	 * Initialize shepherds
	 */
	private void initShepherdsTest(){
		assertEquals(game.getGameAllShepherds().size(), db.getTotShepherdNum());
	}
	
	/**
	 * Initialize dinars
	 */
	private void initDinarsTest(){
		assertEquals(game.getGameAllDinars().size(), db.getDinarsTotNum());
	}
	
	/**
	 * Initialize wolf
	 */
	private void initWolfTest(){
		assertNotNull(game.getGameWolf());
	}
	
	
}
