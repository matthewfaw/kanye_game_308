package views;

import javafx.scene.Group;

public abstract class ViewElement {
	protected Group fRoot;
	
	/**
	 * Initializes a generalized base view element
	 */
	public ViewElement()
	{
		fRoot = new Group();
	}
	
	/**
	 * 
	 * @return the upper x coordinate 
	 */
	public double getX()
	{
		return fRoot.getLayoutX();
	}
	/**
	 * sets the upper corner x coordinate
	 * @param aPosition
	 */
	public void setX(double aPosition)
	{
		fRoot.setLayoutX(aPosition);
	}
	
	/**
	 * 
	 * @return the lower y coordinate of the object
	 */
	public double getY()
	{
		return fRoot.getLayoutY();
	}
	/**
	 *  sets the lower y coordinate of the object
	 * @param aPosition
	 */
	public void setY(double aPosition)
	{
		fRoot.setLayoutY(aPosition);
	}
	
	/**
	 * 
	 * @return the root of the object for view manipulation
	 */
	public Group getRoot()
	{
		return fRoot;
	}
}