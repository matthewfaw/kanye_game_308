package views.scenes;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import views.ViewElement;

public class StartScreen extends NonGameScreen {
	private Button fEasyButton;
	private Button fHardButton;

	public StartScreen(int aWidth, int aHeight, String aTitle, String aGameExplanation)
	{
		super(aWidth, aHeight, aTitle);

		Text explanationText = createText(aGameExplanation, Font.font("Verdana", 12));
		explanationText.setLayoutX((aWidth - explanationText.getBoundsInParent().getWidth())/2);
		explanationText.setLayoutY(calculateYSpacing(fTitleText));
		
		fEasyButton = createButton("Easy Game");
		fEasyButton.setLayoutX(fTitleText.getBoundsInParent().getMinX());
		fEasyButton.setLayoutY(calculateYSpacing(explanationText));
		
		fHardButton = createButton("Hard Game");
		fHardButton.setLayoutX(fTitleText.getBoundsInParent().getMaxX() - fHardButton.getMaxWidth());
		fHardButton.setLayoutY(calculateYSpacing(explanationText));
		
		fRoot.getChildren().add(explanationText);
		fRoot.getChildren().add(fEasyButton);
		fRoot.getChildren().add(fHardButton);
	}
	
	private Button createButton(String aDifficulty)
	{
		Button button = new Button();
		button.setText(aDifficulty);
		button.setMaxWidth(100);
		
		return button;
	}
	
	private double calculateYSpacing(Node aNode)
	{
		return aNode.getBoundsInParent().getMaxY() + aNode.getBoundsInParent().getHeight();
	}
	
	public Button getEasyButton()
	{
		return fEasyButton;
	}
	public Button getHardButton()
	{
		return fHardButton;
	}
}