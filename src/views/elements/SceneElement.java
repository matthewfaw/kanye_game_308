package views.elements;

import javafx.scene.Group;
import views.ViewElement;

/**
 * This class serves as the primary building block for all scene elements.  It's primary
 * purpose is to provide methods which any scene element may use to tell if it intersects
 * with any other scene element. It also provides methods to determine on which side a particular element
 * intersects a scene element, a useful calculation when dealing with object interactions.
 * 
 * This class makes the assumption that if the length of overlap of two objects is sufficiently small, then
 * the intersection is a "false positive", and thus can be ignored.  This assumption was necessary to allow 
 * my characters to move around the scene without sticking.  When a character jumps, due to the discrete nature
 * of the game, the character may not always land precisely above ground.  Thus, the character may detect a small
 * region of overlap with the ground on it's sides, and thus may falsely think it's intersecting something from the side
 * and thus not allow left or right movement.  By creating a detection buffer, this issue is resolved.
 * 
 * The dependencies of the code are only on JavaFX Group and the ViewElements superclass.
 * By using the buffer, we imposing some restrictions on the rate of the clock tick of game play.  If I were to improve
 * the design of this class, I would make the buffer region a function of the sampling rate, to make things more precise.
 * 
 * This class allows any scene element object to ask:
 * boolean b = sceneElement.intersects(someGroup);
 * Or, more specifically, 
 * boolean b = sceneElement.intersectsFromRight(someGroup);
 * 
 * @author matthewfaw
 *
 */

public abstract class SceneElement extends ViewElement {
	private static final int DETECTION_BUFFER = 5;
	private static final double MINIMUM_SIDE_LENGTH_MULTIPLIER = 0.2;
	
	/**
	 * Initiaize scene element by calling its constructor
	 */
	public SceneElement()
	{
		super();
	}
	
	/**
	 * Check if scene element intersects with an obstacle
	 * @param obstacle
	 * @return true if there's an intersection, false if there isn't
	 */
	public boolean intersects(Group obstacle)
	{
		return fRoot.getBoundsInParent().intersects(obstacle.getBoundsInParent());
	}
	
	//XXX: Clean this method up
	/**
	 * Checks if scene element has an object directly below it
	 * This method is somewhat ugly because the positions aren't "exact", meaning
	 * two elements never collide exactly
	 * @param obstacle
	 * @return true if there is an obstacle 
	 */
	public boolean intersectsFromBelow(Group obstacle)
	{
		double rootBottomY = fRoot.getBoundsInParent().getMaxY();
		double obstacleTopY = obstacle.getBoundsInParent().getMinY();
		
		boolean rootTouchingObstacleInY = (rootBottomY >= obstacleTopY && 
											fRoot.getBoundsInParent().getMinY() < obstacle.getBoundsInParent().getMinY());

		double minLeftXCoord = Math.min(fRoot.getBoundsInParent().getMinX(), obstacle.getBoundsInParent().getMinX());
		double maxRightXCoord = Math.max(fRoot.getBoundsInParent().getMaxX(), obstacle.getBoundsInParent().getMaxX());

		boolean rootTouchingObstacleInX = (maxRightXCoord - minLeftXCoord 
											<= fRoot.getBoundsInParent().getWidth() + obstacle.getBoundsInParent().getWidth() - DETECTION_BUFFER);
		
		return rootTouchingObstacleInX && rootTouchingObstacleInY;
	}

	//XXX: Clean this method up
	/**
	 * Checks if scene element has an object directly aboce it
	 * @param obstacle
	 * @return true if there is an obstacle , false otherwise
	 */
	public boolean intersectsFromAbove(Group obstacle)
	{
		double rootTopY = fRoot.getBoundsInParent().getMinY();
		double obstacleBottomY = obstacle.getBoundsInParent().getMaxY();
		
		return (rootTopY <= obstacleBottomY &&
				fRoot.getBoundsInParent().getMaxY() > obstacle.getBoundsInParent().getMaxY());
	}
	
	/**
	 * Checks if scene element has an object directly to the left of it
	 * @param obstacle
	 * @return true if there is such an element
	 */
	public boolean intersectsFromLeft(Group obstacle)
	{
		double rootLeftX = fRoot.getBoundsInParent().getMinX();
		double obstacleRightX = obstacle.getBoundsInParent().getMaxX();
		
		if (sideIntersectionBoundaryIsAValidHeight(fRoot, obstacle)) {
			return (rootLeftX >= obstacleRightX);
		} else {
			return false;
		}
		
	}
	/**
	 * Checks if scene element has an object directly to the right of it
	 * @param obstacle
	 * @return true if there is such an element
	 */
	public boolean intersectsFromRight(Group obstacle)
	{
		double rootRightX = fRoot.getBoundsInParent().getMaxX();
		double obstacleLeftX = obstacle.getBoundsInParent().getMinX();
		
		if (sideIntersectionBoundaryIsAValidHeight(fRoot, obstacle)) {
			return (rootRightX <= obstacleLeftX);
		} else {
			return false;
		}	
	}
	
	private boolean sideIntersectionBoundaryIsAValidHeight(Group aFirstObject, Group aSecondObject)
	{
		double rootBottomY = aFirstObject.getBoundsInParent().getMaxY();
		double obstacleTopY = aSecondObject.getBoundsInParent().getMinY();
		
		double delta = Math.min(aFirstObject.getBoundsInParent().getHeight(), aSecondObject.getBoundsInParent().getHeight());
		// XXX: Remove magic number
		return Math.abs(rootBottomY - obstacleTopY) > MINIMUM_SIDE_LENGTH_MULTIPLIER  * delta;
	}
}