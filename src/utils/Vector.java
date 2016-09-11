package utils;

public class Vector {
	private double fX;
	private double fY;

	public Vector(double aX, double aY/*, double aWidth, double aHeight*/)
	{
		fX = aX;
		fY = aY;
	}
	
	public double getX()
	{
		return fX;
	}
	public double getY()
	{
		return fY;
	}
}