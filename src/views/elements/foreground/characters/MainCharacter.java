package views.elements.foreground.characters;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import views.elements.SceneElement;

public class MainCharacter extends Character {
	public MainCharacter(int aWidth, int aHeight)
	{
		fRoot = new Group();
		
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("kanye.jpg"));
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(aWidth);
        imageView.setFitHeight(aHeight);
        
        fRoot.getChildren().add(imageView);
 
	}
}