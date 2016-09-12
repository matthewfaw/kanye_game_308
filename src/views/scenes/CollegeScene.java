package views.scenes;

import java.util.ArrayList;

import javafx.scene.Group;
import utils.PictureNames;
import views.elements.foreground.characters.MainCharacter;
import views.elements.foreground.obstacles.College;
import views.elements.foreground.obstacles.Ground;
import views.elements.foreground.obstacles.Obstacle;
import views.elements.foreground.obstacles.Tunnel;
import views.elements.background.BackgroundImage;

public class CollegeScene extends GameScene {
	private static final String FILE_NAME = "college_description.txt";
	private static final String BACKGROUND_IMAGE_NAME = PictureNames.Sky;
	
//	private Group fRoot;
//	private ArrayList<Obstacle> fObstacles;
	
	public CollegeScene(int aWidth, int aHeight)
	{
		fRoot = new Group();
		fObstacles = new ArrayList<Obstacle>();
		//this.constructSceneFromFile(FILE_NAME);
		
		College college = new College(aWidth/4, aHeight/4);
		college.setX(0);
		college.setY(200);
		
//		Tunnel tunnel = new Tunnel(aWidth/8, aHeight/4);
		fDstTunnel = new Tunnel(aWidth/4, aHeight/8);
		fDstTunnel.setX(300);
		fDstTunnel.setY(400);
		fDstTunnel.setSrc(this);
		//tunnel.setDstRoot(aRoot);
		//tunnel.setDstScene();
		
		Ground ground = new Ground(aWidth*3/4, aHeight/4);
		ground.setX(0);
		ground.setY(300);
		
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