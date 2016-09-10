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
	
	private SceneController fSceneController;
	private CharacterController fMainCharacterController;
	private ArrayList<CharacterController> fEnemyControllers;
	private Scene fScene;
	
	public String getGameName()
	{
		return GAME_NAME;
	}
	
	public Scene init(int aWidth, int aHeight)
	{
		fSceneController = new SceneController();
		fSceneController.createGameRoot(aWidth, aHeight);
		
		GameScene initialScene = fSceneController.createScenes(aWidth, aHeight);
		
		fMainCharacterController = new CharacterController();
		fMainCharacterController.setSurroundings(initialScene);
		Character kanye = fMainCharacterController.createMainCharacter(aWidth/8, aHeight/8);
		
		fEnemyControllers = new ArrayList<CharacterController>();

		fSceneController.addToGameRoot(initialScene);
		fSceneController.addToGameRoot(kanye);
		
		fScene = new Scene(fSceneController.getGameRoot(), aWidth, aHeight, BACKGROUND_COLOR);
        fScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));

		return fScene;
	}
	
	public void step(double aElapsedTime)
	{
		fMainCharacterController.checkForFreefall();
		fMainCharacterController.updatePosition(aElapsedTime, GRAVITY);
//		for (CharacterController enemyController: fEnemyControllers) {
//			enemyController.updatePosition(aElapsedTime, GRAVITY);
//		}
		
		Tunnel tunnelToTransitionThrough = fMainCharacterController.checkForSceneTransition();
		if (tunnelToTransitionThrough != null) {
			fSceneController.transportToNewScene(tunnelToTransitionThrough);
			fEnemyControllers.clear();

			if (tunnelToTransitionThrough.getDst() instanceof ForestScene) {
				CharacterController enemyController = new CharacterController();
				enemyController.setSurroundings(tunnelToTransitionThrough.getDst());
				Character enemy = enemyController.createEnemy(50, 50);
				fEnemyControllers.add(enemyController);
				
				fSceneController.addToGameRoot(enemy);
				
//				ArrayList<Enemy> enemies = new ArrayList<Enemy>();
//				enemies.add(enemy);
//				fMainCharacterController.setCurrentEnemies(enemies);
			}
			
			fMainCharacterController.setSurroundings(tunnelToTransitionThrough.getDst());
		}
	}
	
	private void handleKeyInput(KeyCode code)
	{
		switch (code) {
        case RIGHT:
    		fMainCharacterController.moveCharacter(KEY_INPUT_SPEED, 0);
    		for (CharacterController enemyController: fEnemyControllers) {
    			enemyController.moveCharacter(KEY_INPUT_SPEED, 0);
    		}
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
        default:
            // do nothing
    }
	}
}