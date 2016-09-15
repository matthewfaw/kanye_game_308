package utils;

/**
 * This enum allows us to make explicit statements in the code what game settings we're specifying.
 * 
 * There are no assumptions or dependencies in this enum
 * 
 * We may use this enum as follows:
 * switch(aGameSetting) {
 * 	case Easy:
 * 		// do easy stuff;
 * 		break;
 * 	case Hard:
 * 		// do hard stuff;
 * 		break;
 * 	default:
 * 		// do default stuff;
 * }
 * @author matthewfaw
 *
 */

public enum GameSettings {
	Easy,
	Hard
}