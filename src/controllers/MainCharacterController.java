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

public class MainCharacterController extends CharacterController {
	private static final Vector DEFAULT_POSITION = new Vector(200, 50);
	
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
	 * creates the character associated with the controller
	 */
	public void createMainCharacter()
	{
		fCharacter = new MainCharacter();
		initializeCharacterFields();
		
//		return ((MainCharacter) fCharacter);
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
	 * sets up the character's associated info
	 */
	protected void initializeCharacterFields()
	{
		fCharacter.setX(DEFAULT_POSITION.getX());
		fCharacter.setY(DEFAULT_POSITION.getY());
		
//		fVelocityX = 0.0;
		fVelocityY = 0.0;
		fTimeInAir = 0.0;
		fOnGround = true;
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
	 * Move the fireballs in the scene
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