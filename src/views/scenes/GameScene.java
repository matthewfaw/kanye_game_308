package views.scenes;

import java.util.ArrayList;

import javafx.scene.Group;
import utils.Vector;
import views.elements.foreground.characters.Enemy;
import views.elements.foreground.obstacles.Obstacle;
import views.elements.foreground.obstacles.Tunnel;

public abstract class GameScene {
	protected static final Vector DEFAULT_LEFT_OBJECT_POSITION = new Vector(0,200);
	protected static final Vector DEFAULT_RIGHT_OBJECT_POSITION = new Vector(350,200);
	protected static final Vector DEFAULT_GROUND_POSITION = new Vector(0,300);

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