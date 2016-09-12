package controllers;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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