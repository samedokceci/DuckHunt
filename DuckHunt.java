import javafx.application.Application;
import javafx.stage.Stage;

public class DuckHunt extends Application {

    /**
     * Game volume, varies between [0,1], when it is changed all value of the game is effected
     */
    public static double VOLUME = 0.025;

    /**
     * Default Height of the stage, background and foreground images
     */
    public static int DEFAULT_HEIGHT = 240;

    /**
     * Default Width of the stage, background and foreground images
     */
    public static int DEFAULT_WIDTH = 256;

    /**
     * Scale value which is multiplied by all objects. When it is increased all objects will be bigger and vice versa
     */
    public static int SCALE = 4;

    /**
     * First method to run game
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Runs titlescreen
     * Sets stages height and width
     * @param stage the primary stage for this application, onto which
     * the application scene can be set. The primary stage will be embedded in
     * the browser if the application was launched as an applet.
     * Applications may create other stages, if needed, but they will not be
     * primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage stage)  {
        stage.setHeight(DEFAULT_HEIGHT * SCALE);
        stage.setWidth(DEFAULT_WIDTH * SCALE);
        TitleScreen ts = new TitleScreen(stage);
        ts.setStage();
    }
}
