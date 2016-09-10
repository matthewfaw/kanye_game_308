package views.elements.foreground.obstacles;

import javafx.scene.paint.Color;
import utils.PictureNames;

public class Wall extends Obstacle {
	public Wall(int aWidth, int aHeight)
	{
		super(aWidth, aHeight, PictureNames.Wall);
	}
}