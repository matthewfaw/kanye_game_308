package views.elements.foreground.obstacles;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import views.elements.SceneElement;

/**
 * This class serves mainly to take advantage of reflection that Java allows through
 * instanceof.  We can identify a scene element as an object, and with that information can customize the character's
 * reaction to an obstacle on the screen.
 * 
 * This class assumes that an obstacle will have a picture.
 * 
 * This class only depends on JavaFX image utilites and the superclass SceneElement
 * 
 * After instantiating:
 * SceneElement elem = new Obstacle(50,50,PictureNames.Wall);
 * if (elem instanceof Obstacle) [
 *  // do stuff
 *  }
 * 
 * @author matthewfaw
 *
 */

public abstract class Obstacle extends SceneElement {
	/**
	 * Creates an Obstacle
	 * @param aWidth
	 * @param aHeight
	 * @param aImageFillerName
	 */
	public Obstacle(int aWidth, int aHeight, String aImageFillerName)
	{
		super();

        Image image = new Image(getClass().getClassLoader().getResourceAsStream(aImageFillerName));
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(aWidth);
        imageView.setFitHeight(aHeight);
        
        fRoot.getChildren().add(imageView);
	}
}