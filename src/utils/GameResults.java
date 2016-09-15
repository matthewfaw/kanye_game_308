package utils;

/**
 * This enum is simply a way of making explicit the possible outcomes of the game.
 * It allows us to make a switch case for the outcome of the game.
 * 
 * There are no dependencies or assumptions in this enum.
 * 
 * We can use the enum in the following way:
 * public void doStuff(GameResults aGameResult)
 * {
 * 	switch(aGameResult) {
 * 		case Win:
 * 			//do winning stuff
 * 			break;
 * 		case Lose:
 * 			//do losing stuff
 * 			break;
 * 		default:
 * 			//do default stuff;
 * 	}
 * }
 * @author matthewfaw
 *
 */

public enum GameResults {
	Win,
	Lose
}