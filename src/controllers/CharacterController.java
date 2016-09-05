package controllers;

import javafx.scene.Group;
import views.elements.foreground.characters.MainCharacter;

/*
 * The purpose of the Character Controller is to:
 * 1. Handle character movement
 * 2. Update the player info
 */

public class CharacterController {
	private static final double DEL_X = 1.0;
	private static final double DEL_Y = 1.0;
	
	private MainCharacter fKanye;
	
	public CharacterController()
	{
		
	}
	
	public Group createMainCharacter(int aWidth, int aHeight)
	{
		fKanye = new MainCharacter(aWidth, aHeight);
		fKanye.setX(100);
		fKanye.setY(250);
		
		Group kanyeRoot = fKanye.getRoot();
		
		return kanyeRoot;
	}
	
	public void moveMainCharacter(int aXUnit, int aYUnit)
	{
		fKanye.setX(fKanye.getX() + aXUnit * DEL_X);
		fKanye.setY(fKanye.getY() + aYUnit * DEL_Y);
	}
}