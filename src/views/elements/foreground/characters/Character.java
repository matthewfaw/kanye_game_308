package views.elements.foreground.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import views.elements.SceneElement;

/**
 * The Character class serves as a base class to provide all the essential features of
 * a character object, while allowing for extended features in subclasses. This allows
 * for base classes to have special features, such as greying out the object, without
 * giving such specialized functionality to every character
 * 
 * The main assumption of the class is that every Character will have a picture, and
 * will be a fixed width and height
 * 
 * Within my codebase, the only dependency is on the superclass, SceneElement
 * 
 * @author matthewfaw
 *
 */

public abstract class Character extends SceneElement {
	protected static final int DEFAULT_WIDTH = 50;
	protected static final int DEFAULT_HEIGHT = 50;
	
	private String fCharacterPictureName;

	/**
	 * Creates a character object with a picture
	 * @param aCharacterPictureName
	 */
	public Character(String aCharacterPictureName)
	{
		super();
		fCharacterPictureName = aCharacterPictureName;

        Image image = new Image(getClass().getClassLoader().getResourceAsStream(aCharacterPictureName));
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(DEFAULT_WIDTH);
        imageView.setFitHeight(DEFAULT_HEIGHT);
        
        fRoot.getChildren().add(imageView);
 
	}
	
	/**
	 * 
	 * @return the picture name associated with the character
	 */
	public String getPictureName()
	{
		return fCharacterPictureName;
	}

}