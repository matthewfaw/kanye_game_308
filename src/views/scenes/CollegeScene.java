package views.scenes;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import views.elements.foreground.obstacles.College;
import views.elements.foreground.obstacles.Ground;
import views.elements.background.BackgroundImage;

public class CollegeScene extends GameScene {
	private static final String FILE_NAME = "college_description.txt";
	
	private Group fRoot;
	
	public CollegeScene(int aWidth, int aHeight)
	{
		fRoot = new Group();
		
		//this.constructSceneFromFile(FILE_NAME);
		
		College college = new College(aWidth/4, aHeight/4);
		college.setX(0);
		college.setY(200);
		
		Ground ground = new Ground(aWidth, aHeight/4);
		ground.setX(0);
		ground.setY(300);
		
		BackgroundImage backgroundImage = new BackgroundImage(aWidth, aHeight);

		fRoot.getChildren().add(backgroundImage.getRoot());
		fRoot.getChildren().add(college.getRoot());
		fRoot.getChildren().add(ground.getRoot());
	}
	
	public Group getRoot()
	{
		return fRoot;
	}
	
}