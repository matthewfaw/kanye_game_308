package views.elements.foreground.obstacles;

import utils.PictureNames;
import views.scenes.GameScene;

public class Tunnel extends Obstacle {
	private GameScene fSrcScene;
	private GameScene fDstScene;
	
	public Tunnel(int aWidth, int aHeight)
	{
		super(aWidth, aHeight, PictureNames.Tunnel);
	}
	
	public Tunnel(int aWidth, int aHeight, String aPictureName)
	{
		super(aWidth, aHeight, aPictureName);
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
}