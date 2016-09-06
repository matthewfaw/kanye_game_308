package controllers;

import java.util.ArrayList;

import javafx.scene.Group;
import views.elements.foreground.characters.MainCharacter;
import views.elements.foreground.obstacles.Obstacle;

/*
 * The purpose of the Character Controller is to:
 * 1. Handle character movement
 * 2. Update the player info
 */

public class CharacterController {
	private static final double DEL_X = 1.0;
	private static final double DEL_Y = 1.0;
	private static final double JUMP_VELOCITY = 5.0;
	
	private MainCharacter fCharacter;
	private ArrayList<Obstacle> fSurroundingObstacles;
	private double fVelocityX;
	private double fVelocityY;
	private double fTimeInAir;
	private boolean fOnGround;
	
	public CharacterController()
	{
		fSurroundingObstacles = new ArrayList<Obstacle>();
	}
	
	public void setSurroundings(ArrayList<Obstacle> aObstacleList)
	{
		fSurroundingObstacles = aObstacleList;
	}
		
	public Group createCharacter(int aWidth, int aHeight)
	{
		fCharacter = new MainCharacter(aWidth, aHeight);
		fCharacter.setX(100);
		fCharacter.setY(250);
		
		fVelocityX = 0.0;
		fVelocityY = 0.0;
		fTimeInAir = 0.0;
		fOnGround = true;
		
		Group characterRoot = fCharacter.getRoot();
		
		return characterRoot;
	}
	
	public void moveCharacter(double aXUnit, double aYUnit)
	{
		fCharacter.setX(fCharacter.getX() + aXUnit * DEL_X);
		fCharacter.setY(fCharacter.getY() + aYUnit * DEL_Y);
	}
	
	public void beginJump()
	{
		fOnGround = false;
		fVelocityY = JUMP_VELOCITY;
	}
	
	public void updatePosition(double aElapsedTime, double aGravity)
	{
		if (fVelocityY != 0.0) {
			fTimeInAir += aElapsedTime;
			fVelocityY = aGravity*fTimeInAir + JUMP_VELOCITY;
			
			double displacementY = 0.5*aGravity*fTimeInAir*fTimeInAir + JUMP_VELOCITY*fTimeInAir;

			this.moveCharacter(0, -displacementY);
		} 
		if (!surroundingsAreClear()) {
			System.out.println("INTERSECTING SOMETHING");
		}
	}
	
	private boolean surroundingsAreClear()
	{
		for (Obstacle obstacle: fSurroundingObstacles) {
			if (fCharacter.getRoot().getBoundsInParent().intersects(obstacle.getRoot().getBoundsInParent())) {
				if (!fOnGround && fTimeInAir > 0.5) {
					endJump();
				}
				return false;
			}
		}
		return true;
	}
	
	private void endJump()
	{
		fTimeInAir = 0.0;
		fVelocityY = 0.0;
		fOnGround = true;
	}
}