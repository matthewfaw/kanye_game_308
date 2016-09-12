package controllers;

import views.elements.foreground.characters.Enemy;
import utils.Direction;
import utils.Vector;

public class EnemyController extends CharacterController {
	private static final boolean ACTIVE = true;
	private static final boolean NOT_ACTIVE = false;
	private static final Vector DEFAULT_ORIGIN = new Vector(50,250);
	
	private double fOldVelocityX;
	private double fOldVelocityY;
	
	/**
	 * Simply calls the parent constructor to initialize necessary fields
	 */
	public EnemyController()
	{
		super();
	}

	/**
	 * Creates a new Enemy object in the scene based on the parameters
	 * @param aEnemyFileName
	 * @param aStartingVelocity
	 * @param aStartingPosition
	 * @param aId
	 */
	public void createEnemy(String aEnemyFileName, Vector aStartingVelocity, Vector aStartingPosition, int aId)
	{
		fCharacter = new Enemy(aEnemyFileName, aId);
		initializeCharacterFields(aStartingVelocity, aStartingPosition);
	}
	
	/**
	 * returns the Enemy object associated with this controller
	 * @return
	 */
	public Enemy getEnemy()
	{
		return (Enemy)fCharacter;
	}

	protected void initializeCharacterFields()
	{
		initializeCharacterFields(Direction.RIGHT, DEFAULT_ORIGIN);
	}

	private void initializeCharacterFields(Vector aStartingVelocity, Vector aStartingPosition)
	{
		fCharacter.setX(aStartingPosition.getX());
		fCharacter.setY(aStartingPosition.getY());
		((Enemy) fCharacter).setActivity(ACTIVE);
		
		fVelocityX = aStartingVelocity.getX();
		fVelocityY = aStartingVelocity.getY();
		fTimeInAir = 0.0;
	}
	
	/**
	 * A method used to simplify character movement.  Will cause the character to reflect when it hits an obstacle
	 */
	public void moveCharacter()
	{
		if (!surroundingsAreClearOnRight() || !surroundingsAreClearOnLeft()) {
			fVelocityX *= -1;
		}
		if (!surroundingsAreClearBelow() || !surroundingsAreClearAbove()) {
			fVelocityY *= -1;
		}
		moveCharacter(fVelocityX, fVelocityY);
	}
	
	/**
	 * A method which makes the enemy stop in it's tracks. It can no longer hurt the main character until it has been
	 * reenabled
	 */
	public void disableEnemy()
	{
		if (fVelocityX != 0.0 || fVelocityY != 0.0) {
			fOldVelocityX = fVelocityX;
			fOldVelocityY = fVelocityY;
		}

		fVelocityX = 0.0;
		fVelocityY = 0.0;
		((Enemy) fCharacter).setActivity(NOT_ACTIVE);
	}

	/**
	 * A method to undo the disableEnemy command
	 */
	public void reenableEnemy()
	{
		fVelocityX = fOldVelocityX;
		fVelocityY = fOldVelocityY;
		((Enemy) fCharacter).setActivity(ACTIVE);
	}
}