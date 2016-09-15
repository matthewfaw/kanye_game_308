// This entire file is part of my masterpiece
// Matthew Faw

package controllers;

import java.util.ArrayList;

import utils.Vector;
import views.elements.foreground.characters.Character;
import views.elements.foreground.obstacles.Obstacle;
import views.scenes.GameScene;
/**
 * The purpose of the CharacterController class is to serve as a common base class
 * to each type of character in the game, capturing the essential features of a game
 * character, and allowing each game character to extend the feature set.
 * 
 * The main assumptions of this class are:
 * After creating a character controller, one will call setSurroundings in order to 
 * initialize the surroundings of the character to the scene, and then one will create character
 * Because the implementation details differ slightly depending on which character controller we're 
 * instantiating, we must make these methods abstract in order to ensure they are created and accessible
 * to each character controller
 * 
 * We additionally note that all subclasses may not need all of these methods. However, putting the methods
 * allowing checking the surroundings and handling jumps will allow us to create more general purpose 
 * character controllers, and enemies who can actually jump
 * 
 * This class depends on the Character Class, the obstacle class, the Door exploration scene class,
 * and the game scene class. This class will be called by the GameController.
 * 
 * This superclass allows us to have the similar functionality for multiple types of subclasses:
 * For example, to create an enemyController:
 * CharacterController enemyController = new EnemeyController(aId);
 * enemyController.setSurroundings(gameScene);
 * enemyController.createCharacter(PictureNames.Taylor, initialVelocity, initialPosition);
 * 
 * Although setSurroundings and createCharacter are not actually defined in CharacterController, because the methods
 * are made abstract, we can guarantee that each subclass has created these methods, and thus they can be validly
 * called!
 * 
 * Whenever a scene transition occurs, simply notify the character controller by again calling setSurroundings
 * with the new Scene as the parameter
 * 
 * @author matthewfaw
 *
 */

public abstract class CharacterController {
	protected static final double DEL_X = 1.0;
	protected static final double DEL_Y = 1.0;
	private static final double JUMP_VELOCITY = 7.0;
	private static final double FREEFALL_VELOCITY = 0.0;
	private static final double SMALL_TIME_INTERVAL = 0.5;
	
	private GameScene fCurrentScene;
	
	protected Character fCharacter;
	protected ArrayList<Obstacle> fSurroundingObstacles;
	protected double fVelocityX;
	protected double fVelocityY;
	protected double fTimeInAir;
	protected boolean fOnGround;
	private boolean fIsFalling;
	protected boolean fGravityIsEnabled;
	
	/**
	 * Initialize necessary fields
	 */
	public CharacterController()
	{
		fSurroundingObstacles = new ArrayList<Obstacle>();
	}

	/**
	 * A method used to initialize the character fields so the character can be made aware of its surroundings
	 * This method is required to be called upon initializing the character controller
	 * @param aGameScene
	 */
	public abstract void setSurroundings(GameScene aGameScene);
		
	/**
	 * Method used to create the character associated with the character controller
	 * This method is required to be called upon initializing the character controller
	 * @param aCharacterFileName
	 * @param aStartingVelocity
	 * @param aStartingPosition
	 */
	public abstract void createCharacter(String aCharacterFileName, Vector aStartingVelocity, Vector aStartingPosition);
	
	/**
	 * A protected method called by createCharacter, used to set up the character's fields
	 * @param aStartingVelocity
	 * @param aStartingPosition
	 */
	protected abstract void initializeCharacterFields(Vector aStartingVelocity, Vector aStartingPosition);
	
	/**
	 * Gets the scene that the character controlled by this object is in
	 * @return the current scene
	 */
	public GameScene getCurrentScene()
	{
		return fCurrentScene;
	}
	
	/**
	 * Adjusts the character's x and y coordinates according to parameters, if the requested move is valid
	 * @param aXUnit
	 * @param aYUnit
	 */
	public void moveCharacter(double aXUnit, double aYUnit)
	{
		if ( (aXUnit > 0 && surroundingsAreClearOnRight()) ||
				(aXUnit < 0 && surroundingsAreClearOnLeft()) ) {
			fCharacter.setX(fCharacter.getX() + aXUnit * DEL_X);
		}
		if ( (aYUnit > 0 && surroundingsAreClearBelow()) ||
				(aYUnit < 0 && surroundingsAreClearAbove())) {
			fCharacter.setY(fCharacter.getY() + aYUnit * DEL_Y);
		}
	}
	
	/**
	 * A method used to check if the character should begin falling, if they stepped off an obstacle
	 */
	public void checkForFreefall()
	{
		//Check if we're standing on thin air:
		if (surroundingsAreClearBelow() && fOnGround && fGravityIsEnabled) {
			fOnGround = false;
			fIsFalling = true;
			fVelocityY = FREEFALL_VELOCITY;
		} else {
			fIsFalling = (false || (fIsFalling && fGravityIsEnabled));
		}
	}
	
	/**
	 * Sets fields necessary to initiate the character's jump
	 */
	public void beginJump()
	{
		fOnGround = false;
		fVelocityY = JUMP_VELOCITY;
	}
	
	/**
	 * A method used by the controller to determine if a character is in a scene where gravity is enabled
	 * @return true if gravity is enabled, false otherwise
	 */
	public boolean isInAJumpingScene()
	{
		return fGravityIsEnabled;
	}
	
	
	private void endJump()
	{
		fTimeInAir = 0.0;
		fVelocityY = 0.0;
		fOnGround = true;
		fIsFalling = false;
	}
	
	private boolean movingInYAxis()
	{
		return (fVelocityY != 0.0 || fIsFalling);
	}
	
	/**
	 * A method used to update the character's jumping trajectory
	 * @param aElapsedTime
	 * @param aGravity
	 */
	public void updateJumpingTrajectory(double aElapsedTime, double aGravity)
	{
		if (movingInYAxis()) {
			fTimeInAir += aElapsedTime;
			//fVelocityY = aGravity*fTimeInAir + JUMP_VELOCITY;
			
			double displacementY = calculateVerticalDisplacement(aGravity);
			this.moveCharacter(0, -displacementY);

			if(!surroundingsAreClearBelow() && fTimeInAir > SMALL_TIME_INTERVAL){
				endJump();
			}
		} 
	}
	
	private double calculateVerticalDisplacement(double aGravity)
	{
		return 0.5*aGravity*fTimeInAir*fTimeInAir + fVelocityY*fTimeInAir;
	}
	
	protected boolean surroundingsAreClearOnLeft()
	{
		for (Obstacle obstacle: fSurroundingObstacles) {
			if (fCharacter.intersects(obstacle.getRoot())) {
				if (fCharacter.intersectsFromLeft(obstacle.getRoot())) {
					return false;
				}
			}
		}
		return true;
	}
	protected boolean surroundingsAreClearOnRight()
	{
		for (Obstacle obstacle: fSurroundingObstacles) {
			if (fCharacter.intersects(obstacle.getRoot())) {
				if (fCharacter.intersectsFromRight(obstacle.getRoot())) {
					return false;
				}
			}
		}
		return true;
	}
	protected boolean surroundingsAreClearAbove()
	{
		for (Obstacle obstacle: fSurroundingObstacles) {
			if (fCharacter.intersects(obstacle.getRoot())) {
				if (fCharacter.intersectsFromAbove(obstacle.getRoot())) {
					return false;
				}
			}
		}
		return true;
	}
	protected boolean surroundingsAreClearBelow()
	{
		for (Obstacle obstacle: fSurroundingObstacles) {
			if (fCharacter.intersects(obstacle.getRoot())) {
				if (fCharacter.intersectsFromBelow(obstacle.getRoot())) {
					return false;
				}
			}
		}
		return true;
	}
}