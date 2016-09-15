package utils;

/**
 * This class is intended to simplify dealing with elements that naturally have more than
 * one part, such as a direction, a point, or a velocity. 
 * 
 * It assumes that vectors have exactly two components. It also assumes that vectors will
 * be composed only of doubles. It also assumes that, once a vector is made, its values
 * will not be updated.
 * 
 * It has no dependencies.
 * 
 * One may instantiate a vector as follows:
 * Vector v = new Vector(4.2,5.7);
 * and access and element by:
 * double xComponent = v.getX();
 * 
 * @author matthewfaw
 *
 */

public class Vector {
	private double fX;
	private double fY;

	/**
	 * Creates a new vector <aX,aY>
	 * @param aX
	 * @param aY
	 */
	public Vector(double aX, double aY)
	{
		fX = aX;
		fY = aY;
	}
	
	/**
	 * returns the first element in the vector
	 * @return first element
	 */
	public double getX()
	{
		return fX;
	}
	/**
	 * returns the second element in the vector
	 * @return second element
	 */
	public double getY()
	{
		return fY;
	}
}