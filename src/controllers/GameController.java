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

public class GameController {
	private static final String GAME_NAME = "Kanye's Quest for the Ultralight Beam";
	private static final Color BACKGROUND_COLOR = Color.WHITE;
	private static final int KEY_INPUT_SPEED = 5;
	
	private CharacterController fCharacterController;
	private Scene fScene;
	
	public String getGameName()
	{
		return GAME_NAME;
	}
	
	public Scene init(int aWidth, int aHeight)
	{
		Group root = new Group();
		
		CollegeScene collegeScene = new CollegeScene(aWidth, aHeight);
		Group collegeRoot = collegeScene.getRoot();
		
		fCharacterController = new CharacterController();
		
		Group kanyeRoot = fCharacterController.createMainCharacter(aWidth/8, aHeight/8);
		
		root.getChildren().add(collegeRoot);
		root.getChildren().add(kanyeRoot);
		
		fScene = new Scene(root, aWidth, aHeight, BACKGROUND_COLOR);
        fScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));

		return fScene;
	}
	
	public void step(double aElapsedTime)
	{
		//fCharacterController.moveMainCharacter(1, 0);
	}
	
	private void handleKeyInput(KeyCode code)
	{
		switch (code) {
        case RIGHT:
    		fCharacterController.moveMainCharacter(KEY_INPUT_SPEED, 0);
            break;
        case LEFT:
    		fCharacterController.moveMainCharacter(-KEY_INPUT_SPEED, 0);
            break;
        case UP:
    		fCharacterController.moveMainCharacter(0, -KEY_INPUT_SPEED);
            break;
        case DOWN:
    		fCharacterController.moveMainCharacter(0, KEY_INPUT_SPEED);
            break;
        default:
            // do nothing
    }
	}
}