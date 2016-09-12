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
	
	public EnemyController()
	{
		super();
	}

	public void createEnemy(String aEnemyFileName, Vector aStartingVelocity, Vector aStartingPosition, int aId)
	{
		fCharacter = new Enemy(aEnemyFileName, aId);
		initializeCharacterFields(aStartingVelocity, aStartingPosition);
	}
	
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

	public void reenableEnemy()
	{
		fVelocityX = fOldVelocityX;
		fVelocityY = fOldVelocityY;
		((Enemy) fCharacter).setActivity(ACTIVE);
	}
}