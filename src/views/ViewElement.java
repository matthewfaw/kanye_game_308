package views;

import javafx.scene.Group;

public abstract class ViewElement {
	protected Group fRoot;
	
	public ViewElement()
	{
		fRoot = new Group();
	}
	
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
}