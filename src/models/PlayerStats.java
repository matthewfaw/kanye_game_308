package models;

public class PlayerStats {
	private double fPlayerHealth;
	
	public PlayerStats()
	{
	}
	
	public double getHealth()
	{
		return fPlayerHealth;
	}

	public void setHealth(double aHealthPercentage)
	{
		fPlayerHealth = aHealthPercentage;
	}
}