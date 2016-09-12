import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

//controllers
import controllers.GameController;

/**
 * This is the main program, it is basically boilerplate to create
 * an animated scene.
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
