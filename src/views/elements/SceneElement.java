package views.elements;

import javafx.scene.Group;
import javafx.scene.shape.Shape;
import views.ViewElement;

// Info pertaining to both foreground and background elements

public abstract class SceneElement extends ViewElement {
	
	public SceneElement()
	{
		super();
	}
	
	//public abstract Shape getOuterShape();
	
	public boolean intersects(Group obstacle)
	{
		return fRoot.getBoundsInParent().intersects(obstacle.getBoundsInParent());
	}
	
	//XXX: Clean this method up
	public boolean intersectsFromBelow(Group obstacle)
	{
		double rootBottomY = fRoot.getBoundsInParent().getMaxY();
		double obstacleTopY = obstacle.getBoundsInParent().getMinY();
		
		boolean rootTouchingObstacleInY = (rootBottomY >= obstacleTopY && 
											fRoot.getBoundsInParent().getMinY() < obstacle.getBoundsInParent().getMinY());

		double minLeftXCoord = Math.min(fRoot.getBoundsInParent().getMinX(), obstacle.getBoundsInParent().getMinX());
		double maxRightXCoord = Math.max(fRoot.getBoundsInParent().getMaxX(), obstacle.getBoundsInParent().getMaxX());

		boolean rootTouchingObstacleInX = (maxRightXCoord - minLeftXCoord 
											<= fRoot.getBoundsInParent().getWidth() + obstacle.getBoundsInParent().getWidth() - 5);
		
		return rootTouchingObstacleInX && rootTouchingObstacleInY;
	}

	//XXX: Clean this method up
	public boolean intersectsFromAbove(Group obstacle)
	{
		double rootTopY = fRoot.getBoundsInParent().getMinY();
		double obstacleBottomY = obstacle.getBoundsInParent().getMaxY();
		
		return (rootTopY <= obstacleBottomY &&
				fRoot.getBoundsInParent().getMaxY() > obstacle.getBoundsInParent().getMaxY());
	}
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
		return Math.abs(rootBottomY - obstacleTopY) > 0.2 * delta;
	}
}