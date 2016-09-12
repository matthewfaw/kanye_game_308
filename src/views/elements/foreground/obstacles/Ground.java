package views.elements.foreground.obstacles;

import utils.PictureNames;

public class Ground extends Obstacle {
	/**
	 * Creates a ground object
	 * @param aWidth
	 * @param aHeight
	 */
	public Ground(int aWidth, int aHeight)
	{
		super(aWidth, aHeight, PictureNames.Grass);
	}
}