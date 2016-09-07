package views.scenes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.Group;
import views.elements.foreground.obstacles.Obstacle;

public abstract class GameScene {
	protected Group fRoot;
	protected ArrayList<Obstacle> fObstacles;
	
	void constructSceneFromFile(String aFileName)
	{
		File file = new File(aFileName);
		try {
			Scanner fileScanner = new Scanner(file);
			
			// Parse the file
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found!!");
			e.printStackTrace();
		} 
	}
	
	public Group getRoot()
	{
		return fRoot;
	}
	
	public ArrayList<Obstacle> getObstacles()
	{
		return fObstacles;
	}
}