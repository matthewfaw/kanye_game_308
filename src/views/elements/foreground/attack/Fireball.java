package views.elements.foreground.attack;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.PictureNames;
import utils.Vector;
import views.elements.SceneElement;

public class Fireball extends SceneElement {
	private Vector fDirection;

	public Fireball(int aWidth, int aHeight, Vector aDirection)
	{
		super();
		fDirection = aDirection;
		
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(PictureNames.Fireball));
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(aWidth);
        imageView.setFitHeight(aHeight);
        
        fRoot.getChildren().add(imageView);
 
	}
	
	public double getXDirection()
	{
		return fDirection.getX();
	}
	public double getYDirection()
	{
		return fDirection.getY();
	}
}