import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

//Music
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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
     * Set things up at the beginning.
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
//        // create your own game here
//        myGame = new ExampleGame();
//        s.setTitle(myGame.getTitle());
//
//        Media media = new Media(getClass().getClassLoader().getResource("all_falls_down.mp3").toString());
//        MediaPlayer player = new MediaPlayer(media);
//        player.play();
//        
//        // attach game to the stage and display it
//        Scene scene = myGame.init(SIZE, SIZE);
//        s.setScene(scene);
//        s.show();
//
//        // sets the game's loop
//        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
//                                      e -> myGame.step(SECOND_DELAY));
//        Timeline animation = new Timeline();
//        animation.setCycleCount(Timeline.INDEFINITE);
//        animation.getKeyFrames().add(frame);
//        animation.play();
        
    }

    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
