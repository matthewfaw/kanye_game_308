package views.scenes;

import utils.PictureNames;
import views.elements.background.BackgroundImage;

public class UltralightBeamScene extends GameScene {
	private static final String BACKGROUND_IMAGE_NAME = PictureNames.Bound;

	/**
	 * Creates an empty scene with Kanye and Kim
	 * @param aWidth
	 * @param aHeight
	 */
	public UltralightBeamScene(int aWidth, int aHeight)
	{
		super();
		BackgroundImage backgroundImage = new BackgroundImage(aWidth, aHeight, BACKGROUND_IMAGE_NAME);
		
		fRoot.getChildren().add(backgroundImage.getRoot());
	}
}