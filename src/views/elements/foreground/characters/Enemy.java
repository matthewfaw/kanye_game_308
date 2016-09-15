package views.elements.foreground.characters;

/**
 * This class serves as a specialized version of a character. 
 * In addition to having basic character functionality, Enemy class also
 * has a notion of an index for easy identification, and activity,
 * so that enemies can be disabled and enabled.
 * 
 * It is assumed that every Enemy will be assigned an id, since I used this 
 * id for easy identification in arrays.
 * 
 * The class has no dependencies, except on its superclass, Character
 * 
 * An enemy may be created as follows:
 * Enemy enemy = new Enemy(PictureNames.Taylor, 42);
 * enemy.setActivity(NOT_ACTIVE); //Assuming NOT_ACTIVE is a boolean
 * 
 * @author matthewfaw
 *
 */

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