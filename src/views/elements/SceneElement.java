package views.elements;

import javafx.scene.Group;

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
}