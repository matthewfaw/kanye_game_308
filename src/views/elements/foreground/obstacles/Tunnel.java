package views.elements.foreground.obstacles;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utils.PictureNames;
import views.scenes.GameScene;

public class Tunnel extends Obstacle {
	private GameScene fSrcScene;
	private GameScene fDstScene;
	
	public Tunnel(int aWidth, int aHeight)
	{
		super(aWidth, aHeight, PictureNames.Tunnel);
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