package views.elements.foreground.obstacles;

import utils.PictureNames;
import views.scenes.GameScene;

public class Tunnel extends Obstacle {
	private GameScene fSrcScene;
	private GameScene fDstScene;
	
	/**
	 * creates a tunnel with generic picturec:w
	 * 
	 * @param aWidth
	 * @param aHeight
	 */
	public Tunnel(int aWidth, int aHeight)
	{
		super(aWidth, aHeight, PictureNames.Tunnel);
	}
	
	/**
	 * Creates a tunnel with specified picture
	 * @param aWidth
	 * @param aHeight
	 * @param aPictureName
	 */
	public Tunnel(int aWidth, int aHeight, String aPictureName)
	{
		super(aWidth, aHeight, aPictureName);
	}
	
	/**
	 * returns the source game scene associated with the tunnel, i.e. the scene the tunnel is in
	 * @return
	 */
	public GameScene getSrc()
	{
		return fSrcScene;
	}
	
	/**
	 * initialies the source game scene to aScene
	 * @param aScene
	 */
	public void setSrc(GameScene aScene)
	{
		fSrcScene = aScene;
	}
	
	/**
	 * 
	 * @return the destination scene of the tunnel
	 */
	public GameScene getDst()
	{
		return fDstScene;
	}
	
	/**
	 * Sets the destination scene of the tunnel
	 * @param aScene
	 */
	public void setDst(GameScene aScene)
	{
		fDstScene = aScene;
	}
}