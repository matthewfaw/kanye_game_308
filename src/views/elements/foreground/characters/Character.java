package views.elements.foreground.characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import views.elements.SceneElement;

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