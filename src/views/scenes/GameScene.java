package views.scenes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.Group;
import views.elements.SceneElement;
import views.elements.foreground.characters.Enemy;
import views.elements.foreground.obstacles.Obstacle;
import views.elements.foreground.obstacles.Tunnel;

public abstract class GameScene {
	protected Group fRoot;
	protected ArrayList<Obstacle> fObstacles;
	protected ArrayList<Enemy> fEnemies;
	protected Tunnel fSrcTunnel;
	protected Tunnel fDstTunnel;
	
	public GameScene()
	{
		fRoot = new Group();
		fObstacles = new ArrayList<Obstacle>();
		fEnemies = new ArrayList<Enemy>();
	}
	
//	public void constructSceneFromFile(String aFileName)
//	{
//		File file = new File(aFileName);
//		try {
//			Scanner fileScanner = new Scanner(file);
//			
//			// Parse the file
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			System.out.println("File not found!!");
//			e.printStackTrace();
//		} 
//	}
//	
	public Group getRoot()
	{
		return fRoot;
	}
	
	public ArrayList<Obstacle> getObstacles()
	{
		return fObstacles;
	}

	public ArrayList<Enemy> getEnemies()
	{
		return fEnemies;
	}
	
	public Tunnel getSrcTunnel()
	{
		return fSrcTunnel;
	}
	
	public Tunnel getDstTunnel()
	{
		return fDstTunnel;
	}
}