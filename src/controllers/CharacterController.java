package controllers;

import java.util.ArrayList;

import javafx.scene.Group;
import views.elements.foreground.characters.*;
import views.elements.foreground.characters.Character;
import views.elements.foreground.obstacles.Obstacle;
import views.elements.foreground.obstacles.Tunnel;
import views.scenes.DoorExplorationScene;
import views.scenes.GameScene;

/*
 * The purpose of the Character Controller is to:
 * 1. Handle character movement
 * 2. Update the player info
 */

public abstract class CharacterController {
	private static final double DEL_X = 1.0;
	private static final double DEL_Y = 1.0;
	private static final double JUMP_VELOCITY = 7.0;
	private static final double FREEFALL_VELOCITY = 0.0;
	
	private GameScene fCurrentScene;
	
	protected Character fCharacter;
	protected ArrayList<Obstacle> fSurroundingObstacles;
//	private ArrayList<Enemy> fSurroundingEnemies;
	protected double fVelocityX;
	protected double fVelocityY;
	protected double fTimeInAir;
	protected boolean fOnGround;
	private boolean fIsFalling;
	private boolean fGravityIsEnabled;
	
	public CharacterController()
	{
		fSurroundingObstacles = new ArrayList<Obstacle>();
//		fSurroundingEnemies = new ArrayList<Enemy>();
	}
		
	public void setSurroundings(GameScene aScene)
	{
		fSurroundingObstacles = aScene.getObstacles();
		fGravityIsEnabled = !(aScene instanceof DoorExplorationScene);
		
		if (fCharacter != null) {
			initializeCharacterFields();
		}
	}
	
	public boolean isInAJumpingScene()
	{
		return fGravityIsEnabled;
	}
	
	public GameScene getCurrentScene()
	{
		return fCurrentScene;
	}
	
	protected abstract void initializeCharacterFields();
	
	public void moveCharacter(double aXUnit, double aYUnit)
	{
		// XXX: fix movement
		if ( (aXUnit > 0 && surroundingsAreClearOnRight()) ||
				(aXUnit < 0 && surroundingsAreClearOnLeft()) ) {
			fCharacter.setX(fCharacter.getX() + aXUnit * DEL_X);
		}
		if ( (aYUnit > 0 && surroundingsAreClearBelow()) ||
				(aYUnit < 0 && surroundingsAreClearAbove())) {
			fCharacter.setY(fCharacter.getY() + aYUnit * DEL_Y);
		}
	}
	
	public void checkForFreefall()
	{
		//Check if we're standing on thin air:
		if (surroundingsAreClearBelow() && fOnGround && fGravityIsEnabled) {
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
	
	protected boolean surroundingsAreClearOnLeft()
	{
		for (Obstacle obstacle: fSurroundingObstacles) {
			if (fCharacter.intersects(obstacle.getRoot())) {
				if (fCharacter.intersectsFromLeft(obstacle.getRoot())) {
					return false;
				}
			}
		}
//		for (Enemy enemy: fSurroundingEnemies) {
//			if (fCharacter.intersects(enemy.getRoot())) {
//				if (fCharacter.intersectsFromLeft(enemy.getRoot())) {
//					return false;
//				}
//			}
//		}
		return true;
	}
	protected boolean surroundingsAreClearOnRight()
	{
		for (Obstacle obstacle: fSurroundingObstacles) {
			//Shape intersection = Shape.intersect(fCharacter.getRoot(), obstacle.getRoot());

			if (fCharacter.intersects(obstacle.getRoot())) {
				if (fCharacter.intersectsFromRight(obstacle.getRoot())) {
					return false;
				}
			}
		}
//		for (Enemy enemy: fSurroundingEnemies) {
//			if (fCharacter.intersects(enemy.getRoot())) {
//				if (fCharacter.intersectsFromLeft(enemy.getRoot())) {
//					return false;
//				}
//			}
//		}
		return true;
	}
	protected boolean surroundingsAreClearAbove()
	{
		for (Obstacle obstacle: fSurroundingObstacles) {
			if (fCharacter.intersects(obstacle.getRoot())) {
				if (fCharacter.intersectsFromAbove(obstacle.getRoot())) {
					return false;
				}
			}
		}
//		for (Enemy enemy: fSurroundingEnemies) {
//			if (fCharacter.intersects(enemy.getRoot())) {
//				if (fCharacter.intersectsFromLeft(enemy.getRoot())) {
//					return false;
//				}
//			}
//		}
		return true;
	}
	protected boolean surroundingsAreClearBelow()
	{
		for (Obstacle obstacle: fSurroundingObstacles) {
			if (fCharacter.intersects(obstacle.getRoot())) {
				if (fCharacter.intersectsFromBelow(obstacle.getRoot())) {
					return false;
				}
			}
		}
//		for (Enemy enemy: fSurroundingEnemies) {
//			if (fCharacter.intersects(enemy.getRoot())) {
//				if (fCharacter.intersectsFromLeft(enemy.getRoot())) {
//					return false;
//				}
//			}
//		}
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