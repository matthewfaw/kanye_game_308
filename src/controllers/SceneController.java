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
import views.scenes.EndScreen;
import views.scenes.ForestScene;
import views.scenes.GameScene;
import views.scenes.NonGameScreen;
import views.scenes.StartScreen;
import views.scenes.UltralightBeamScene;

/*
 * The purpose of the scene controller is to:
 * 1. Handle setting up a given scene
 * 2. Handle scene transitions
 */

public class SceneController {
	private Group fGameRoot;
	private HealthBar fHealthBar;
	private StartScreen fStartScreen;
	private EndScreen fEndScreen;
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
	
	public int getWidth()
	{
		return fSceneWidth;
	}
	
	public int getHeight()
	{
		return fSceneHeight;
	}
	
	public Group getGameRoot()
	{
		return fGameRoot;
	}
	
	public GameScene getCurrentScene()
	{
		return fCurrentScene;
	}
	
	public StartScreen getStartScreen()
	{
		return fStartScreen;
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
	
	//NOTE: I'm overloading this method and repeating some code to restrict this
	// method's usage to only certain classes
	public void addToGameRoot(GameScene aDstScene)
	{
		fCurrentScene = aDstScene;
		fGameRoot.getChildren().add(0, fHealthBar.getRoot());
		fGameRoot.getChildren().add(0, aDstScene.getRoot());
	}
	
	public void addToGameRoot(NonGameScreen aStartScreen)
	{
		fGameRoot.getChildren().add(aStartScreen.getRoot());
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
	
	public EndScreen getWinningScreen()
	{
		fEndScreen.setTitle("You won!");
		return fEndScreen;
	}
	public EndScreen getLosingScreen()
	{
		fEndScreen.setTitle("You lost :(");
		return fEndScreen;
	}
	
	//XXX: perhaps make this more customizable?
	// Create all scenes of the game, and return the first one
	public ArrayList<GameScene> createScenes(int aWidth, int aHeight, String aGameName, String aGameInfo)
	{
		fHealthBar = new HealthBar(aWidth, aHeight/10);
		fStartScreen = new StartScreen(aWidth, aHeight, aGameName, aGameInfo);
		fEndScreen = new EndScreen(aWidth, aHeight);

		CollegeScene collegeScene = new CollegeScene(aWidth, aHeight);
		ForestScene forestScene = new ForestScene(aWidth, aHeight);
		DoorExplorationScene doorExplorationScene = new DoorExplorationScene(aWidth, aHeight);
		UltralightBeamScene ultralightBeamScene = new UltralightBeamScene(aWidth, aHeight);

		collegeScene.getDstTunnel().setDst(forestScene);
		forestScene.getSrcTunnel().setDst(collegeScene);
		forestScene.getDstTunnel().setDst(doorExplorationScene);
		doorExplorationScene.getSrcTunnel().setDst(forestScene);
		doorExplorationScene.getDstTunnel().setDst(ultralightBeamScene);

		ArrayList<GameScene> gameScenes = new ArrayList<GameScene>();
		gameScenes.add(collegeScene);
		gameScenes.add(forestScene);
		gameScenes.add(doorExplorationScene);
		gameScenes.add(ultralightBeamScene);
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