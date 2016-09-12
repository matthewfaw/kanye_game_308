package views.scenes;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class StartScreen extends NonGameScreen {
	private static final int MAX_BUTTON_WIDTH = 100;
	private static final int SPACING = 20;
	
	private Button fEasyButton;
	private Button fHardButton;

	/**
	 * Creates a splash screen for the game
	 * @param aWidth
	 * @param aHeight
	 * @param aTitle
	 * @param aGameExplanation
	 */
	public StartScreen(int aWidth, int aHeight, String aTitle, String aGameExplanation)
	{
		super(aWidth, aHeight, aTitle);

		Text explanationText = createText(aGameExplanation, TEXT_FONT);
		explanationText.setLayoutX((aWidth - explanationText.getBoundsInParent().getWidth())/2);
		explanationText.setLayoutY(getYPositionUnderNode(fTitleText));
		
		fEasyButton = createButton("Easy Game");
		fEasyButton.setLayoutX(fTitleText.getBoundsInParent().getMinX());
		fEasyButton.setLayoutY(getYPositionUnderNode(explanationText));
		
		fHardButton = createButton("Hard Game");
		fHardButton.setLayoutX(fTitleText.getBoundsInParent().getMaxX() - fHardButton.getMaxWidth());
		fHardButton.setLayoutY(getYPositionUnderNode(explanationText));
		
		fRoot.getChildren().add(explanationText);
		fRoot.getChildren().add(fEasyButton);
		fRoot.getChildren().add(fHardButton);
	}
	
	private Button createButton(String aDifficulty)
	{
		Button button = new Button();
		button.setText(aDifficulty);
		button.setMaxWidth(MAX_BUTTON_WIDTH);
		
		return button;
	}
	
	private double getYPositionUnderNode(Node aNode)
	{
	return aNode.getBoundsInParent().getMaxY() + SPACING;
	}
	
	/**
	 * 
	 * @return the button associated with the easy game option
	 */
	public Button getEasyButton()
	{
		return fEasyButton;
	}
	/**
	 * 
	 * @return the button associated with the hard game option
	 */
	public Button getHardButton()
	{
		return fHardButton;
	}
}