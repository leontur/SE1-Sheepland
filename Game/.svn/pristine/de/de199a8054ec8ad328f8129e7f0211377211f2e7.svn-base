package game.client.view;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
 
/**
 * SOUND PLAYER CLASS
 * 
 * REQUESTED AS IN NEW THREAD
 * PLAY IN THE CLIENT THE SELECTED SOUND
 * 
 * @author Leonardo
 *
 */
public class SoundPlayer implements Runnable {

	private String trackName;
	private String path = "./resources/wav/";
	private static boolean isLock;
	
	/**
	 * sound executor (only for mp3)
	 * @param selSound, is the id of the track to play
	 */
	public SoundPlayer(String trackName){
		this.trackName = trackName;
	}
	
	/**
	 * Indicates when the music start
	 */
	public void run() {
		try{
			
			if(isLock && "notifica".equals(trackName)){
				Thread.sleep(1500);
				isLock = false;
				return;
			}
			
			//lock notify (to avoid duplications)
			if("notifica".equals(trackName)){
				isLock = true;
			}else{
				isLock = false;
			}
			
			//open file
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(path + trackName + ".wav"));
			
			//execute
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
			
			//unlock notify
			isLock = false;
		
		}catch(Exception e){
			ClientLogger.exceptionClientLogger("Error with playing sound", e);
		}
		
	}

}
