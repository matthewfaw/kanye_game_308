package views.elements.foreground.attack;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.PictureNames;
import utils.Vector;
import views.elements.SceneElement;

public class Fireball extends SceneElement {
	private static final int FIREBALL_WIDTH = 40;
	private static final int FIREBALL_HEIGHT = 40;
	
	private Vector fDirection;

	public Fireball(Vector aDirection)
	{
		super();
		fDirection = aDirection;
		
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(PictureNames.Fireball));
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(FIREBALL_WIDTH);
        imageView.setFitHeight(FIREBALL_HEIGHT);
        
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