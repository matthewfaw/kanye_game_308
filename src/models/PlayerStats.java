package models;

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