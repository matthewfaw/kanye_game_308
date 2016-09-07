package views.scenes;

import java.util.ArrayList;

import javafx.scene.Group;
import views.elements.background.BackgroundImage;
import views.elements.foreground.obstacles.College;
import views.elements.foreground.obstacles.Ground;
import views.elements.foreground.obstacles.Obstacle;
import views.elements.foreground.obstacles.Tunnel;

public class ForestScene extends GameScene {
	private static final String BACKGROUND_IMAGE_NAME = "trees.jpg";
	
	public ForestScene(int aWidth, int aHeight)
	{
		fRoot = new Group();
		fObstacles = new ArrayList<Obstacle>();
		//this.constructSceneFromFile(FILE_NAME);
		
		fTunnel = new Tunnel(aWidth/8, aHeight/4);
		fTunnel.setX(350);
		fTunnel.setY(200);
		fTunnel.setSrcRoot(fRoot);
		
		Ground ground = new Ground(aWidth, aHeight/4);
		ground.setX(0);
		ground.setY(300);
		
		BackgroundImage backgroundImage = new BackgroundImage(aWidth, aHeight, BACKGROUND_IMAGE_NAME);
		
		fObstacles.add(fTunnel);
		fObstacles.add(ground);
		
		fRoot.getChildren().add(backgroundImage.getRoot());
		fRoot.getChildren().add(fTunnel.getRoot());
		fRoot.getChildren().add(ground.getRoot());
	}
}