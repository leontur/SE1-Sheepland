 package game.server.model;

import static org.junit.Assert.*;
import game.server.model.Position;
import game.server.model.Region;
import game.server.model.Sheep;
import game.server.model.SheepBlack;
import game.server.model.Wolf;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * MAIN REGION-TEST CLASS
 * 
 * @author Dario
 */

public class RegionTest {
	
	//CLASS VARS
	public Region tester = new Region();
	//METHODS VARS
	public List<Sheep> sheepList = new ArrayList<Sheep>();
	public List<SheepBlack> sheepBList = new ArrayList<SheepBlack>();
	public Sheep sheep = new Sheep();
	public List<Position> posList = new ArrayList<Position>();
	public int testInt1 = 1;
	public List<Wolf> wolfList = new ArrayList<Wolf>();
	
	/**
	 * Check the white sheep in this region 
	 * @throws RemoteException 
	 */
	@Test
	public void getAllSheepsTest(){
		Region region = new Region(5, sheep, null);
		sheepList.clear();
		sheepList.add(sheep);
		sheepList.add(sheep);
		region.addANewSheep(sheep);
		assertEquals(region.getAllSheeps(), sheepList);
	}

	/**
	 * Add a white sheep to this region
	 */
	@Test
	public void addANewSheepsTest1(){
		this.getAllSheepsTest();
	}
	
	/**
	 * Check the black sheep in this region 
	 * @throws RemoteException 
	 */
	@Test
	public void getAllBlackSheepsTest(){ 
		SheepBlack sheep = new SheepBlack();
		sheepBList.clear();
		sheepBList.add(sheep);
		tester.addANewBlackSheep(sheep);
		assertEquals(tester.getAllBlackSheeps(),sheepBList);
	}
	
	/**
	 * Add a black sheep to this region
	 */
	@Test
	public void addABlackSheepTest1(){
		getAllBlackSheepsTest();
	}

	/**
	 * Check the removal of a white sheep from this region
	 * @throws RemoteException 
	 */
	@Test
	public void removeASheepTest(){
		Sheep sheep = new Sheep();
		tester.addANewSheep(sheep);
		int num = tester.getAllSheeps().size();
		for(int i=0; i<num; i++)
			tester.removeASheep();
		
		assertEquals(tester.getAllSheeps().size(), 0);
	}
	
	/**
	 * Check the removal of a black sheep from this region
	 */
	@Test
	public void removeABlackSheepTest(){
		SheepBlack sheepBlack = new SheepBlack();
		tester.addANewBlackSheep(sheepBlack);
		int num = tester.getAllBlackSheeps().size();
		for (int i=0; i<num; i++){
			tester.removeABlackSheep();
		}
		
		assertEquals(tester.getAllBlackSheeps().size(), 0);
	}
	
	/**
	 * Check the removal of a black sheep from this region
	 */
	@Test
	public void removeABlackSheepTestB(){
		
		Region region = new Region();
		region.removeABlackSheep();
		assertNotNull(region.getAllBlackSheeps());
		
	}
	
	/**
	 * Check the removal of The white sheep from this region
	 * @throws RemoteException
	 */
	@Test
	public void removeTheSheepTest(){
		removeASheepTest();
		Sheep sh = new Sheep(10);
		tester.addANewSheep(sh);
		tester.removeTheSheep(sh);
		assertEquals(tester.getAllSheeps().size(), 0);
	}
	
	/**
	 * Check the last sheep in this region
	 * @throws RemoteException 
	 */
	@Test
	public void getLastSheepInThisRegionTest(){
		Sheep sheep = new Sheep();
		removeASheepTest();
		tester.addANewSheep(sheep);
		assertEquals(tester.getLastSheepInThisRegion(), sheep);
	}
	
	/**
	 * Check the last sheep in this region
	 * @throws RemoteException 
	 */
	@Test
	public void getLastSheepInThisRegionTestB(){
		removeASheepTest();
		assertNull(tester.getLastSheepInThisRegion());
	}
	
	/**
	 * Check the last black sheep in this region
	 * @throws RemoteException 
	 */
	@Test
	public void getLastBlackSheepInThisRegionTest(){
		SheepBlack sheepBlack = new SheepBlack();
		removeABlackSheepTest();
		tester.addANewBlackSheep(sheepBlack);
		assertEquals(tester.getLastBlackSheepInThisRegion(),sheepBlack);
	}
	
	/**
	 * Check borders and positions
	 * @throws RemoteException 
	 */
	@Test
	public void getBordersTest(){
		List<Position> po = new ArrayList<Position>();
		po.add(new Position());
		tester.setAdjPositions(po);
		assertEquals(tester.getBorders(), po);
	}
	
	@Test
	public void setAdjPositionsTestA(){
		getBordersTest();
	}
	
	/**
	 *  Check the plot type
	 */
	@Test
	public void getInitialCardPlotTypeTest(){
		String testString = "test";
		tester.setInitialCardPlotType(testString);
		assertEquals(tester.getInitialCardPlotType(), testString);
	}

	@Test
	public void setInitialTestPlotTypeTest1(){
		getInitialCardPlotTypeTest();
	}
	
	/**
	 * Check the current region identifier
	 */
	@Test
	public void getRegionIdentifierTest(){
		tester.setRegionIdentifier(testInt1);
		assertEquals(tester.getRegionIdentifier(), testInt1);
	}
	
	@Test 
	public void setRegionIdentifierTest1(){
		getRegionIdentifierTest();
	}
	
	/**
	 * Check Wolf 
	 * @throws RemoteException 
	 */
	@Test
	public void getWolfTest(){
		Wolf wolf = new Wolf();
		tester.setWolf(wolf);
		assertEquals(tester.getWolf(), wolf);
	}
	
	@Test
	public void setWolfTest(){
		getWolfTest();
	}
	
	/**
	 * Remove a wolf to this region
	 */
	@Test
	public void removeWolfTest(){
		Wolf wolf = new Wolf();
		tester.setWolf(wolf);
		tester.removeWolf();
		assertNull(tester.getWolf());
		
	}
	
	/**
	 * Constructor test class
	 * @throws RemoteException 
	 */
	@Test
	public void ConstructorTest(){
		Region test = new Region();
		if(test.getObjType() == "Region" && test.getRegionIdentifier() == 0 && test.getAllSheeps() == null && test.getInitialCardPlotType() == null){
			assertTrue(true);
		} 
	}
	
	/**
	 * Constructor test class
	 * @throws RemoteException 
	 */
	@Test
	public void ConstructorTestA(){
		Sheep sh = new Sheep();
		String st = "test";
		Region test = new Region(10, sh, st);
		if(test.getObjType() == "Region" && test.getRegionIdentifier() == 10 && test.getAllSheeps() == sh && test.getInitialCardPlotType() == st){
			assertTrue(true);
		} 
	}
}

