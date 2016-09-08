package controllers;
/*
 * The purpose of the game controller is to:
 * 1. Initialize the game
 * 2. Step to the next instant in time of the game
 * 3. Reset the game
 */

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import views.elements.foreground.characters.MainCharacter;
import views.scenes.CollegeScene;
import views.scenes.DoorExplorationScene;
import views.scenes.ForestScene;

public class GameController {
	private static final String GAME_NAME = "Kanye's Quest for the Ultralight Beam";
	private static final Color BACKGROUND_COLOR = Color.WHITE;
	private static final int KEY_INPUT_SPEED = 5;
	private static final double GRAVITY = -10;
	
	private CharacterController fMainCharacterController;
	private Group fGameRoot;
	private Scene fScene;
	
	public String getGameName()
	{
		return GAME_NAME;
	}
	
	public Scene init(int aWidth, int aHeight)
	{
		fGameRoot = new Group();
		
		CollegeScene collegeScene = new CollegeScene(aWidth, aHeight);

		ForestScene forestScene = new ForestScene(aWidth, aHeight);

		DoorExplorationScene doorExplorationScene = new DoorExplorationScene(aWidth, aHeight);
		
		collegeScene.getDstTunnel().setDst(forestScene);

		forestScene.getSrcTunnel().setDst(collegeScene);
		forestScene.getDstTunnel().setDst(doorExplorationScene);

		doorExplorationScene.getSrcTunnel().setDst(forestScene);
		
		fMainCharacterController = new CharacterController(fGameRoot, collegeScene);
		
		fMainCharacterController.setSurroundings(collegeScene.getObstacles());
		Group kanyeRoot = fMainCharacterController.createCharacter(aWidth/8, aHeight/8);
		
		fGameRoot.getChildren().add(collegeScene.getRoot());
		fGameRoot.getChildren().add(kanyeRoot);
		
		fScene = new Scene(fGameRoot, aWidth, aHeight, BACKGROUND_COLOR);
        fScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));

		return fScene;
	}
	
	public void step(double aElapsedTime)
	{
		fMainCharacterController.checkForFreefall();
		fMainCharacterController.updatePosition(aElapsedTime, GRAVITY);
		fMainCharacterController.checkForSceneTransition();
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
        default:
            // do nothing
    }
	}
}