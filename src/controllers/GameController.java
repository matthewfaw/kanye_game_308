package controllers;
/*
 * The purpose of the game controller is to:
 * 1. Initialize the game
 * 2. Step to the next instant in time of the game
 * 3. Reset the game
 */

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import views.scenes.CollegeScene;

public class GameController {
	private static final String GAME_NAME = "Kanye's Quest for the Ultralight Beam";
	private static final Color BACKGROUND_COLOR = Color.WHITE;
	
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
		root.getChildren().add(collegeRoot);
		
		fScene = new Scene(root, aWidth, aHeight, BACKGROUND_COLOR);
		
		// REMOVE!
		return fScene;
	}
	
	public void step(double aElapsedTime)
	{
		
	}
}