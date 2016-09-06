package views.elements.foreground.obstacles;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import views.elements.SceneElement;

public class College extends Obstacle {
	private static final Color COLLEGE_COLOR = Color.DARKBLUE;
	
	public College(int aWidth, int aHeight)
	{
		fRoot = new Group();
		
		Rectangle college = new Rectangle();
		college.setWidth(aWidth);
		college.setHeight(aHeight);
		college.setFill(COLLEGE_COLOR);
		
		fRoot.getChildren().add(college);
	}
}