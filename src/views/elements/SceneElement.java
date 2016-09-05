package views.elements;

import javafx.scene.Group;

// Info pertaining to both foreground and background elements

public abstract class SceneElement {
	protected Group fRoot;
	
	public void setX(double aPosition)
	{
		fRoot.setLayoutX(aPosition);
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