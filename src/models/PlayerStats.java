package models;

public class PlayerStats {
	private double fPlayerHealth;
	private int fGoldCount;
	
	public PlayerStats()
	{
		fGoldCount = 0;
	}
	
	public double getHealth()
	{
		return fPlayerHealth;
	}

	public void setHealth(double aHealthPercentage)
	{
		fPlayerHealth = aHealthPercentage;
	}
	
	public int getGoldCount()
	{
		return fGoldCount;
	}
	
	public void addGoldPiece()
	{
		++fGoldCount;
	}
}