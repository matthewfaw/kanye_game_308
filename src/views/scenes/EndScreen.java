package views.scenes;

public class EndScreen extends NonGameScreen {
	/**
	 * Creates an ending screen to display a final message
	 * @param aWidth
	 * @param aHeight
	 */
	public EndScreen(int aWidth, int aHeight)
	{
		// We don't know if we should display losing or winning until after the game has started
		super(aWidth, aHeight, "");
	}
	
	/**
	 * Modify the text on the scene to aTitle
	 * @param aTitle
	 */
	public void setTitle(String aTitle)
	{
		fTitleText.setText(aTitle);
	}
	
}