package views.elements.foreground.obstacles;

import utils.PictureNames;

/**
 * This class serves primarily to create more readability when creating a scene by defaulting the Picture displayed in the
 * wall object.
 * 
 * The only assumption is that the user will not want to specify the background image of the wall 
 * 
 * The class depends on the Obstacle subclass and PictureNames
 * 
 * The class is used by:
 * Wall wall = new Wall(100,10);
 * ... = wall.getRoot();
 * 
 * @author matthewfaw
 *
 */

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