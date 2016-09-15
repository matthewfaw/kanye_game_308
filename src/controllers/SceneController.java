package controllers;

import java.util.ArrayList;

import javafx.scene.Group;
import views.elements.foreground.attack.Fireball;
import views.elements.foreground.characters.Character;
import views.elements.foreground.characters.MainCharacter;
import views.elements.foreground.data_displays.HealthBar;
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

/**
 * The purpose of the scene controller is:
 * 1) to handle setting up a given scene properly
 * 2) To handle the break down and build up of scenes upon scene transition.
 * 
 * The main assumptions of the class are that a user has passed in a new scene or scene element 
 * (i.e. that this element is not void). 
 * Additionally, the class assumes that, after constructing the scene controller, a user calls
 * the createGameRoot method, specifying the width and height of the scene. This width and height
 * are assumed to be valid
 * 
 * Additionally, it is assumed that a user calls createScenes when the game starts to set up all of the 
 * Scene objects.  The user then can select which scene he wants to be displayed first, and add that scene
 * to the game root, as follows:
 * SceneController sceneController = new SceneController();
 * sceneController.createGameRoot(width, height);
 * ArrayList<GameScene> scenes = sceneController.createScenes(width, height, gameName, gameInfo);
 * If a user wants to display the start screen:
 * sceneController.addToGameRoot(sceneController.getStartScreen());
 * If a user wants to display a game scene
 * sceneController.addToGameRoot(scenes.get(INDEX_OF_SCENE))
 * 
 * @author matthewfaw
 *
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

	/**
	 * 
	 */
	public SceneController()
	{
	}
	
	/**
	 * Creates the game root, to which all scene elements are added
	 * @param aWidth
	 * @param aHeight
	 */
	public void createGameRoot(int aWidth, int aHeight)
	{
		fGameRoot = new Group();
		fSceneWidth = aWidth;
		fSceneHeight = aHeight;
	}
	
	/**
	 * get the width of the scene
	 * @return scene width
	 */
	public int getWidth()
	{
		return fSceneWidth;
	}
	
	/**
	 * get the scene height
	 * @return scene height
	 */
	public int getHeight()
	{
		return fSceneHeight;
	}
	
	/**
	 * gets the root node
	 * @return root node
	 */
	public Group getGameRoot()
	{
		return fGameRoot;
	}
	
	/**
	 * gets the game scene the game is currently in
	 * @return the current game
	 */
	public GameScene getCurrentScene()
	{
		return fCurrentScene;
	}
	
	/**
	 * Gets the splash screen of the game
	 * @return the object associated with the spash screen
	 */
	public StartScreen getStartScreen()
	{
		return fStartScreen;
	}
	
	/**
	 * Sets the health value displayed on the health bar to aValue
	 * @param aValue
	 */
	public void updateHealthBar(double aValue)
	{
		fHealthBar.setHealthBarPercentage(aValue);
	}
	
	/**
	 * Sets the gold count displayed on the health bar to aValue
	 * @param aValue
	 */
	public void updateGoldCount(int aValue)
	{
		fHealthBar.setGoldCount(aValue);
	}
	
	/**
	 * Manages all the root node manipulations necessary to transition to a new scene
	 * @param aNewScene
	 */
	public void transportToNewScene(GameScene aNewScene)
	{
		clearGameRoot();
		addToGameRoot(aNewScene);
		//XXX: seems pretty weird to pass a field as an input arg...
		addToGameRoot(fMainCharacter);
	}
	
	/**
	 * Removes the gold from the scene
	 * @param gold
	 */
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
	/**
	 * Adds a new scene, and the associated health bar, to the screen
	 * @param aDstScene
	 */
	public void addToGameRoot(GameScene aDstScene)
	{
		fCurrentScene = aDstScene;
		// Placing the 0 in the add method forces the game scene to be added to the back
		// This is useful, as it makes sure the health bar is always on top
		fGameRoot.getChildren().add(0, fHealthBar.getRoot());
		fGameRoot.getChildren().add(0, aDstScene.getRoot());
	}
	
	/**
	 * Adds a screen to the game root without adding the health bar
	 * @param aStartScreen
	 */
	public void addToGameRoot(NonGameScreen aStartScreen)
	{
		fGameRoot.getChildren().add(aStartScreen.getRoot());
	}
	
	/**
	 * Adds a character to the scene
	 * @param aCharacter
	 */
	public void addToGameRoot(Character aCharacter)
	{
		if (aCharacter instanceof MainCharacter) {
			fMainCharacter = (MainCharacter) aCharacter;
		}
		fGameRoot.getChildren().add(aCharacter.getRoot());
	}

	/**
	 * Adds a fireball to the display
	 * @param aFireball
	 */
	public void addToGameRoot(Fireball aFireball)
	{
		fGameRoot.getChildren().add(aFireball.getRoot());
	}
	/**
	 * Adds gold to the display
	 * @param aGold
	 */
	public void addToGameRoot(Gold aGold)
	{
		fGameRoot.getChildren().add(aGold.getRoot());
	}
	
//	public EndScreen getWinningScreen()
//	{
//		fEndScreen.setTitle("You won!");
//		return fEndScreen;
//	}
	/*
	 * Display to the user that they have lost the game
	 */
	public EndScreen getLosingScreen()
	{
		fEndScreen.setTitle("You lost :(");
		return fEndScreen;
	}
	
	//XXX: perhaps make this more customizable?
	// Create all scenes of the game, and return the first one
	/**
	 * Creates all the game scenes
	 * @param aWidth
	 * @param aHeight
	 * @param aGameName
	 * @param aGameInfo
	 * @return the game scenes, packaged into an array
	 */
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
	}
}