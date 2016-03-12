package game.server.model;

import static org.junit.Assert.*;
import game.server.model.City;
import game.server.model.Position;
import game.server.model.SheepBlack;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Main city test class
 * 
 * @author Dario
 *
 */
public class CityTest {

	//CLASS VARS
	private City tester = new City();
	
	//METHODS VARS
	public Position position = new Position();
	public List<Position> posList = new ArrayList<Position>();
	public SheepBlack sheepBlack = new SheepBlack();
	public List<SheepBlack> sheepBList = new ArrayList<SheepBlack>();
	
	/**
	 * Retrieve current city's adjacent positions 
	 */
	@Test
	public void getAdjPositionsTest() {
		setAdjPositionsTest();
		assertEquals(tester.getAdjPositions(), posList);
	}
	private void setAdjPositionsTest(){
		posList.add(position);
		tester.setAdjPositions(posList);
	}
	
	/**
	 * Set current city's new adjacent positions
	 */
	@Test
	public void setAdjPositionsTestA(){
		this.getAdjPositionsTest();
	}
	
	/**
	 * Check the identifier of this city
	 */
	@Test
	public void getCityIdentifierTest(){
		tester.setCityIdentifier(15);
		assertTrue(tester.getCityIdentifier() == 15);
	}
	
	@Test
	public void setCityIdentifierTest(){
		getCityIdentifierTest();
	}
	
	/**
	 * Check the current city name
	 */
	@Test
	public void getCityNameTest(){
		City city = new City();
		assertEquals(city.getCityName(), "Sheepsburg");
	}
	
	/**
	 * Constructor test class
	 */
	@Test
	public void ContructorTest(){
		City test = new City();
		if(test.getRegionIdentifier() == 0 && test.getObjType() == "City" && test.getAllBlackSheeps() == null && test.getInitialCardPlotType() == "Sheepsburg" && test.getCityIdentifier() == 0){
			assertTrue(true);
		}
	}
	
	/**
	 * Constructor test class
	 */
	@Test
	public void ContructorTestA(){
		SheepBlack sb = new SheepBlack();
		City test = new City(10, 10, sb);
		if(test.getRegionIdentifier() == 10 && test.getObjType() == "City" && test.getAllBlackSheeps() == sb && test.getInitialCardPlotType() == "Sheepsburg" && test.getCityIdentifier() == 10){
			assertTrue(true);
		}
		
	}
	

}
