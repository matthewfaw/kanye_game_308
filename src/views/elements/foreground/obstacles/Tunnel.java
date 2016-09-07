package views.elements.foreground.obstacles;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import views.scenes.GameScene;

public class Tunnel extends Obstacle {
	private static final Color TUNNEL_COLOR = Color.BROWN;

	private GameScene fSrcScene;
	private GameScene fDstScene;
	
	public Tunnel(int width, int height)
	{
		fRoot = new Group();
		
		Rectangle tunnel = new Rectangle();
		tunnel.setWidth(width);
		tunnel.setHeight(height);
		tunnel.setFill(TUNNEL_COLOR);
		
		fRoot.getChildren().add(tunnel);
	}
	
	public GameScene getSrc()
	{
		return fSrcScene;
	}
	
	public void setSrc(GameScene aScene)
	{
		fSrcScene = aScene;
	}
	
	public GameScene getDst()
	{
		return fDstScene;
	}
	
	public void setDst(GameScene aScene)
	{
		fDstScene = aScene;
	}
	
//	public Group getSrcRoot()
//	{
//		return fSrcRoot;
//	}
//	public void setSrcRoot(Group aRoot)
//	{
//		fSrcRoot = aRoot;
//	}
//	
//	public GameScene getDstScene()
//	{
//		return fDstScene;
//	}
//	public void setDstScene(GameScene aScene)
//	{
//		fDstScene = aScene;
//	}
	
//	public Group getDstRoot()
//	{
//		return fDstRoot;
//	}
//	public void setDstRoot(Group aRoot)
//	{
//		fDstRoot = aRoot;
//	}

}