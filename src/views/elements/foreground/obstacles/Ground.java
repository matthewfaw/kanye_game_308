package views.elements.foreground.obstacles;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utils.PictureNames;
import views.elements.SceneElement;

public class Ground extends Obstacle {
	public Ground(int aWidth, int aHeight)
	{
		super(aWidth, aHeight, PictureNames.Grass);
	}
}