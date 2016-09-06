package views.elements.foreground.obstacles;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import views.elements.SceneElement;

public class Ground extends Obstacle {
	private static final Color GROUND_COLOR = Color.DARKGREEN;
	
	public Ground(int width, int height)
	{
		fRoot = new Group();
		
		Rectangle ground = new Rectangle();
		ground.setWidth(width);
		ground.setHeight(height);
		ground.setFill(GROUND_COLOR);
		
		fRoot.getChildren().add(ground);
	}
}