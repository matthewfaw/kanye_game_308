package views.elements.foreground.obstacles;

import utils.PictureNames;

/**
 * This class simply serves to make creating a college more readible by choosing the
 * default picture of the college.
 * 
 * This class assumes that a college should have only one specified picture
 * 
 * The class only depends on Obstacle superclass and PictureNames
 * 
 * Instantiating this object is simple:
 * College c = new College(80,80);
 * ... = c.getRoot();
 * 
 * @author matthewfaw
 *
 */

public class College extends Obstacle {
	/**
	 * Creates a College object
	 * @param aWidth
	 * @param aHeight
	 */
	public College(int aWidth, int aHeight)
	{
		super(aWidth, aHeight, PictureNames.College);
	}
}