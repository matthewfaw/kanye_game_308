package views.scenes;

import utils.PictureNames;
import utils.Vector;
import views.elements.background.BackgroundImage;
import views.elements.foreground.obstacles.Ground;
import views.elements.foreground.obstacles.Tunnel;
import views.elements.foreground.obstacles.Wall;

public class DoorExplorationScene extends GameScene {
	
	private static final String BACKGROUND_IMAGE_NAME = PictureNames.Stone;
	private static final Vector ORIGIN = new Vector(0,0);
	private static final Vector MIDDLE_OBJECT_IN_GROUND_POSITION = new Vector(150,300);
	private static final int DEFAULT_WALL_WIDTH = 50;
	private static final int SMALL_WALL_WIDTH = 40;
	
	/**
	 * Creates a scene which contains every scene element pertaining to the scene with taylor swift enemies
	 * @param aHeight
	 * @param aWidth
	 */
	public DoorExplorationScene(int aHeight, int aWidth)
	{
		super();

		fSrcTunnel = new Tunnel(aWidth/8, aHeight/4);
		fSrcTunnel.setX(DEFAULT_LEFT_OBJECT_POSITION.getX());
		fSrcTunnel.setY(DEFAULT_LEFT_OBJECT_POSITION.getY());
		fSrcTunnel.setSrc(this);
		
		Ground ground = new Ground(aWidth, aHeight/4);
		ground.setX(DEFAULT_GROUND_POSITION.getX());
		ground.setY(DEFAULT_GROUND_POSITION.getY());
		
		fDstTunnel = new Tunnel(aWidth/8, aHeight/4, PictureNames.Kim);
		fDstTunnel.setX(MIDDLE_OBJECT_IN_GROUND_POSITION.getX());
		fDstTunnel.setY(MIDDLE_OBJECT_IN_GROUND_POSITION.getY());
		fDstTunnel.setSrc(this);
		fDstTunnel.setDst(this);
		
		Wall leftWall = new Wall(DEFAULT_WALL_WIDTH, aHeight);
		leftWall.setX(ORIGIN.getX());
		leftWall.setY(ORIGIN.getY());
		Wall rightWall = new Wall(DEFAULT_WALL_WIDTH, aHeight);
		rightWall.setX(aWidth - DEFAULT_WALL_WIDTH);
		rightWall.setY(ORIGIN.getY());
		Wall topWall = new Wall(aWidth, SMALL_WALL_WIDTH);
		topWall.setX(ORIGIN.getX());
		topWall.setY(ORIGIN.getY());
		
		BackgroundImage backgroundImage = new BackgroundImage(aWidth, aHeight, BACKGROUND_IMAGE_NAME);
		
		fObstacles.add(leftWall);
		fObstacles.add(rightWall);
		fObstacles.add(topWall);
		fObstacles.add(fSrcTunnel);
		fObstacles.add(ground);
		fObstacles.add(fDstTunnel);
		
		fRoot.getChildren().add(backgroundImage.getRoot());
		fRoot.getChildren().add(leftWall.getRoot());
		fRoot.getChildren().add(rightWall.getRoot());
		fRoot.getChildren().add(topWall.getRoot());
		fRoot.getChildren().add(fSrcTunnel.getRoot());
		fRoot.getChildren().add(ground.getRoot());
		fRoot.getChildren().add(fDstTunnel.getRoot());
	}
}