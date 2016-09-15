package views.elements.foreground.obstacles;

import utils.PictureNames;
import views.scenes.GameScene;

/**
 * This class allows us to further take advantage of reflection,
 * by allowing us to differentiate between any normal Obstacle and a tunnel.
 * This allows us to have special logic for when we identify a tunnel, namely to transition scenes.
 * The tunnel object has a link to a new scene to transition to--it is the tunnel through which scene transitions
 * occur.
 * 
 * This class assumes that if a user wants to use the Tunnel's link to a new scene, that the user will first set that scene.
 * 
 * The code depends on the GameScene class since the tunnel keeps track of it's source and destination scenes. It also depends
 * on the Obstacle superclass and PictureNames.
 * 
 * Once a tunnel object has been created, we set the destination scene by:
 * tunnel.setDst(dstGameScene);
 * GameScene dstScene = tunnel.getDst();
 * 
 * @author matthewfaw
 *
 */

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