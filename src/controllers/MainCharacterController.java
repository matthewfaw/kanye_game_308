package controllers;

import java.util.ArrayList;

import utils.PictureNames;
import utils.Vector;
import views.elements.foreground.attack.Fireball;
import views.elements.foreground.characters.Enemy;
import views.elements.foreground.characters.MainCharacter;
import views.elements.foreground.obstacles.Obstacle;
import views.elements.foreground.obstacles.Tunnel;
import views.elements.foreground.rewards.Gold;
import views.scenes.DoorExplorationScene;
import views.scenes.GameScene;

/**
 * The purpose of this class is to capture the functionality essential to the main character
 * but not needed by any other character (i.e. the enemies)
 * 
 * The main assumptions of this class are that:
 * 1) The owner of the MainCharacterController will pass any enemy that has been created in the scene and
 * any gold that has been created in the scene
 * 2) At each time step, the owner will use the controller's methods to check if the character has interacted
 * with any other element in the scene.
 * 
 * The class depends on the Fireball, Enemy, MainCharacter, Obstacle, Tunnel, and Gold classes.  The class 
 * is to be owned by the game controller.
 * 
 * At each time step, the owner may check:
 * 1) if the character is touching gold charaacterController.isTouchingGold()
 * 2) If the character should take a scene transition (i.e. he found a tunnel) characterController.checkForSceneTransition()
 * 3) If the character killed an active enemy characterController.killedAnActiveEnemy()
 * 4) If the character is being hurt by an enemy. characterController.isBeingHurtByAnActiveEnemy()
 * 
 * @author matthewfaw
 *
 */

public class MainCharacterController extends CharacterController {
	private ArrayList<Enemy> fSurroundingEnemies;
	private ArrayList<Fireball> fFireballs;
	private ArrayList<Gold> fSurroundingGold;

	/**
	 * Initializes the character controller's necessary fields
	 */
	public MainCharacterController()
	{
		super();
		fSurroundingEnemies = new ArrayList<Enemy>();
		fFireballs = new ArrayList<Fireball>();
		fSurroundingGold = new ArrayList<Gold>();
	}

	/**
	 * a method used to fill out all of the character's fields related to the surrounding scene
	 * @param aScene
	 */
	public void setSurroundings(GameScene aScene)
	{
		if (aScene.getObstacles() != null) {
			fSurroundingObstacles = aScene.getObstacles();
		}
		fGravityIsEnabled = !(aScene instanceof DoorExplorationScene);
		
		if (fCharacter != null) {
			initializeCharacterFields(new Vector(0,0), MainCharacter.DEFAULT_POSITION);
		}
	}

	/**
	 * creates the character associated with the controller
	 */
	public void createCharacter(String aCharacterName, Vector aStartingVelocity, Vector aStartingPosition)
	{
		fCharacter = new MainCharacter(PictureNames.MainCharacter);
		initializeCharacterFields(aStartingVelocity, aStartingPosition);
	}

	/**
	 * sets up the character's associated info
	 */
	protected void initializeCharacterFields(Vector aStartingVelocity, Vector aStartingPosition)
	{
		fCharacter.setX(aStartingPosition.getX());
		fCharacter.setY(aStartingPosition.getY());
		
		fVelocityX = 0.0;
		fVelocityY = aStartingVelocity.getY();
		fTimeInAir = 0.0;
		fOnGround = true;
	}

	
	/**
	 * retrieves the associated character
	 * @return the character controlled by the controller
	 */
	public MainCharacter getMainCharacter()
	{
		return (MainCharacter)fCharacter;
	}
	
	/**
	 * Adds an enemy to be associated with the character
	 * @param aNewEnemy
	 */
	public void addEnemy(Enemy aNewEnemy)
	{
		fSurroundingEnemies.add(aNewEnemy);
	}
	
	/**
	 * Adds gold to be associated with the character
	 * @param aNewGoldPiece
	 */
	public void addGold(Gold aNewGoldPiece)
	{
		fSurroundingGold.add(aNewGoldPiece);
	}
	
	/**
	 * Checks if the character is currently touching gold
	 * @return returns true if the character is touching gold
	 */
	public Gold isTouchingGold()
	{
		for (Gold gold: fSurroundingGold) {
			if (fCharacter.intersects(gold.getRoot())) {
				return gold;
			}
		}
		return null;
	}
	
	/**
	 * remove gold from the array--useful for after the gold has been picked up by the character
	 * @param aGold
	 */
	public void removeGold(Gold aGold)
	{
		fSurroundingGold.remove(aGold);
	}

	/**
	 * Clears all of the fields that relate to the character's current surroundings
	 */
	public void emptyBelongings()
	{
		fSurroundingEnemies.clear();
		fFireballs.clear();
		fSurroundingGold.clear();
	}
		
	/**
	 * Checks if the character is incedent on an active tunnel
	 * @return the tunnel, if the character is touching one; otherwise, returns null
	 */
	public Tunnel checkForSceneTransition()
	{
		for (Obstacle obstacle: fSurroundingObstacles) {
			if (obstacle instanceof Tunnel && fCharacter.intersects(obstacle.getRoot())) {
				Tunnel tunnel = (Tunnel) obstacle;

				return tunnel;
			}
		}
		return null;
	}
	
	/**
	 * Creates fire to be launched by Kanye
	 * @param aDirection
	 * @return the Fireball object, so that the game controller can manage it
	 */
	public Fireball spitFire(Vector aDirection)
	{
		Fireball fireball = new Fireball(aDirection);
		fireball.setX(fCharacter.getX());
		fireball.setY(fCharacter.getY());
		fFireballs.add(fireball);
		
		return fireball;
	}

	/**
	 * Move the fireballs in the scene owned by this character
	 */
	public void moveFireballs()
	{
		// XXX: fix movement
		for (Fireball fireball: fFireballs) {
			fireball.setX(fireball.getX() + fireball.getXDirection() * DEL_X);
			fireball.setY(fireball.getY() + fireball.getYDirection() * DEL_Y);
		}
	}
	
	/**
	 * checks if the character killed an active enemy
	 * @return the killed enemy, if there were one, otherwise null
	 */
	public Enemy killedAnActiveEnemy()
	{
		for (Enemy enemy: fSurroundingEnemies) {
			if (fCharacter.intersectsFromBelow(enemy.getRoot()) && enemy.isActive()) {
				if (!enemy.getPictureName().equals(PictureNames.Taylor)) {
					return enemy;
				}
			}
			for (Fireball fireball: fFireballs) {
				if (fireball.intersects(enemy.getRoot())) {
					return enemy;
				}
			}
		}
		return null;
	}

	/**
	 * Checks if an enemy is touching the main character
	 * @return
	 */
	public boolean isBeingHurtByAnActiveEnemy()
	{
		for (Enemy enemy: fSurroundingEnemies) {
			if (fCharacter.intersects(enemy.getRoot()) && enemy.isActive()) {
				return true;
			}
		}
		return false;
	}
	
}