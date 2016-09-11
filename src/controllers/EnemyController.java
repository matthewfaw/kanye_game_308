package controllers;

import views.elements.foreground.characters.Enemy;
import utils.EnemyNames;
import utils.PictureNames;

public class EnemyController extends CharacterController {
	private static final boolean ACTIVE = true;
	private static final boolean NOT_ACTIVE = false;
	
	private double fOldVelocityX;
	private double fOldVelocityY;
	
	public EnemyController()
	{
		super();
	}

	public Enemy createEnemy(int aWidth, int aHeight, String aEnemyFileName)
	{
		fCharacter = new Enemy(aWidth, aHeight, aEnemyFileName);
		initializeCharacterFields();
		return ((Enemy) fCharacter);
	}

	protected void initializeCharacterFields()
	{
		fCharacter.setX(50);
		fCharacter.setY(250);
		((Enemy) fCharacter).setActivity(ACTIVE);
		
		fVelocityX = 1.0;
		if (fCharacter.getPictureName().equals(PictureNames.Taylor)) {
			fVelocityY = -1.0;
		} else {
			fVelocityY = 0.0;
		}
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