package controllers;

import java.util.ArrayList;

import utils.PictureNames;
import utils.Vector;
import views.elements.foreground.attack.Fireball;
import views.elements.foreground.characters.Character;
import views.elements.foreground.characters.Enemy;
import views.elements.foreground.characters.MainCharacter;
import views.elements.foreground.obstacles.Obstacle;
import views.elements.foreground.obstacles.Tunnel;
import views.elements.foreground.rewards.Gold;

public class MainCharacterController extends CharacterController {
	
	private ArrayList<Enemy> fSurroundingEnemies;
	private ArrayList<Fireball> fFireballs;
	private ArrayList<Gold> fSurroundingGold;

	public MainCharacterController()
	{
		super();
		fSurroundingEnemies = new ArrayList<Enemy>();
		fFireballs = new ArrayList<Fireball>();
		fSurroundingGold = new ArrayList<Gold>();
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
	
	public void addGold(Gold aNewGoldPiece)
	{
		fSurroundingGold.add(aNewGoldPiece);
	}
	
	public Gold isTouchingGold()
	{
		for (Gold gold: fSurroundingGold) {
			if (fCharacter.intersects(gold.getRoot())) {
				return gold;
			}
		}
		return null;
	}
	
	public void removeGold(Gold aGold)
	{
		fSurroundingGold.remove(aGold);
	}

	public void emptyBelongings()
	{
		fSurroundingEnemies.clear();
		fFireballs.clear();
		fSurroundingGold.clear();
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
	
	public Fireball spitFire(Vector aDirection)
	{
		Fireball fireball = new Fireball(aDirection);
		fireball.setX(fCharacter.getX());
		fireball.setY(fCharacter.getY());
		fFireballs.add(fireball);
		
		return fireball;
	}

	public void moveFireballs()
	{
		// XXX: fix movement
		for (Fireball fireball: fFireballs) {
			fireball.setX(fireball.getX() + fireball.getXDirection() * DEL_X);
			fireball.setY(fireball.getY() + fireball.getYDirection() * DEL_Y);
		}
	}
	
	public Enemy killedAnActiveEnemy()
	{
		for (Enemy enemy: fSurroundingEnemies) {
			if (fCharacter.intersectsFromBelow(enemy.getRoot()) && enemy.isActive()) {
				if (!enemy.getPictureName().equals(PictureNames.Taylor)) {
					return enemy;
				}
			}
			for (Fireball fireball: fFireballs) {
				if (fireball.intersects(enemy.getRoot())) {
					return enemy;
				}
			}
		}
		return null;
	}

	public boolean isBeingHurtByAnActiveEnemy()
	{
		for (Enemy enemy: fSurroundingEnemies) {
			if (fCharacter.intersects(enemy.getRoot()) && enemy.isActive()) {
//				if ((fCharacter.intersectsFromLeft(enemy.getRoot()) || fCharacter.intersectsFromRight(enemy.getRoot())) 
//						&& enemy.isActive()) {
					return true;
//				}
			}
		}
		return false;
	}
	
}