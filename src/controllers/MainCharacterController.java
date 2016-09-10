package controllers;

import java.util.ArrayList;

import views.elements.foreground.characters.Character;
import views.elements.foreground.characters.Enemy;
import views.elements.foreground.characters.MainCharacter;
import views.elements.foreground.obstacles.Obstacle;
import views.elements.foreground.obstacles.Tunnel;

public class MainCharacterController extends CharacterController {
	
	private ArrayList<Enemy> fSurroundingEnemies;

	public MainCharacterController()
	{
		super();
		fSurroundingEnemies = new ArrayList<Enemy>();
	}

	public MainCharacter createMainCharacter(int aWidth, int aHeight)
	{
		fCharacter = new MainCharacter(aWidth, aHeight);
		initializeCharacterFields();
		
		return ((MainCharacter) fCharacter);
	}
	
	protected void initializeCharacterFields()
	{
		fCharacter.setX(200);
		fCharacter.setY(0);
		
//		fVelocityX = 0.0;
		fVelocityY = 0.0;
		fTimeInAir = 0.0;
		fOnGround = true;
	}
	
	public void setCurrentEnemies(ArrayList<Enemy> aEnemies)
	{
		fSurroundingEnemies = aEnemies;
	}
	
	public void clearEnemies()
	{
		fSurroundingEnemies.clear();
	}
		
	public Tunnel checkForSceneTransition()
	{
		for (Obstacle obstacle: fSurroundingObstacles) {
			if (obstacle instanceof Tunnel && fCharacter.intersects(obstacle.getRoot())) {
				Tunnel tunnel = (Tunnel) obstacle;

				return tunnel;
			}
		}
		return null;
	}
	
	public boolean isTouchingAnEnemy()
	{
		for (Enemy enemy: fSurroundingEnemies) {
			if (fCharacter.intersects(enemy.getRoot())) {
				return true;
			}
		}
		return false;
	}
	
}