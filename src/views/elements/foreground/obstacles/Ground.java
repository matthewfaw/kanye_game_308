package views.elements.foreground.obstacles;

import utils.PictureNames;

/**
 * Simply a class to make creating Grass more readable.
 * We force every grass object to have the same picture
 * 
 * The only assumptions are that Grass is an obstacle, and Grass should default to a picture of grass
 * 
 * The class only depends on it's superclass Obstacle and the utils class PictureNames
 * 
 * We may create new grass:
 * Grass grass = new Grass(40, 60);
 * ... = grass.getRoot();
 * 
 * @author matthewfaw
 *
 */

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