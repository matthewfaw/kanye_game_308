package models;

/**
 * The purpose of this class is to serve as a general purpose data storage location. Models are where the
 * business logic happens. This model, PlayerStats, simply keeps track of the player's health and gold count.
 * Because this class is so lightweight, it may appear somewhat unneeded.  The nice part of this class is that
 * it allows us to keep track of data associated with many different objects.  Maybe we want to add complicated 
 * backend logic that determines the character's current health based on the number of levels passed, the number of characters
 * killed, the number of characters still alive, the amount of gold collected, ect.  Having a separate "layer" to keep
 * track of such game statistics is quite valuable. It allows us to have relatively simple controller logic in relation to 
 * game statistics
 * 
 * The main assumptions of player statis is that 1) The player starts out with gold count of 0, and 2) Each gold piece
 * is worth the same amount.
 * 
 * THe code is not depenedent upon other classes in the project.
 * 
 * At the beginning of the game, we create a single instance of PlayerStats:
 * PlayerStats playerStats = new PlayerStats();
 * 
 * Every time we see a new gold coin:
 * if (we see a gold coin) {
 * 	playerStats.addGoldCoin();
 * }
 * 
 * @author matthewfaw
 *
 */
public class PlayerStats {
	private double fPlayerHealth;
	private int fGoldCount;
	
	/**
	 * Initialize chacter's gold count to 0
	 */
	public PlayerStats()
	{
		fGoldCount = 0;
	}
	
	/**
	 * returns the health of the player
	 * @return player health
	 */
	public double getHealth()
	{
		return fPlayerHealth;
	}

	/**
	 * updates the player's health to a new value
	 * @param aHealthPercentage
	 */
	public void setHealth(double aHealthPercentage)
	{
		fPlayerHealth = aHealthPercentage;
	}
	
	/**
	 * Gets the current number of gold coins collected
	 * @return collected number of coins so far
	 */
	public int getGoldCount()
	{
		return fGoldCount;
	}
	
	/**
	 * Increment's the player's gold count by one
	 */
	public void addGoldPiece()
	{
		++fGoldCount;
	}
}