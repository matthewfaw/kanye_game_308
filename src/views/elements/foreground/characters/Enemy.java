package views.elements.foreground.characters;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import views.elements.SceneElement;
import utils.EnemyNames;

public class Enemy extends Character {
	public Enemy(int aWidth, int aHeight, EnemyNames aEnemyName)
	{
		fRoot = new Group();
		ImageView aImageView = new ImageView();
		
		Image image;
		switch (aEnemyName) {
			case Camera:
				image = createImage("camera.jpg");
				break;
			case Taylor:
				image = createImage("taylor.jpg");
				break;
			default:
				image = createImage("duke.gif");
		}

        aImageView.setImage(image);
        aImageView.setFitWidth(aWidth);
        aImageView.setFitHeight(aHeight);
        
        fRoot.getChildren().add(aImageView);
 
	}
	
	private Image createImage(String fileName)
	{
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(fileName));
        return image;
	}
}