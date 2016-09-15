package utils;

/**
 * The purpose of this class is to simplify the specification of movement
 * direction.  It explicitly defines that the directions of movement in the 
 * game are left, right, up, and down.  This allows users to easily specify in their code
 * in what direction they want an object to move, while maintaining code readibility
 * 
 * The class assumes that movement is in two dimensions.
 * 
 * The class depends on the Vector class in the utils package.
 * 
 * To use this class, just access one of it's members: For example, if we had a method
 * move(Vector aDirectionVector), we could call this method using:
 * 
 * move(Direction.LEFT);
 * 
 * @author matthewfaw
 *
 */

public final class Direction {
	public static final Vector LEFT = new Vector(-1,0);
	public static final Vector RIGHT = new Vector(1,0);
	public static final Vector UP = new Vector(0,-1);
	public static final Vector DOWN = new Vector(0,1);
}