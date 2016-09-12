package views.elements.foreground.data_displays;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import views.elements.SceneElement;

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