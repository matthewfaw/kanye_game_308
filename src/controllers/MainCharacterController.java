package controllers;

import java.util.ArrayList;

import utils.PictureNames;
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

	public void createMainCharacter(int aWidth, int aHeight)
	{
		fCharacter = new MainCharacter(aWidth, aHeight);
		initializeCharacterFields();
		
//		return ((MainCharacter) fCharacter);
	}
	
	public MainCharacter getMainCharacter()
	{
		return (MainCharacter)fCharacter;
	}
	
	protected void initializeCharacterFields()
	{
		fCharacter.setX(200);
		fCharacter.setY(50);
		
//		fVelocityX = 0.0;
		fVelocityY = 0.0;
		fTimeInAir = 0.0;
		fOnGround = true;
	}
	
//	public void setCurrentEnemies(ArrayList<Enemy> aEnemies)
//	{
//		fSurroundingEnemies = aEnemies;
//	}
	
	public void addEnemy(Enemy aNewEnemy)
	{
		fSurroundingEnemies.add(aNewEnemy);
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
	
	public Enemy killedAnActiveEnemy()
	{
		for (Enemy enemy: fSurroundingEnemies) {
			if (fCharacter.intersectsFromBelow(enemy.getRoot()) && enemy.isActive()) {
				if (!enemy.getPictureName().equals(PictureNames.Taylor)) {
					return enemy;
				}
			}
		}
		return null;
	}

	public boolean isBeingHurtByAnActiveEnemy()
	{
		for (Enemy enemy: fSurroundingEnemies) {
			if ((fCharacter.intersectsFromLeft(enemy.getRoot()) || fCharacter.intersectsFromRight(enemy.getRoot())) 
					&& enemy.isActive()) {
				return true;
			}
		}
		return false;
	}
	
}