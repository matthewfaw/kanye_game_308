package controllers;

import views.elements.foreground.characters.Enemy;
import views.scenes.GameScene;

import utils.Vector;

/**
 * The purpose of this class is to capture the functionality of enemies that is not shared with other characters
 * 
 * It is assumed that, after intantiating an EnemyController, one calls setSurroundings(gameScene), and createCharacter(...).
 * At each time step, the moveCharacter() method is called to move the enemy in the proper direction.
 * 
 * The code depends on the Enemy class, and the Direction and Vector utils classes.  The enemyController is to be
 * owned by the gameController.
 * 
 * To create an enemyController:
 * EnemyController enemyController = new EnemeyController(aId);
 * enemyController.setSurroundings(gameScene);
 * enemyController.createCharacter(PictureNames.Taylor, initialVelocity, initialPosition);
 * To move the enemy in the scene:
 * enemyController.moveCharacter();
 * To disable the enemy from moving and harming the main character:
 * enemyController.disableEnemy();
 * To reeneable:
 * enemyController.reenableEnemy();
 * 
 * @author matthewfaw
 *
 */

public class EnemyController extends CharacterController {
	private static final boolean ACTIVE = true;
	private static final boolean NOT_ACTIVE = false;
	
	private double fOldVelocityX;
	private double fOldVelocityY;
	private int fId;
	
	/**
	 * Simply calls the parent constructor to initialize necessary fields
	 * @param aId: the identifier needed to find and enemy in an array
	 */
	public EnemyController(int aId)
	{
		super();
		fId = aId;
	}

	/**
	 * Creates a new Enemy object in the scene based on the parameters
	 * @param aEnemyFileName
	 * @param aStartingVelocity
	 * @param aStartingPosition
	 */
	public void createCharacter(String aEnemyFileName, Vector aStartingVelocity, Vector aStartingPosition)
	{
		fCharacter = new Enemy(aEnemyFileName, fId);
		initializeCharacterFields(aStartingVelocity, aStartingPosition);
	}
	
	/**
	 * Initializes the obstacles that are surrounding the current character fCharacter
	 * @param aGameScene: the current game scene from which to get the obstacles
	 */
	public void setSurroundings(GameScene aGameScene)
	{
		if (aGameScene.getObstacles() != null) {
			fSurroundingObstacles = aGameScene.getObstacles();
		}
	}
	
	/**
	 * returns the Enemy object associated with this controller
	 * @return
	 */
	public Enemy getEnemy()
	{
		return (Enemy)fCharacter;
	}

	protected void initializeCharacterFields(Vector aStartingVelocity, Vector aStartingPosition)
	{
		fCharacter.setX(aStartingPosition.getX());
		fCharacter.setY(aStartingPosition.getY());
		((Enemy) fCharacter).setActivity(ACTIVE);
		
		fVelocityX = aStartingVelocity.getX();
		fVelocityY = aStartingVelocity.getY();
		fTimeInAir = 0.0;
		fOnGround = true;
	}
	
	/**
	 * A method used to simplify automating character movement.  Will cause the character to reflect when it hits an obstacle
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