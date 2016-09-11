package utils;

public class Position {
	private double fXPosition;
	private double fYPosition;
//	private double fWidth;
//	private double fHeight;

	public Position(double aXPosition, double aYPosition/*, double aWidth, double aHeight*/)
	{
		fXPosition = aXPosition;
		fYPosition = aYPosition;
//		fWidth = aWidth;
//		fHeight = aHeight;
	}
	
	public double getX()
	{
		return fXPosition;
	}
	public double getY()
	{
		return fYPosition;
	}
//	double getWidth()
//	{
//		return fWidth;
//	}
//	double getHeight()
//	{
//		return fHeight;
//	}
}