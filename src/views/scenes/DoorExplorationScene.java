package views.scenes;

import java.util.ArrayList;

import javafx.scene.Group;
import views.elements.background.BackgroundImage;
import views.elements.foreground.obstacles.College;
import views.elements.foreground.obstacles.Ground;
import views.elements.foreground.obstacles.Obstacle;
import views.elements.foreground.obstacles.Tunnel;

public class DoorExplorationScene extends GameScene {
	
	private static final String BACKGROUND_IMAGE_NAME = "duke.gif";
	
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
		
		BackgroundImage backgroundImage = new BackgroundImage(aWidth, aHeight, BACKGROUND_IMAGE_NAME);
		
		fObstacles.add(fSrcTunnel);
		fObstacles.add(ground);
		
		fRoot.getChildren().add(backgroundImage.getRoot());
		fRoot.getChildren().add(fSrcTunnel.getRoot());
		fRoot.getChildren().add(ground.getRoot());
	}
}