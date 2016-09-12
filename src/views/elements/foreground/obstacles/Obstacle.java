package views.elements.foreground.obstacles;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import views.elements.SceneElement;

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