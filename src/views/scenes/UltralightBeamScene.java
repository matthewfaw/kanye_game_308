package views.scenes;

import java.util.ArrayList;

import javafx.scene.Group;
import utils.PictureNames;
import views.elements.background.BackgroundImage;
import views.elements.foreground.obstacles.Obstacle;

public class UltralightBeamScene extends GameScene {
	private static final String BACKGROUND_IMAGE_NAME = PictureNames.Bound;

	public UltralightBeamScene(int aWidth, int aHeight)
	{
		fRoot = new Group();
		fObstacles = new ArrayList<Obstacle>();
		BackgroundImage backgroundImage = new BackgroundImage(aWidth, aHeight, BACKGROUND_IMAGE_NAME);
		
		fRoot.getChildren().add(backgroundImage.getRoot());
	}
}