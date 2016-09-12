package views.scenes;

import java.util.ArrayList;

import javafx.scene.Group;
import utils.PictureNames;
import views.elements.background.BackgroundImage;
import views.elements.foreground.obstacles.College;
import views.elements.foreground.obstacles.Ground;
import views.elements.foreground.obstacles.Obstacle;
import views.elements.foreground.obstacles.Tunnel;
import views.elements.foreground.obstacles.Wall;

public class DoorExplorationScene extends GameScene {
	
	private static final String BACKGROUND_IMAGE_NAME = PictureNames.Stone;
	
	public DoorExplorationScene(int aHeight, int aWidth)
	{
		fRoot = new Group();
		fObstacles = new ArrayList<Obstacle>();
		//this.constructSceneFromFile(FILE_NAME);
		
//		Tunnel tunnel = new Tunnel(aWidth/8, aHeight/4);
		fSrcTunnel = new Tunnel(aWidth/8, aHeight/4);
		fSrcTunnel.setX(0);
		fSrcTunnel.setY(200);
		fSrcTunnel.setSrc(this);
		//tunnel.setDstRoot(aRoot);
		//tunnel.setDstScene();
		
		Ground ground = new Ground(aWidth, aHeight/4);
		ground.setX(0);
		ground.setY(300);
		
		fDstTunnel = new Tunnel(aWidth/8, aHeight/4, PictureNames.Kim);
		fDstTunnel.setX(150);
		fDstTunnel.setY(300);
		fDstTunnel.setSrc(this);
		fDstTunnel.setDst(this);
		
		Wall leftWall = new Wall(aWidth/8, aHeight);
		leftWall.setX(0);
		leftWall.setY(0);
		Wall rightWall = new Wall(50, aHeight);
		rightWall.setX(aWidth - 50);
		rightWall.setY(0);
		Wall topWall = new Wall(aWidth, 40);
		topWall.setX(0);
		topWall.setY(0);
		
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