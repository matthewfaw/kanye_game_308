package views.scenes;

public class EndScreen extends NonGameScreen {
	public EndScreen(int aWidth, int aHeight)
	{
		// We don't know if we should display losing or winning until after the game has started
		super(aWidth, aHeight, "");
	}
	
	public void setTitle(String aTitle)
	{
		fTitleText.setText(aTitle);
	}
	
}