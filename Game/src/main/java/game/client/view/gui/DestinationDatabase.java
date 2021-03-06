package game.client.view.gui;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * MAIN OBJECT POSITION 
 * IN MAP
 * 
 * used for
 *  -initial position
 *  -game position
 * 
 * @author Leonardo
 *
 */
public class DestinationDatabase {

	//DASHBOARD
	
	private static final List<int[]> DASHBOARD_DESTINATION = Arrays.asList(
			new int [][] { 
					//0 shepherds
					{280,150}, 	
					//1 dinars
					{30,415}, 	
					//2 enclosures
					{370,340}, 	
					//3 final enclosures
					{370,420}, 	
					//4 initial cards
					{37,510}, 	
					//5 standard cards
					{37,590}, 	
					//6 dice
					{20,320}, 	
					//7 big enclos
					{200,340},	
					//8	big final enclos
					{200,410},	
					//9  number1: dinar
					{100,420},	
					//10 number2: dinar
					{130,420},	
					//11 number1: enclos
					{270,350},	
					//12 number2: enclos
					{300,350},	
					//13 number1: fin enclos
					{270,420},	
					//14 number2: fin enclos
					{300,420},	
					//15 number: dice
					{100,350},	
					//16 move do dash
					{5,60}, 	
					//17 move wait dash
					{5,60},  	
					//18 player number tag
					{250,10},	
					//19 player number counter
					{504,18},	
					//20 notify banner
					{434,5}		
			        }
				);
	
	//REGIONS
	//[row]: region id (0->18) | [0]: COORDX  [1]: COORDY  [2]: RADIUS
	
	/**
	 * List of ordinates indicating where are positioned the sheep in every region
	 */
	private static final List<int[]> REGIONS_CENTER_COORDS = Arrays.asList(
			new int [][] {
					{534,428,50},
					{594,496,50},
					{678,422,50},
					{664,559,50},
					{759,512,50},
					{751,360,50},
					{836,424,50},
					{848,301,50},
					{756,261,50},
					{862,188,50},
					{804,122,50},
					{692,205,50},
					{722,91,50},
					{611,147,50},
					{613,248,50},
					{501,178,50},
					{539,297,50},
					{612,370,50},
					{674,299,50} //sheepsburg
			        }
				);
	
	//POSITIONS
	//[row]: position id (0->41) | [0]: COORDX  [1]: COORDY
	
	/**
	 * List of ordinates indicating every position where the shepherd may linger
	 */
	private static final List<int[]> POSITIONS_CENTER_COORDS = Arrays.asList(
			new int [][] {
					{550,502,12},
					{611,558,12},
					{715,534,12},
					{788,479,12},
					{840,368,12},
					{783,395,12},
					{745,435,12},
					{717,460,12},
					{681,488,12},
					{647,466,12},
					{612,449,12},
					{651,401,12},
					{683,357,12},
					{717,389,12},
					{799,331,12},
					{749,313,12},
					{718,333,12},
					{798,281,12},
					{852,246,12},
					{805,230,12},
					{768,213,12},
					{823,170,12},
					{761,119,12},
					{742,190,12},
					{674,112,12},
					{710,163,12},
					{670,178,12},
					{658,226,12},
					{727,237,12},
					{714,284,12},
					{679,256,12},
					{622,199,12},
					{579,218,12},
					{577,275,12},
					{609,312,12},
					{645,333,12},
					{641,284,12},
					{550,173,12},
					{509,247,12},
					{577,338,12},
					{542,370,12},
					{582,402,12},
			        }
				);
	
	//MOVE TYPES
	//[row]: move type | [0]: COORDX A  [1]: COORDY A  | [3]: COORDX B  [4]: COORDY B  | A is upleft, B is down right
	private static final List<int[]> MOVE_TYPE_COORDS = Arrays.asList(
			new int [][] {
					//shepherd move
					{30,150,100,250},
					//sheep move
					{150,150,230,250},
					//plot buy
					{280,150,360,250}
			        }
				);
		
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * GETTERS
	 */
	
	/**
	 * Return all the dashboard imagine coordinates 
	 * @return
	 */
	public List<int[]> getDashboardDestination(){
		return DASHBOARD_DESTINATION;
	}
	
	/**
	 * Return all regions center image coordinates
	 * @return
	 */
	public List<int[]> getRegionsCenterCoords(){
		return REGIONS_CENTER_COORDS;
	}
	
	/**
	 * Return all positions center image coordinates
	 * @return
	 */
	public List<int[]> getPositionsCenterCoords(){
		return POSITIONS_CENTER_COORDS;
	}
	
	/**
	 * Move type coordinates
	 * @return
	 */
	public List<int[]> getMoveTypeCoords(){
		return MOVE_TYPE_COORDS;
	}
	
}
