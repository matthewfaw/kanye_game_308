package views.elements.foreground.rewards;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.PictureNames;
import views.elements.SceneElement;

/**
 * The purpose of this class, too, is primarily readiblity, since gold will likely always need to be the same size and image.
 * 
 * The assumptions are that the size and image selections are not problematic.
 * 
 * The dependencies are on SceneElement, PictureNames, and JavaFX Image and ImageView
 * 
 * Create a new piece of gold:
 * Gold g = new Gold();
 * Group root = g.getRoot();
 * 
 * @author matthewfaw
 *
 */

public class Gold extends SceneElement {
	private static final int GOLD_WIDTH = 40;
	private static final int GOLD_HEIGHT = 40;
	public Gold()
	{
		super();

        Image image = new Image(getClass().getClassLoader().getResourceAsStream(PictureNames.Gold));
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(GOLD_WIDTH);
        imageView.setFitHeight(GOLD_HEIGHT);
        
        fRoot.getChildren().add(imageView);
		
	}
}