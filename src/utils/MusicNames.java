package utils;

/**
 * This class is what is used to reference which songs are available for the game to use. 
 * Songs should not be referenced through their string values directly, only through this
 * class, so that we can make sure that song exists.
 * 
 * The fields of this class are intended to correspond in a one-to-one fashion to the 
 * songs available in the music folder. The game was designed to have one song per scene,
 * and as such, I reference the songs based on their corresponding scene.
 * 
 * One may access a field using:
 * MusicNames.COLLEGE_SCENE_MUSIC, for example. This can be used with the MusicController to play a song:
 * musicController.playSong(MusicNames.COLLEGE_SCENE_MUSIC);
 * 
 * @author matthewfaw
 *
 */

public final class MusicNames {
	//NOTE: This is the best way I could find to deal with constant strings in an easy way
	public static final String COLLEGE_SCENE_MUSIC = "my_way_home.mp3";
	public static final String FOREST_SCENE_MUSIC = "flashing_lights.mp3";
	public static final String TAYLOR_SCENE_MUSIC = "devil_in_a_new_dress.mp3";
	public static final String ULTRALIGHT_BEAM_SCENE_MUSIC = "champion.mp3";
}