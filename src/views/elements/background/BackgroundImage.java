package views.elements.background;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import views.elements.SceneElement;

public class BackgroundImage extends SceneElement {
	public BackgroundImage(int aWidth, int aHeight, String aImageName)
	{
		fRoot = new Group();
		
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(aImageName));
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(aWidth);
        imageView.setFitHeight(aHeight);
        
        fRoot.getChildren().add(imageView);
	}
}