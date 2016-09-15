package controllers;

import java.util.ArrayList;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import models.PlayerStats;
import utils.Direction;
import utils.GameResults;
import utils.GameSettings;
import utils.MusicNames;
import utils.PictureNames;
import utils.Vector;
import views.elements.foreground.attack.Fireball;
import views.elements.foreground.characters.Enemy;
import views.elements.foreground.characters.MainCharacter;
import views.elements.foreground.obstacles.Tunnel;
import views.elements.foreground.rewards.Gold;
import views.scenes.CollegeScene;
import views.scenes.DoorExplorationScene;
import views.scenes.ForestScene;
import views.scenes.GameScene;
import views.scenes.UltralightBeamScene;

/**
 * This is the class that is responsible for controlling the game's core logic.
 * One may think of this
 * class as the "manager" of every other controller.  It is responsible for creating all
 * of the character controllers, as well as the music controller and scene controller. It
 * is also responsible for managing when and how these controllers interact.
 * 
 * The only real assumtions this class makes is that
 * 1) The main program will call the init method before anything else
 * 2) A user will be able to use a mouse to click on the game's splash screen
 * 3) A user will be able to use the keyboard.
 * 
 * This class depends on each of the Controller classes, the PlayerStats class, each of the Scene classes, 
 * each of classes in the utils package, the Fireball class, the Enemy class, the Tunnel class, and the Gold class.
 * 
 * The class is instantiated by:
 * Scene scene = fGameController.init(SIZE, SIZE, getClass().getClassLoader());
 * 
 * One may progress the game to the next instant in time by:
 * fGameController.step(SECOND_DELAY)
 * 
 * @author matthewfaw
 *
 */
public class GameController {
	private static final String GAME_NAME = "Kanye's Quest for the Ultralight Beam";
	private static final String GAME_INFO = "Game Info:\n"
											+ "To pass the first level, Kanye must *drop* out of college.\n"
											+ "This transports him to the Forest, where his journey begins...\n"
											+ "Collect coins to pass through the tunnel in the forest\n"
											+ "Jump on top of the cameras to make them disappear\n"
											+ "Avoid Taylors at all costs! Find Kim!!\n"
											+ "You can attack the Taylor enemy by *spitting* fire with h,j,k,l keys\n"
											+ "When Champion plays, you win the game!\n\n"
											+ "Controls:\n"
											+ "h,j,k,l: Shoot fire left, down, up, and right, respecitvely\n"
											+ "Arrow keys: Move Kanye in the pressed direction\n"
											+ "Mouse click: used to select which game difficulty level to begin with\n\n"
											+ "Cheat codes:\n"
											+ "d: disables enemy movement, and their ability to harm Kanye\n"
											+ "r: reenables enemy movement\n"
											+ "t: transports Kanye to the next game scene after the game has started";
	private static final Color BACKGROUND_COLOR = Color.WHITE;
	private static final int KEY_INPUT_SPEED = 5;
	private static final double GRAVITY = -10;
	private static final double FULL_HEALTH = 100.0;
	private static final double EASY_HEALTH_DEDUCTION = -0.5;
	private static final double HARD_HEALTH_DEDUCTION = -1.0;
	
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
	
	private static final int EASY_NUM_CAMERAS = 3;
	private static final int HARD_NUM_CAMERAS = 5;
	private static final int EASY_NUM_TAYLORS = 4;
	private static final int HARD_NUM_TAYLORS = 6;
	private static final int NUM_GOLD_COINS = 10;
	private static final int MINIMUM_GOLD_COUNT = NUM_GOLD_COINS*3/4;

	private double fHealthDeduction;
	private int fNumCameras;
	private int fNumTaylors;

	private SceneController fSceneController;
	private MusicController fMusicController;
	private ArrayList<GameScene> fGameScenes;
	private MainCharacterController fMainCharacterController;
	private ArrayList<EnemyController> fEnemyControllers;
	private Scene fScene;
	private PlayerStats fPlayerStats;
	private int fCurrentSceneIndex;
	private Random fRandomNumberGenerator;
	private boolean fGameHasStarted;
	
	/**
	 * A method used by the Main program to get the name of the game. 
	 * @return the game name
	 */
	public String getGameName()
	{
		return GAME_NAME;
	}
	
	/**
	 * A method to handle the initial game setup.  Sets up the splash screen, and prepares necessary fields
	 * @param aWidth
	 * @param aHeight
	 * @param aClassLoader
	 * @return the JavaFX Scene object to be displayed on the Stage.
	 */
	public Scene init(int aWidth, int aHeight, ClassLoader aClassLoader)
	{
		fRandomNumberGenerator = new Random();
		fGameHasStarted = false;
		
		fMusicController = new MusicController(aClassLoader);
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
			setDifficultyFields(EASY_HEALTH_DEDUCTION, EASY_NUM_CAMERAS, EASY_NUM_TAYLORS);
			break;
		case Hard:
			setDifficultyFields(HARD_HEALTH_DEDUCTION, HARD_NUM_CAMERAS, HARD_NUM_TAYLORS);
			break;
		}
		
		fMainCharacterController = new MainCharacterController();
		fMainCharacterController.setSurroundings(fGameScenes.get(COLLEGE_SCENE_INDEX));
		fCurrentSceneIndex = COLLEGE_SCENE_INDEX;
		fMainCharacterController.createCharacter(PictureNames.MainCharacter, new Vector(0,0), MainCharacter.DEFAULT_POSITION);
		fMusicController.playSong(MusicNames.COLLEGE_SCENE_MUSIC);
		
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
//		case Win:
//			fSceneController.addToGameRoot(fSceneController.getWinningScreen());
//			break;
		case Lose:
			fSceneController.addToGameRoot(fSceneController.getLosingScreen());
			break;
		default:
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
	
	
	/**
	 * A method called every time step, used to make the game progress.
	 * @param aElapsedTime
	 */
	public void step(double aElapsedTime)
	{
		if (gameHasStarted()) {
			moveCharacters(aElapsedTime);
			moveFireballs(aElapsedTime);
			updateHealth();
			updateGoldCount();
			checkForEnemyDeath();

			Tunnel tunnelToTransitionThrough = fMainCharacterController.checkForSceneTransition();
			
			if (tunnelToTransitionThrough != null) {
				if (fPlayerStats.getGoldCount() > MINIMUM_GOLD_COUNT || !(fSceneController.getCurrentScene() instanceof ForestScene)) {
					handleSceneTransition(tunnelToTransitionThrough.getDst());
				}
			}
		}
	}
	
	private void moveCharacters(double aElapsedTime)
	{
		fMainCharacterController.checkForFreefall();
		fMainCharacterController.updateJumpingTrajectory(aElapsedTime, GRAVITY);
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
			fEnemyControllers.get(killedEnemy.getId()).getEnemy().getRoot().getChildren().clear();
		}
	}
	
	private void handleSceneTransition(GameScene aDstScene)
	{
		fMainCharacterController.emptyBelongings();
		fMainCharacterController.setSurroundings(aDstScene);
		fSceneController.transportToNewScene(aDstScene);
		fEnemyControllers.clear();

		if (aDstScene instanceof CollegeScene) {
			fMusicController.playSong(MusicNames.COLLEGE_SCENE_MUSIC);
			fCurrentSceneIndex = COLLEGE_SCENE_INDEX;
		} else if (aDstScene instanceof ForestScene) {
			fMusicController.playSong(MusicNames.FOREST_SCENE_MUSIC);
			for (int i=0; i<fNumCameras; ++i) {
				addEnemyToGame(aDstScene, PictureNames.Camera);
			}
			for (int i=0; i<NUM_GOLD_COINS; ++i) {
				addOneGoldToGame(aDstScene);
			}
			fCurrentSceneIndex = FOREST_SCENE_INDEX;
		} else if (aDstScene instanceof DoorExplorationScene) {
			fMusicController.playSong(MusicNames.TAYLOR_SCENE_MUSIC);
			for (int i=0; i<fNumTaylors; ++i) {
				addEnemyToGame(aDstScene, PictureNames.Taylor);
			}
			fCurrentSceneIndex = DOOR_SCENE_INDEX;
		} else if (aDstScene instanceof UltralightBeamScene) {
			fMusicController.playSong(MusicNames.ULTRALIGHT_BEAM_SCENE_MUSIC);
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
		
		EnemyController enemyController = new EnemyController(fEnemyControllers.size());
		enemyController.setSurroundings(aScene);
		enemyController.createCharacter(aEnemyFileName, startingVelocity, startingPosition); 
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