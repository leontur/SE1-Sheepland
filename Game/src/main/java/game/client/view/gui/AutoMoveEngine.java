package game.client.view.gui;

import game.client.view.ClientLogger;
import game.client.view.gui.model.Sheepland;

import java.rmi.RemoteException;

/**
 * RUNNABLE IN NEW THREAD
 * THIS CLASS
 * MOVE AN OBJECT EVERY TOT MINUTES 
 * BY INCREASING ITS COORDS
 * 
 * @author Leonardo
 *
 */
public class AutoMoveEngine {
	
	/**
	 * Async execution
	 * @throws InterruptedException
	 */
	public void asyncMoveService(final int time, 
			final int delta, 
			final String classname, 
			final String member, 
			final boolean onlycount, 
			final AutoMoveEngine ame, 
			final Object obj, 
			final int speedMilli) throws InterruptedException{
    	
		//task used to create a thread
        Runnable task = new Runnable() {
            public void run() {
                try {
                	
                	//Wait before start
                	Thread.sleep(1000);
                	
                	if(!onlycount){
                		//call engine
                		longMoving(time, classname, member, obj, delta, speedMilli);
                	}else{
                		//call waiter
                		returnOnSleep(time, classname, member, obj, delta, ame, speedMilli);
                	}
                } catch (Exception ex) {
                    //handle error
                	ClientLogger.silentExceptionClientLogger("auto gif move executor", ex);
                }
            }
        };
        
        //run task in new thread
        Thread counter = new Thread(task);
        counter.start();
    }
	
	/////////////////////////////////////////////////////////////////////////////////////////
	//METHODS BELOWS ARE EXECUTED IN TWO DIFFERENT THREADS
	
    //ENGINE 1
	
	/**
 	 * Move an object every few minutes
 	 * through a long moving
 	 *  
	 * @param time
	 * @param classname
	 * @param member
	 * @param obj
	 * @param delta
	 * @param speedMilli
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 * @throws RemoteException
	 */
    private void longMoving(int time, String classname, String member, Object obj, int delta, int speedMilli) throws InterruptedException, ClassNotFoundException, RemoteException{
        
    	//execute on different thread the counter
    	new AutoMoveEngine().asyncMoveService(time, delta, classname, member, true, this, obj, speedMilli);
    	
    	int count = 0;
 		while(true){
 			
 			//SCALABLE CASES (else if..)
 			//coords increment
	        if("Sheepland".equals(classname)){

	        	//call inside method
	        	((Sheepland) obj).setGifMovement(member, true);
	        	
	        	//check if move has to stop
     			if(count>=delta){
     				break;
 				} 
	        	
	        }
 			
 			//wait between movements
 			Thread.sleep(speedMilli);
 			count++;
 		}
 
 		//wait a new call        
    }
    
    //ENGINE 2
    
    /**
     * At the end of time returns on sleep 
     * @param time
     * @param classname
     * @param member
     * @param obj
     * @param delta
     * @param ame
     * @param speedMilli
     * @throws InterruptedException
     */
    private void returnOnSleep(int time, String classname, String member, Object obj, int delta, AutoMoveEngine ame, int speedMilli) throws InterruptedException{
    	Thread.sleep(time);
    	ame.setNewCall(time, classname, member, obj, delta, speedMilli);
    }
    
    //
    //////////////////////////////////////////////////////////////////////////////////////////
	
    /**
     * Set a new call
     * @param time
     * @param classname
     * @param member
     * @param obj
     * @param delta
     * @param speedMilli
     */
    public void setNewCall(int time, String classname, String member, Object obj, int delta, int speedMilli){
    	//recall movement
        try {
			new AutoMoveEngine().asyncMoveService(time, delta, classname, member, false, null, obj, speedMilli);
		} catch (InterruptedException e) {
			ClientLogger.silentExceptionClientLogger("", e);
		}
    }
    
}
