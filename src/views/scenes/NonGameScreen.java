package views.scenes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import views.ViewElement;

public abstract class NonGameScreen extends ViewElement {
	protected static final Color BACKGROUND_COLOR = Color.WHITE;
	protected static final Font TITLE_FONT = Font.font("Verdana", 20);
	protected static final Font TEXT_FONT = Font.font("Verdana", 10);
	
	protected Rectangle fBackground;
	protected Text fTitleText;
	
	public NonGameScreen(int aWidth, int aHeight, String aTitle)
	{
		super();

		fBackground = new Rectangle();
		fBackground.setWidth(aWidth);
		fBackground.setHeight(aHeight);
		fBackground.setFill(BACKGROUND_COLOR);
		
		fTitleText = createText(aTitle, TITLE_FONT);
		fTitleText.setLayoutX((aWidth - fTitleText.getBoundsInLocal().getWidth())/2);
		fTitleText.setLayoutY(aHeight/4);

		fRoot.getChildren().add(fBackground);
		fRoot.getChildren().add(fTitleText);
	}

	protected Text createText(String aTextString, Font aFontValue)
	{
		Text text = new Text();
		text.setText(aTextString);
		text.setTextAlignment(TextAlignment.CENTER);
		text.setFill(Color.BLACK);
		text.setFont(aFontValue);
		
		return text;
	}
	
}