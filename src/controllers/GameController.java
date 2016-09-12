package controllers;
/*
 * The purpose of the game controller is to:
 * 1. Initialize the game
 * 2. Step to the next instant in time of the game
 * 3. Reset the game
 */

import java.util.ArrayList;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import models.PlayerStats;
import utils.Direction;
import utils.GameResults;
import utils.GameSettings;
import utils.PictureNames;
import utils.Vector;
import views.elements.foreground.attack.Fireball;
import views.elements.foreground.characters.Character;
import views.elements.foreground.characters.Enemy;
import views.elements.foreground.characters.MainCharacter;
import views.elements.foreground.obstacles.Tunnel;
import views.elements.foreground.rewards.Gold;
import views.scenes.CollegeScene;
import views.scenes.DoorExplorationScene;
import views.scenes.ForestScene;
import views.scenes.GameScene;
import views.scenes.UltralightBeamScene;

public class GameController {
	private static final String GAME_NAME = "Kanye's Quest for the Ultralight Beam";
	private static final String GAME_INFO = "Game Info:\n"
											+ "Use arrow keys to move Kanye\n"
											+ "Collect coins to pass through the tunnel in the forest\n"
											+ "Jump on top of the cameras to make them disappear\n"
											+ "Avoid Taylors at all costs!\n"
											+ "";
	private static final Color BACKGROUND_COLOR = Color.WHITE;
	private static final int KEY_INPUT_SPEED = 5;
	private static final double GRAVITY = -10;
	private static final double FULL_HEALTH = 100.0;
//	private static final double HEALTH_DEDUCTION = -1.0;
	
	private static final double MOVING_LEFT = -1.0;
	private static final double MOVING_RIGHT = 1.0;
	private static final double NOT_MOVING = 0.0;
	
	private static final int BOTTOM_OF_SCREEN = 250;
	private static final int MIDDLE_OF_SCREEN = 150;
	private static final int TOP_OF_SCREEN = 50;
	private static final int LEFT_OF_SCREEN = 50;
	private static final int RIGHT_OF_SCREEN = 250;

	private static final int COLLEGE_SCENE_INDEX = 0;
	private static final int FOREST_SCENE_INDEX = 1;
	private static final int DOOR_SCENE_INDEX = 2;
	private static final int ULTRALIGHT_BEAM_SCENE_INDEX = 3;
	
//	private static final int NUM_CAMERAS = 3;
//	private static final int NUM_TAYLORS = 4;
	private static final int NUM_GOLD_COINS = 10;
	private static final int MINIMUM_GOLD_COUNT = NUM_GOLD_COINS*3/4;

	private double fHealthDeduction;
	private int fNumCameras;
	private int fNumTaylors;

	private SceneController fSceneController;
	private ArrayList<GameScene> fGameScenes;
	private MainCharacterController fMainCharacterController;
	private ArrayList<EnemyController> fEnemyControllers;
	private Scene fScene;
	private PlayerStats fPlayerStats;
	private int fCurrentSceneIndex;
	private Random fRandomNumberGenerator;
	private boolean fGameHasStarted;
	
	public String getGameName()
	{
		return GAME_NAME;
	}
	
	public Scene init(int aWidth, int aHeight)
	{
		fRandomNumberGenerator = new Random();
		fGameHasStarted = false;
		
		fSceneController = new SceneController();
		fSceneController.createGameRoot(aWidth, aHeight);
		
		fGameScenes = fSceneController.createScenes(aWidth, aHeight, GAME_NAME, GAME_INFO);
		
		fSceneController.addToGameRoot(fSceneController.getStartScreen());
		fScene = new Scene(fSceneController.getGameRoot(), aWidth, aHeight, BACKGROUND_COLOR);
        fScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        
        setButtonAction(fSceneController.getStartScreen().getEasyButton(), GameSettings.Easy);
        setButtonAction(fSceneController.getStartScreen().getHardButton(), GameSettings.Hard);

		return fScene;
	}
	
	private void setButtonAction(Button aButton, GameSettings aGameSetting)
	{
        aButton.setOnAction(new EventHandler<ActionEvent>() {
        	@Override 
        	public void handle(ActionEvent e)
        	{
        		fSceneController.getStartScreen().getRoot().getChildren().clear();
        		startGame(aGameSetting);
        	}
        });
		
	}
	
	private void startGame(GameSettings aGameSetting)
	{
		fGameHasStarted = true;
		
		switch(aGameSetting) {
		case Easy:
			setDifficultyFields(-0.5, 3, 4);
			break;
		case Hard:
			setDifficultyFields(-1, 5, 6);
			break;
		}
		
		fMainCharacterController = new MainCharacterController();
		fMainCharacterController.setSurroundings(fGameScenes.get(COLLEGE_SCENE_INDEX));
		fCurrentSceneIndex = COLLEGE_SCENE_INDEX;
		fMainCharacterController.createMainCharacter(fSceneController.getWidth()/8, fSceneController.getHeight()/8);
		
		fEnemyControllers = new ArrayList<EnemyController>();

		fPlayerStats = new PlayerStats();
		updateHealth(FULL_HEALTH);

		fSceneController.addToGameRoot(fGameScenes.get(COLLEGE_SCENE_INDEX));
		fSceneController.addToGameRoot(fMainCharacterController.getMainCharacter());
	}
	
	private void endGame(GameResults aGameResult)
	{
		fSceneController.getGameRoot().getChildren().clear();
		switch (aGameResult) {
		case Win:
			fSceneController.addToGameRoot(fSceneController.getWinningScreen());
		case Lose:
			fSceneController.addToGameRoot(fSceneController.getLosingScreen());
		}
	}
	
	private void setDifficultyFields(double aHealthDeduction, int aNumCameras, int aNumTaylors)
	{
		fHealthDeduction = aHealthDeduction;
		fNumCameras = aNumCameras;
		fNumTaylors = aNumTaylors;
	}

	private boolean gameHasStarted()
	{
		return fGameHasStarted;
	}
	
	
	public void step(double aElapsedTime)
	{
		if (gameHasStarted()) {
			moveCharacters(aElapsedTime);
			moveFireballs(aElapsedTime);
			updateHealth();
			updateGoldCount();
			checkForEnemyDeath();

			Tunnel tunnelToTransitionThrough = fMainCharacterController.checkForSceneTransition();
			//XXX: uncomment when gold spawning is working
			if (tunnelToTransitionThrough != null /*&& tunnelToTransitionThrough.isActive()*/) {
				if (fPlayerStats.getGoldCount() > MINIMUM_GOLD_COUNT || !(fSceneController.getCurrentScene() instanceof ForestScene)) {
					handleSceneTransition(tunnelToTransitionThrough.getDst());
				}
			}
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
	
	private void moveFireballs(double aElapsedTime)
	{
		fMainCharacterController.moveFireballs();
	}
	
	private void updateHealth()
	{
		if (fMainCharacterController.isBeingHurtByAnActiveEnemy()) {
			updateHealth(fPlayerStats.getHealth() + fHealthDeduction);
		}
	}
	private void updateGoldCount()
	{
		Gold gold = fMainCharacterController.isTouchingGold();
		if (gold != null) {
			fMainCharacterController.removeGold(gold);
			fSceneController.removeGold(gold);
			fPlayerStats.addGoldPiece();
			fSceneController.updateGoldCount(fPlayerStats.getGoldCount());
		}
	}
	
	private void checkForEnemyDeath()
	{
		Enemy killedEnemy = fMainCharacterController.killedAnActiveEnemy();
		if (killedEnemy != null) {
			//XXX: this is horrible...
			fEnemyControllers.get(killedEnemy.getId()).getEnemy().getRoot().getChildren().clear();;
		}
	}
	
	private void handleSceneTransition(GameScene aDstScene)
	{
//		fMainCharacterController.clearEnemies();
		fMainCharacterController.emptyBelongings();
		fMainCharacterController.setSurroundings(aDstScene);
		fSceneController.transportToNewScene(aDstScene);
		fEnemyControllers.clear();

		if (aDstScene instanceof CollegeScene) {
			fCurrentSceneIndex = COLLEGE_SCENE_INDEX;
		} else if (aDstScene instanceof ForestScene) {
			for (int i=0; i<fNumCameras; ++i) {
				addEnemyToGame(aDstScene, PictureNames.Camera);
			}
			for (int i=0; i<NUM_GOLD_COINS; ++i) {
				addOneGoldToGame(aDstScene);
			}
			fCurrentSceneIndex = FOREST_SCENE_INDEX;
		} else if (aDstScene instanceof DoorExplorationScene) {
			for (int i=0; i<fNumTaylors; ++i) {
				addEnemyToGame(aDstScene, PictureNames.Taylor);
			}
			fCurrentSceneIndex = DOOR_SCENE_INDEX;
		} else if (aDstScene instanceof UltralightBeamScene) {
			fCurrentSceneIndex = ULTRALIGHT_BEAM_SCENE_INDEX;
		}

	}
	
	private void addEnemyToGame(GameScene aScene, String aEnemyFileName)
	{
		double startingYVelocity;
		double startingYPosition;
		if (aEnemyFileName.equals(PictureNames.Camera)) {
			startingYPosition = BOTTOM_OF_SCREEN;
			startingYVelocity = NOT_MOVING;
		} else {
			startingYPosition = getRandomNumber(MIDDLE_OF_SCREEN, BOTTOM_OF_SCREEN);
			startingYVelocity = getRandomDirection();
		}
		Vector startingPosition = new Vector(getRandomNumber(LEFT_OF_SCREEN,RIGHT_OF_SCREEN), startingYPosition);
		Vector startingVelocity = new Vector(getRandomDirection(), startingYVelocity);
		
		EnemyController enemyController = new EnemyController();
		enemyController.setSurroundings(aScene);
		enemyController.createEnemy(50, 50, aEnemyFileName, startingVelocity, startingPosition, fEnemyControllers.size());
		fEnemyControllers.add(enemyController);

		fSceneController.addToGameRoot(enemyController.getEnemy());

		fMainCharacterController.addEnemy(enemyController.getEnemy());
	}
	
	private void addOneGoldToGame(GameScene aScene)
	{
		Gold gold = new Gold();
		
		gold.setX(getRandomNumber(LEFT_OF_SCREEN, RIGHT_OF_SCREEN));
		gold.setY(getRandomNumber(TOP_OF_SCREEN, BOTTOM_OF_SCREEN));
		fSceneController.addToGameRoot(gold);
		fMainCharacterController.addGold(gold);
	}
	
	private void updateHealth(double aValue)
	{
		fPlayerStats.setHealth(aValue);
		fSceneController.updateHealthBar(aValue);
		if (fPlayerStats.getHealth() <= 0) {
			endGame(GameResults.Lose);
		}
	}
	
	private void changeScene()
	{
		int nextSceneIndex = (fCurrentSceneIndex + 1) % fGameScenes.size();
		handleSceneTransition(fGameScenes.get(nextSceneIndex));
	}
	
	// Returns a pseudorandom number between lower and upper, inclusive
	private double getRandomNumber(int aLowerBound, int aUpperBound)
	{
		return fRandomNumberGenerator.nextInt(aUpperBound - aLowerBound + 1) + aLowerBound;
	}
	private double getRandomDirection()
	{
		return Math.pow(-1, fRandomNumberGenerator.nextInt());
	}
	
	private void spawnFireball(Vector aDirection)
	{
		if (fSceneController.getCurrentScene() instanceof DoorExplorationScene) {
			Fireball fireball = fMainCharacterController.spitFire(aDirection);
			fSceneController.addToGameRoot(fireball);
		}
	}
	
	private void handleKeyInput(KeyCode code)
	{
		if (fGameHasStarted) {
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
			case D:
				for (EnemyController enemyController: fEnemyControllers) {
					enemyController.disableEnemy();
				}
				break;
			case H:
				spawnFireball(Direction.LEFT);
				break;
			case J:
				spawnFireball(Direction.DOWN);
				break;
			case K:
				spawnFireball(Direction.UP);
				break;
			case L:
				spawnFireball(Direction.RIGHT);
				break;
			case R:
				for (EnemyController enemyController: fEnemyControllers) {
					enemyController.reenableEnemy();
				}
				break;
			case T:
				changeScene();
				break;
			default:
				// do nothing
			}
		} 
	}
}