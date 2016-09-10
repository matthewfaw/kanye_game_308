package views.elements.foreground.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import views.elements.SceneElement;

public abstract class Character extends SceneElement {
	public Character(int aWidth, int aHeight, String aCharacterPictureName)
	{
		super();

        Image image = new Image(getClass().getClassLoader().getResourceAsStream(aCharacterPictureName));
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(aWidth);
        imageView.setFitHeight(aHeight);
        
        fRoot.getChildren().add(imageView);
 
	}

}