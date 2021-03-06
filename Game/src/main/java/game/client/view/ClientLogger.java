package game.client.view;

/**
 * Client Logging class
 * Requested by catch
 * STATIC CLASS
 * 
 * @author Leonardo
 *
 */
public class ClientLogger {

	/**
	 * DEFAULT CONSTRUCTOR
	 * private for static classes
	 */
	private ClientLogger(){
		
	}
	
	/**
	 * LOG AN EXCEPTION IN THE CLIENT
	 */
	public static void exceptionClientLogger(String str, Exception e) {
		System.err.println("CLIENT EXCEPTION: " + str);
		e.printStackTrace();
	}
	
	/**
	 * !SILENT! LOG AN EXCEPTION IN THE CLIENT
	 */
	public static void silentExceptionClientLogger(String str, Exception e) {
		//System.out.println("client catch observed - " + e.getCause());
	}
	
	
}
