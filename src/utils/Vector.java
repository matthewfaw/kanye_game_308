package utils;

public class Vector {
	private double fX;
	private double fY;

	/**
	 * Creates a new vector <aX,aY>
	 * @param aX
	 * @param aY
	 */
	public Vector(double aX, double aY/*, double aWidth, double aHeight*/)
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