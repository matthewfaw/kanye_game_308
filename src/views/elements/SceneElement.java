package views.elements;

import javafx.scene.Group;
import javafx.scene.shape.Shape;

// Info pertaining to both foreground and background elements

public abstract class SceneElement {
	
	protected Group fRoot;
	
	public double getX()
	{
		return fRoot.getLayoutX();
	}
	public void setX(double aPosition)
	{
		fRoot.setLayoutX(aPosition);
	}
	
	public double getY()
	{
		return fRoot.getLayoutY();
	}
	public void setY(double aPosition)
	{
		fRoot.setLayoutY(aPosition);
	}
	
	public Group getRoot()
	{
		return fRoot;
	}
	
	//public abstract Shape getOuterShape();
	
	public boolean intersects(Group obstacle)
	{
		return fRoot.getBoundsInParent().intersects(obstacle.getBoundsInParent());
	}
	
	public boolean intersectsFromBelow(Group obstacle)
	{
		double rootBottomY = fRoot.getBoundsInParent().getMaxY();
		double obstacleTopY = obstacle.getBoundsInParent().getMinY();
		
		return (rootBottomY >= obstacleTopY);
	}
	public boolean intersectsFromAbove(Group obstacle)
	{
		double rootTopY = fRoot.getBoundsInParent().getMinY();
		double obstacleBottomY = obstacle.getBoundsInParent().getMaxY();
		
		return (rootTopY <= obstacleBottomY);
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