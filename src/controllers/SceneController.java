package controllers;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Node;
import views.elements.foreground.attack.Fireball;
import views.elements.foreground.characters.Character;
import views.elements.foreground.characters.MainCharacter;
import views.elements.foreground.data_displays.HealthBar;
import views.elements.foreground.obstacles.Tunnel;
import views.elements.foreground.rewards.Gold;
import views.scenes.CollegeScene;
import views.scenes.DoorExplorationScene;
import views.scenes.ForestScene;
import views.scenes.GameScene;

/*
 * The purpose of the scene controller is to:
 * 1. Handle setting up a given scene
 * 2. Handle scene transitions
 */

public class SceneController {
	private Group fGameRoot;
	private HealthBar fHealthBar;
	private GameScene fCurrentScene;
	private MainCharacter fMainCharacter;
	private int fSceneWidth;
	private int fSceneHeight;
	
	public SceneController()
	{
	}
	
	public void createGameRoot(int aWidth, int aHeight)
	{
		fGameRoot = new Group();
		fSceneWidth = aWidth;
		fSceneHeight = aHeight;
	}
	
	public Group getGameRoot()
	{
		return fGameRoot;
	}
	
	public GameScene getCurrentScene()
	{
		return fCurrentScene;
	}
	
	public void updateHealthBar(double aValue)
	{
		fHealthBar.setHealthBarPercentage(aValue);
	}
	
	public void updateGoldCount(int aValue)
	{
		fHealthBar.setGoldCount(aValue);
	}
	
	public void transportToNewScene(GameScene aNewScene)
	{
		clearGameRoot();
		addToGameRoot(aNewScene);
		//XXX: seems pretty weird to pass a field as an input arg...
		addToGameRoot(fMainCharacter);
	}
	
	public void removeGold(Gold gold)
	{
		gold.getRoot().getChildren().clear();
	}
	
	private void clearGameRoot()
	{
		fGameRoot.getChildren().clear();
	}
	
	public void addToGameRoot(GameScene aDstScene)
	{
		fCurrentScene = aDstScene;
		fGameRoot.getChildren().add(0, fHealthBar.getRoot());
		fGameRoot.getChildren().add(0, aDstScene.getRoot());
	}
	
	public void addToGameRoot(Character aCharacter)
	{
		if (aCharacter instanceof MainCharacter) {
			fMainCharacter = (MainCharacter) aCharacter;
		}
		fGameRoot.getChildren().add(aCharacter.getRoot());
	}

	public void addToGameRoot(Fireball aFireball)
	{
		fGameRoot.getChildren().add(aFireball.getRoot());
	}
	public void addToGameRoot(Gold aGold)
	{
		fGameRoot.getChildren().add(aGold.getRoot());
	}
	
	//XXX: perhaps make this more customizable?
	// Create all scenes of the game, and return the first one
	public ArrayList<GameScene> createScenes(int aWidth, int aHeight)
	{
		fHealthBar = new HealthBar(aWidth, aHeight/10);
		
		CollegeScene collegeScene = new CollegeScene(aWidth, aHeight);
		ForestScene forestScene = new ForestScene(aWidth, aHeight);
		DoorExplorationScene doorExplorationScene = new DoorExplorationScene(aWidth, aHeight);

		collegeScene.getDstTunnel().setDst(forestScene);
		forestScene.getSrcTunnel().setDst(collegeScene);
		forestScene.getDstTunnel().setDst(doorExplorationScene);
		doorExplorationScene.getSrcTunnel().setDst(forestScene);

		ArrayList<GameScene> gameScenes = new ArrayList<GameScene>();
		gameScenes.add(collegeScene);
		gameScenes.add(forestScene);
		gameScenes.add(doorExplorationScene);
		return gameScenes;
//		GameScene initialScene = collegeScene;
//		return initialScene;
	}
	
//	public void addEnemiesToScene()
//	{
////		CharacterController enemyController = new CharacterController(fGameRoot);
////		Group enemyRoot = enemyController.createEnemy(50,50);
////		fGameRoot.getChildren().add(enemyRoot);
//		
//	}
}