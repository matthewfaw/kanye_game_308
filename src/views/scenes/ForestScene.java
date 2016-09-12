package views.scenes;

import utils.PictureNames;
import views.elements.background.BackgroundImage;
import views.elements.foreground.obstacles.Ground;
import views.elements.foreground.obstacles.Tunnel;

public class ForestScene extends GameScene {
	private static final String BACKGROUND_IMAGE_NAME = PictureNames.Trees;
	
	public ForestScene(int aWidth, int aHeight)
	{
		super();
		
		fSrcTunnel = new Tunnel(aWidth/8, aHeight/4);
		fSrcTunnel.setX(DEFAULT_LEFT_OBJECT_POSITION.getX());
		fSrcTunnel.setY(DEFAULT_LEFT_OBJECT_POSITION.getY());
		fSrcTunnel.setSrc(this);
		
		fDstTunnel = new Tunnel(aWidth/8, aHeight/4);
		fDstTunnel.setX(DEFAULT_RIGHT_OBJECT_POSITION.getX());
		fDstTunnel.setY(DEFAULT_RIGHT_OBJECT_POSITION.getY());
		fDstTunnel.setSrc(this);

		
		Ground ground = new Ground(aWidth, aHeight/4);
		ground.setX(DEFAULT_GROUND_POSITION.getX());
		ground.setY(DEFAULT_GROUND_POSITION.getY());
		
		BackgroundImage backgroundImage = new BackgroundImage(aWidth, aHeight, BACKGROUND_IMAGE_NAME);
		
		fObstacles.add(fSrcTunnel);
		fObstacles.add(fDstTunnel);
		fObstacles.add(ground);
		
		fRoot.getChildren().add(backgroundImage.getRoot());
		fRoot.getChildren().add(fSrcTunnel.getRoot());
		fRoot.getChildren().add(fDstTunnel.getRoot());
		fRoot.getChildren().add(ground.getRoot());
	}
}