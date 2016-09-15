package views;

import javafx.scene.Group;

/**
 * The purpose of this class is to serve as the foundational class for every view class.
 * It enforces each ViewElement to have a root node (which can be added to the game scene),
 * as well as a way to access this root node, and obtain its coordinates.
 * 
 * This class assumes that each view element will 1) need a root, and 2) will need to be able to 
 * access this root.  This assumption is reasonable, because the root is how view elements are added to the scene
 * 
 * This class only depends on the JavaFX Group object.
 * 
 * This class, since it's abstract, cannot be instantiated. However, one can perform such operations on
 * any ViewElement:
 * aViewElement.getRoot();
 * aViewElement.getX();
 * 
 * @author matthewfaw
 *
 */

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