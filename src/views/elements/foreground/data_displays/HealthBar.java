package views.elements.foreground.data_displays;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class HealthBar extends DataDisplay {
	private static final Color BACKGROUND_COLOR = Color.WHITE;
	
	private int fHealthPercentage;
	private Text fText;
	
	public HealthBar(int aWidth, int aHeight)
	{
		super();
		
		fRoot = new Group();
		
		Rectangle background = new Rectangle();
		background.setWidth(aWidth);
		background.setHeight(aHeight);
		background.setFill(BACKGROUND_COLOR);
		
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
		fText.setText("Health: " + aPercentHealth + "%");
	}
}