package views.elements.foreground.obstacles;

import utils.PictureNames;

public class Wall extends Obstacle {
	/**
	 * Creates a new wall
	 * @param aWidth
	 * @param aHeight
	 */
	public Wall(int aWidth, int aHeight)
	{
		super(aWidth, aHeight, PictureNames.Wall);
	}
}