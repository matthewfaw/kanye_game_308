package views.elements.foreground.data_displays;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class HealthBar extends DataDisplay {
	private static final Color BACKGROUND_COLOR = Color.WHITE;
	
	private double fPercentHealth;
	private int fGoldCount;
	private Text fText;
	
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
//		setHealthBarPercentage(100.0);
		fText.setTextAlignment(TextAlignment.CENTER);
		fText.setFill(Color.BLACK);
		fText.setLayoutX(background.getX() + 10);
		fText.setLayoutY(background.getY() + 20);
		
		fRoot.getChildren().add(background);
		fRoot.getChildren().add(fText);
	}
	
	public void setHealthBarPercentage(double aPercentHealth)
	{
		fPercentHealth = aPercentHealth;
		setText();
	}
	
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