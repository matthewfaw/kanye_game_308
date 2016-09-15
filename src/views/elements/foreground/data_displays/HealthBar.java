package views.elements.foreground.data_displays;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import views.elements.SceneElement;

/**
 * This class manages all of the content that is to be displayed on the health bar.
 * 
 * The class assumes that the only relevent information to be displayed in the gold cound and 
 * percent health. It does not permit adding other information.
 * It also assumes a default spacing of the text. If the bar height is sufficiently small, then
 * the information will not display properly.  Since the information needs to be readable, however, 
 * it seems reasonble to impose a minimum height on the HealthBar.
 * 
 * The only non-JavaFX dependency in this class is on SceneElement, the superclass;
 * 
 * The health bar can be created:
 * HealthBar bar = new HealthBar(width, height);
 * Set the gold count:
 * bar.setGoldCount(5000);
 * bar.setHealthBarPercentage(94);
 * 
 * @author matthewfaw
 *
 */
public class HealthBar extends SceneElement {
	private static final Color BACKGROUND_COLOR = Color.WHITE;
	private static final int DEFAULT_OFFSET = 20;
	
	private double fPercentHealth;
	private int fGoldCount;
	private Text fText;
	
	/**
	 * creates a new health bar
	 * @param aWidth
	 * @param aHeight
	 */
	public HealthBar(int aWidth, int aHeight)
	{
		super();
		
		fRoot = new Group();
		
		Rectangle background = new Rectangle();
		background.setWidth(aWidth);
		background.setHeight(aHeight);
		background.setFill(BACKGROUND_COLOR);
		
		fPercentHealth = 0;
		fGoldCount = 0;
		
		fText = new Text();
		fText.setTextAlignment(TextAlignment.CENTER);
		fText.setFill(Color.BLACK);
		fText.setLayoutX(background.getX() + DEFAULT_OFFSET);
		fText.setLayoutY(background.getY() + DEFAULT_OFFSET);
		
		fRoot.getChildren().add(background);
		fRoot.getChildren().add(fText);
	}
	
	/**
	 * sets up the health percentage displayed on the screen
	 * @param aPercentHealth
	 */
	public void setHealthBarPercentage(double aPercentHealth)
	{
		fPercentHealth = aPercentHealth;
		setText();
	}
	
	/**
	 * sets up the gold count displayed on the screen
	 * @param aPercentHealth
	 */
	public void setGoldCount(int aGoldCount)
	{
		fGoldCount = aGoldCount;
		setText();
	}
	private void setText() 
	{
		fText.setText("Health: " + fPercentHealth + "%, Gold count: " + fGoldCount);
	}

}