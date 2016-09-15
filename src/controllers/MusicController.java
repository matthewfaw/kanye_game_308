package controllers;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The purpose of the MusicController is to allow a user (the game controller)
 * to easily trigger a song to play.  
 * 
 * This class assumes that it is passed a valid song name when requesting a song.  
 * The valid song names can be accessed
 * in the utils package under MusicNames...
 * 
 * This class depends on the javafx Media and MediaPlayer classes to handle playing the music.
 * The song names live in the MusicNames class, and the corresponding sound files live in the 
 * music folder.
 * 
 * The MusicController is created by passing in a class loader:
 * MusicController musicController = new MusicController(aClassLoader)
 * To play a song,
 * musicController.playSong(MusicNames.<valid music name>);
 * 
 * When playSong is called again, the music controller first stops the song that is currently playing, and
 * then starts the new song.
 * 
 * @author matthewfaw
 *
 */

public class MusicController {
	private String fCurrentSong;
	private MediaPlayer fMediaPlayer;
	private ClassLoader fClassLoader;

	/**
	 * Sets up the class loader to be used in the media player
	 * @param aClassLoader
	 */
	public MusicController(ClassLoader aClassLoader)
	{
		fClassLoader = aClassLoader;
	}
	
	/**
	 * Stops any song that is currently playing, and plays the specified song
	 * @param aSongName
	 */
	public void playSong(String aSongName)
	{
		if (fMediaPlayer != null) {
			if (fMediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
				fMediaPlayer.stop();
			}
		}
		fCurrentSong = aSongName;
        Media media = new Media(fClassLoader.getResource(aSongName).toString());
        fMediaPlayer = new MediaPlayer(media);
        fMediaPlayer.play();
	}
}