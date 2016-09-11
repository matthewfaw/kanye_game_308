package controllers;

import views.elements.foreground.characters.Enemy;
import utils.EnemyNames;
import utils.PictureNames;
import utils.Vector;

public class EnemyController extends CharacterController {
	private static final boolean ACTIVE = true;
	private static final boolean NOT_ACTIVE = false;
	
	private double fOldVelocityX;
	private double fOldVelocityY;
	
	public EnemyController()
	{
		super();
	}

	public void createEnemy(int aWidth, int aHeight, String aEnemyFileName, Vector aStartingVelocity, Vector aStartingPosition)
	{
		fCharacter = new Enemy(aWidth, aHeight, aEnemyFileName);
		initializeCharacterFields(aStartingVelocity, aStartingPosition);
//		return ((Enemy) fCharacter);
	}
	
	public Enemy getEnemy()
	{
		return (Enemy)fCharacter;
	}

	protected void initializeCharacterFields()
	{
		initializeCharacterFields(new Vector(1.0, 0.0), new Vector(50, 250));
	}

	private void initializeCharacterFields(Vector aStartingVelocity, Vector aStartingPosition)
	{
		fCharacter.setX(aStartingPosition.getX());
		fCharacter.setY(aStartingPosition.getY());
		((Enemy) fCharacter).setActivity(ACTIVE);
		
		fVelocityX = aStartingVelocity.getX();
		fVelocityY = aStartingVelocity.getY();
//		if (fCharacter.getPictureName().equals(PictureNames.Taylor)) {
//			fVelocityY = -1.0;
//		} else {
//			fVelocityY = 0.0;
//		}
		fTimeInAir = 0.0;
//		fOnGround = true;
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
	
	public void disableMovement()
	{
		if (fVelocityX != 0.0 || fVelocityY != 0.0) {
			fOldVelocityX = fVelocityX;
			fOldVelocityY = fVelocityY;
		}

		fVelocityX = 0.0;
		fVelocityY = 0.0;
		((Enemy) fCharacter).setActivity(NOT_ACTIVE);
	}

	public void reenableMovement()
	{
		fVelocityX = fOldVelocityX;
		fVelocityY = fOldVelocityY;
		((Enemy) fCharacter).setActivity(ACTIVE);
	}
}