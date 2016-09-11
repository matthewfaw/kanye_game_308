package controllers;
/*
 * The purpose of the game controller is to:
 * 1. Initialize the game
 * 2. Step to the next instant in time of the game
 * 3. Reset the game
 */

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import models.PlayerStats;
import utils.PictureNames;
import views.elements.foreground.characters.Character;
import views.elements.foreground.characters.Enemy;
import views.elements.foreground.characters.MainCharacter;
import views.elements.foreground.obstacles.Tunnel;
import views.scenes.CollegeScene;
import views.scenes.DoorExplorationScene;
import views.scenes.ForestScene;
import views.scenes.GameScene;

public class GameController {
	private static final String GAME_NAME = "Kanye's Quest for the Ultralight Beam";
	private static final Color BACKGROUND_COLOR = Color.WHITE;
	private static final int KEY_INPUT_SPEED = 5;
	private static final double GRAVITY = -10;
	private static final double FULL_HEALTH = 100.0;
	private static final double HEALTH_DEDUCTION = -1.0;

	private static final int COLLEGE_SCENE_INDEX = 0;
	private static final int FOREST_SCENE_INDEX = 1;
	private static final int DOOR_SCENE_INDEX = 2;
	
	private SceneController fSceneController;
	private ArrayList<GameScene> fGameScenes;
	private MainCharacterController fMainCharacterController;
	private ArrayList<EnemyController> fEnemyControllers;
	private Scene fScene;
	private PlayerStats fPlayerStats;
	private int fCurrentSceneIndex;
	
	public String getGameName()
	{
		return GAME_NAME;
	}
	
	public Scene init(int aWidth, int aHeight)
	{
		fSceneController = new SceneController();
		fSceneController.createGameRoot(aWidth, aHeight);
		
//		GameScene initialScene = fSceneController.createScenes(aWidth, aHeight);
		fGameScenes = fSceneController.createScenes(aWidth, aHeight);
		
		fMainCharacterController = new MainCharacterController();
		fMainCharacterController.setSurroundings(fGameScenes.get(COLLEGE_SCENE_INDEX));
		fCurrentSceneIndex = COLLEGE_SCENE_INDEX;
		Character kanye = fMainCharacterController.createMainCharacter(aWidth/8, aHeight/8);
		
		fEnemyControllers = new ArrayList<EnemyController>();

		fPlayerStats = new PlayerStats();
		updateHealth(FULL_HEALTH);

		fSceneController.addToGameRoot(fGameScenes.get(COLLEGE_SCENE_INDEX));
		fSceneController.addToGameRoot(kanye);
		
		fScene = new Scene(fSceneController.getGameRoot(), aWidth, aHeight, BACKGROUND_COLOR);
        fScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));

		return fScene;
	}
	
	public void step(double aElapsedTime)
	{
		moveCharacters(aElapsedTime);
		updateHealth();

		Tunnel tunnelToTransitionThrough = fMainCharacterController.checkForSceneTransition();
		if (tunnelToTransitionThrough != null) {
			handleSceneTransition(tunnelToTransitionThrough.getDst());
		}
	}
	
	private void moveCharacters(double aElapsedTime)
	{
		fMainCharacterController.checkForFreefall();
		fMainCharacterController.updatePosition(aElapsedTime, GRAVITY);
		for (EnemyController enemyController: fEnemyControllers) {
			enemyController.moveCharacter();
		}
	}
	
	private void updateHealth()
	{
		if (fMainCharacterController.isTouchingAnEnemy()) {
			updateHealth(fPlayerStats.getHealth() + HEALTH_DEDUCTION);
		}
	}
	
	private void handleSceneTransition(GameScene aDstScene)
	{
		fSceneController.transportToNewScene(aDstScene);
		fEnemyControllers.clear();
		fMainCharacterController.clearEnemies();

		if (aDstScene instanceof CollegeScene) {
			fCurrentSceneIndex = COLLEGE_SCENE_INDEX;
		} else if (aDstScene instanceof ForestScene) {
			addEnemyToGame(aDstScene, PictureNames.Camera);
			fCurrentSceneIndex = FOREST_SCENE_INDEX;
		} else if (aDstScene instanceof DoorExplorationScene) {
			addEnemyToGame(aDstScene, PictureNames.Taylor);
			fCurrentSceneIndex = DOOR_SCENE_INDEX;
		}

		fMainCharacterController.setSurroundings(aDstScene);
	}
	
	private void addEnemyToGame(GameScene aScene, String aEnemyFileName)
	{
		EnemyController enemyController = new EnemyController();
		enemyController.setSurroundings(aScene);
		Enemy enemy = enemyController.createEnemy(50, 50, aEnemyFileName);
		fEnemyControllers.add(enemyController);

		fSceneController.addToGameRoot(enemy);

		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		enemies.add(enemy);
		fMainCharacterController.setCurrentEnemies(enemies);
	}
	
	private void updateHealth(double aValue)
	{
		fPlayerStats.setHealth(aValue);
		fSceneController.updateHealthBar(aValue);
	}
	
	private void changeScene()
	{
		int nextSceneIndex = (fCurrentSceneIndex + 1) % fGameScenes.size();
		handleSceneTransition(fGameScenes.get(nextSceneIndex));
	}
	
	private void handleKeyInput(KeyCode code)
	{
		switch (code) {
        case RIGHT:
    		fMainCharacterController.moveCharacter(KEY_INPUT_SPEED, 0);
            break;
        case LEFT:
    		fMainCharacterController.moveCharacter(-KEY_INPUT_SPEED, 0);
            break;
        case UP:
        	// XXX remove if statements
        	if (fMainCharacterController.isInAJumpingScene()) {
        		fMainCharacterController.beginJump();
        	} else {
        		fMainCharacterController.moveCharacter(0, -KEY_INPUT_SPEED);
        	}            	
            break;
        case DOWN:
    		if (!fMainCharacterController.isInAJumpingScene()) {
            	fMainCharacterController.moveCharacter(0, KEY_INPUT_SPEED);
    		}
            break;
        case T:
        	changeScene();
        default:
            // do nothing
		}
	}
}