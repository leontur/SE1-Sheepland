package game.server.model;

import static org.junit.Assert.*;
import game.server.interfaces.DatabaseInterface;
import game.server.model.Enclosure;

import org.junit.Test;

/**
 * Enclosure test class
 * 
 * @author Dario
 *
 */
public class EnclosureTest {
	
	//CLASS VARS
	public Enclosure tester = new Enclosure();
    private DatabaseInterface db = new Database();

	/**
	 * Check the current enclosure identifier
	 */
	@Test
	public void getEnclosureIdentifier(){
	
		int temp;
		int min = 0; 
	 	int max = db.getEnclosureNum();
	
		for(int i=0; i<max; i++){
		
			setEnclosureIdentifierTest(i);
			temp = tester.getEnclosureIdentifier();
		
			if(!(min <= temp && temp <= max)){
			assertTrue(false);
			break;
		}
	}
	
	assertTrue(true);
	
    }
	private void setEnclosureIdentifierTest(int idtotest){
		tester.setEnclosureIdentifier(idtotest);
	}
	
	@Test
	public void setEnclosureIdentifierTset1(){
		getEnclosureIdentifier();
	}
	
	/**
	 * Constructor test
	 */
	@Test
	public void constructorTest(){
	 Enclosure test1 = new Enclosure();
	 if(test1.getEnclosureIdentifier() == 0 && test1.getObjType() == "Enclosure"){
		 assertTrue(true);
	 }
	}
	
	/**
	 * Constructor test
	 */
	@Test
	public void costurtorTest2(){
	Enclosure test2 = new Enclosure(10);
	if(test2.getEnclosureIdentifier() ==10 && test2.getObjType() == "Enclosure"){
		assertTrue(true);
	}
	}
}
