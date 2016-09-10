package controllers;

import views.elements.foreground.characters.Enemy;

public class EnemyController extends CharacterController {
	
	public EnemyController()
	{
		super();
	}

	public Enemy createEnemy(int aWidth, int aHeight)
	{
		fCharacter = new Enemy(aWidth, aHeight);
		initializeCharacterFields();
		return ((Enemy) fCharacter);
	}

	protected void initializeCharacterFields()
	{
		fCharacter.setX(50);
		fCharacter.setY(250);
		
		fVelocityX = 1.0;
		fVelocityY = 0.0;
		fTimeInAir = 0.0;
		fOnGround = true;
	}
	
	public void moveCharacter()
	{
		if (!surroundingsAreClearOnRight() || !surroundingsAreClearOnLeft()) {
			fVelocityX *= -1;
		}
		moveCharacter(fVelocityX, 0.0);
	}
}