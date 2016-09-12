package views.scenes;

import utils.PictureNames;
import utils.Vector;
import views.elements.foreground.obstacles.College;
import views.elements.foreground.obstacles.Ground;
import views.elements.foreground.obstacles.Tunnel;
import views.elements.background.BackgroundImage;

public class CollegeScene extends GameScene {
	private static final String BACKGROUND_IMAGE_NAME = PictureNames.Sky;
	private static final Vector DEFAULT_TUNNEL_POSITION = new Vector(300,400);
	
	/**
	 * Creates a CollegeScene that encapulates everyting within the college scene of the game
	 * @param aWidth
	 * @param aHeight
	 */
	public CollegeScene(int aWidth, int aHeight)
	{
		super();

		College college = new College(aWidth/4, aHeight/4);
		college.setX(DEFAULT_LEFT_OBJECT_POSITION.getX());
		college.setY(DEFAULT_LEFT_OBJECT_POSITION.getY());
		
		fDstTunnel = new Tunnel(aWidth/4, aHeight/8);
		fDstTunnel.setX(DEFAULT_TUNNEL_POSITION.getX());
		fDstTunnel.setY(DEFAULT_TUNNEL_POSITION.getY());
		fDstTunnel.setSrc(this);
		
		Ground ground = new Ground(aWidth*3/4, aHeight/4);
		ground.setX(DEFAULT_GROUND_POSITION.getX());
		ground.setY(DEFAULT_GROUND_POSITION.getY());
		
		BackgroundImage backgroundImage = new BackgroundImage(aWidth, aHeight, BACKGROUND_IMAGE_NAME);
		
		fObstacles.add(college);
		fObstacles.add(fDstTunnel);
		fObstacles.add(ground);
		
		fRoot.getChildren().add(backgroundImage.getRoot());
		fRoot.getChildren().add(college.getRoot());
		fRoot.getChildren().add(fDstTunnel.getRoot());
		fRoot.getChildren().add(ground.getRoot());
	}
	
	
	
}