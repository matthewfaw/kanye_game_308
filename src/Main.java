import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

//controllers
import controllers.GameController;

/**
 * This is the main program.  The purpose of this class is simply to set up
 * the boilerplate, essential components of a JavaFX app.
 * 
 * The static fields are assumed to be positive values.
 * 
 * This class depends on the GameController class's getName method to set the
 * Game's name, the init method to initialize the game, 
 * and the step method to move the game forward in time. The only
 * other dependencies are boilerplate animation and screen display.
 * 
 * This is the entry point of the game. Anyone adding code to the game should
 * not have to alter this class.
 * 
 * @author Matthew C. Faw
 */
public class Main extends Application {
    public static final int SIZE = 400;
    public static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

//    private ExampleGame myGame;
    private GameController fGameController;


    /**
     * Set up everything necessary for basic JavaFX app
     */
    @Override
    public void start (Stage s) {
    	
    	fGameController = new GameController();
    	s.setTitle(fGameController.getGameName());
    	
    	Scene scene = fGameController.init(SIZE, SIZE, getClass().getClassLoader());
    	s.setScene(scene);
    	s.show();
    	
    	KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
    								e -> fGameController.step(SECOND_DELAY));
    	Timeline animation = new Timeline();
    	animation.setCycleCount(Timeline.INDEFINITE);
    	animation.getKeyFrames().add(frame);
    	animation.play();
    }

    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
