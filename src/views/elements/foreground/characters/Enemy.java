package views.elements.foreground.characters;

public class Enemy extends Character {
	private boolean fIsActive;
	private int fId;
	
	/**
	 * Initializes an enemy
	 * @param aEnemyFileName
	 * @param aId
	 */
	public Enemy(String aEnemyFileName, int aId)
	{
		super(aEnemyFileName);
		fIsActive = true;
		fId = aId;
	}
	
	/**
	 * 
	 * @return true if the scene element is active, false if it has been deactivated
	 */
	public boolean isActive()
	{
		return fIsActive;
	}
	
	/**
	 * Sets if the element should be active or not
	 * @param aIsActive
	 */
	public void setActivity(boolean aIsActive)
	{
		fIsActive = aIsActive;
	}
	
	/**
	 * Sets the identifier of the enemy so it can be easily found in arrays
	 * @return
	 */
	public int getId()
	{
		return fId;
	}
}