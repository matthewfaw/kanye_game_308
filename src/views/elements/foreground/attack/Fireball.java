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

	/**
	 * Creates a fireball to be lauched by Kanye
	 * @param aDirection
	 */
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
	
	/**
	 * 
	 * @return the x direction of the fireball
	 */
	public double getXDirection()
	{
		return fDirection.getX();
	}
	/**
	 * 
	 * @return the y direction of the fireball
	 */
	public double getYDirection()
	{
		return fDirection.getY();
	}
}