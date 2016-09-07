package controllers;

import java.util.ArrayList;

import javafx.scene.Group;
import views.elements.foreground.characters.MainCharacter;
import views.elements.foreground.obstacles.Obstacle;
import views.elements.foreground.obstacles.Tunnel;

/*
 * The purpose of the Character Controller is to:
 * 1. Handle character movement
 * 2. Update the player info
 */

public class CharacterController {
	private static final double DEL_X = 1.0;
	private static final double DEL_Y = 1.0;
	private static final double JUMP_VELOCITY = 7.0;
	private static final double FREEFALL_VELOCITY = 0.0;
	
	private Group fGameRoot;
	
	private MainCharacter fCharacter;
	private ArrayList<Obstacle> fSurroundingObstacles;
	private double fVelocityX;
	private double fVelocityY;
	private double fTimeInAir;
	private boolean fOnGround;
	private boolean fIsFalling;
	
	private SceneController fSceneController;
		
	public CharacterController(Group aGameRoot)
	{
		fGameRoot = aGameRoot;
		fSurroundingObstacles = new ArrayList<Obstacle>();
		fSceneController = new SceneController(aGameRoot);
	}
	
	public void setSurroundings(ArrayList<Obstacle> aObstacleList)
	{
		fSurroundingObstacles = aObstacleList;
	}
	
	public void checkForSceneTransition()
	{
		for (Obstacle obstacle: fSurroundingObstacles) {
			if (obstacle instanceof Tunnel) {
				if (fCharacter.intersects(obstacle.getRoot())) {
					Tunnel tunnel = (Tunnel) obstacle;
					
					transportCharacterToNewScene(tunnel);
					return;
				}
				//GameScene dstScene = tunnel.getDstScene();
			}
		}
	}
	
	private void transportCharacterToNewScene(Tunnel aTunnel) 
	{
		fSceneController.changeScenes(aTunnel.getSrcRoot(), aTunnel.getDstRoot());
		// XXX: delete this once dest scene has been created
		fSurroundingObstacles.clear();
		//fSurroundingObstacles = aTunnel.getDstScene().getObstacles();
	}
		
	public Group createCharacter(int aWidth, int aHeight)
	{
		fCharacter = new MainCharacter(aWidth, aHeight);
		fCharacter.setX(200);
//		fCharacter.setY(250);
		fCharacter.setY(0);
		
		fVelocityX = 0.0;
		fVelocityY = 0.0;
		fTimeInAir = 0.0;
		fOnGround = true;
		
		Group characterRoot = fCharacter.getRoot();
		
		return characterRoot;
	}
	
	public void moveCharacter(double aXUnit, double aYUnit)
	{
		// XXX: fix movement
		if (aXUnit > 0 && surroundingsAreClearOnRight()) {
			fCharacter.setX(fCharacter.getX() + aXUnit * DEL_X);
		} else if (aXUnit < 0 && surroundingsAreClearOnLeft()) {
			fCharacter.setX(fCharacter.getX() + aXUnit * DEL_X);
		}
		fCharacter.setY(fCharacter.getY() + aYUnit * DEL_Y);
	}
	
	public void checkForFreefall()
	{
		//Check if we're standing on thin air:
		if (surroundingsAreClearBelow() && fOnGround) {
			fOnGround = false;
			fIsFalling = true;
			System.out.println("Freefall");
			fVelocityY = FREEFALL_VELOCITY;
		} else {
			fIsFalling = (false || fIsFalling);
		}
	}
	
	public void beginJump()
	{
		fOnGround = false;
		fVelocityY = JUMP_VELOCITY;
	}
	
	// XXX: possibly rename, this function name is hard to distinguish from moveCharacter
	public void updatePosition(double aElapsedTime, double aGravity)
	{
		if (movingInYAxis()) {
			fTimeInAir += aElapsedTime;
			//fVelocityY = aGravity*fTimeInAir + JUMP_VELOCITY;
			
			double displacementY = 0.5*aGravity*fTimeInAir*fTimeInAir + fVelocityY*fTimeInAir;

			this.moveCharacter(0, -displacementY);
			//XXX: get rid of or name magic constant
			if(!surroundingsAreClearBelow() && fTimeInAir > 0.5){
				endJump();
			}
		} 
	}
	
	private boolean surroundingsAreClearOnLeft()
	{
		for (Obstacle obstacle: fSurroundingObstacles) {
			if (fCharacter.intersects(obstacle.getRoot())) {
				if (fCharacter.intersectsFromLeft(obstacle.getRoot())) {
					return false;
				}
			}
		}
		return true;
	}
	private boolean surroundingsAreClearOnRight()
	{
		for (Obstacle obstacle: fSurroundingObstacles) {
			//Shape intersection = Shape.intersect(fCharacter.getRoot(), obstacle.getRoot());

			if (fCharacter.intersects(obstacle.getRoot())) {
				if (fCharacter.intersectsFromRight(obstacle.getRoot())) {
					return false;
				}
			}
		}
		return true;
	}
	private boolean surroundingsAreClearAbove()
	{
		for (Obstacle obstacle: fSurroundingObstacles) {
			if (fCharacter.intersects(obstacle.getRoot())) {
				if (fCharacter.intersectsFromAbove(obstacle.getRoot())) {
					return false;
				}
			}
		}
		return true;
	}
	private boolean surroundingsAreClearBelow()
	{
		for (Obstacle obstacle: fSurroundingObstacles) {
			if (fCharacter.intersects(obstacle.getRoot())) {
				if (fCharacter.intersectsFromBelow(obstacle.getRoot())) {
					return false;
				}
			}
		}
		return true;
	}
	
	private void endJump()
	{
		fTimeInAir = 0.0;
		fVelocityY = 0.0;
		fOnGround = true;
		fIsFalling = false;
	}
	
	private boolean movingInYAxis()
	{
		return (fVelocityY != 0.0 || fIsFalling);
	}
	
}