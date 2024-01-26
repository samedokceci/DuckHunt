import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.nio.file.Paths;

/**
 * A class which is created for Title Screen
 */
public class TitleScreen implements EventHandler<KeyEvent> {
    /**
     * The main and only stage
     */
    Stage stage;

    /**
     * First scene of the game, enter scene
     */
    Scene enterScene;

    /**
     * Main and only pane of the scene
     */
    BorderPane root;

    /**
     * Timeline object which is for flashing text animation
     */
    Timeline flashingTextAnimation;

    /**
     * An object of background selection screen, which will be executed after this class
     */
    BackgroundSelectionScreen backgroundSelectionScreen;

    /**
     * Media player object to play title music (Title.mp3)
     */
    MediaPlayer titleMusicPlayer;

    /**
     * TitleScreen constructor which takes main stage as parameter from DuckHunt class and passes to the next class
     * @param stage
     */
    public TitleScreen(Stage stage)   {
        this.stage = stage;
        backgroundSelectionScreen = new BackgroundSelectionScreen(this);
        root = new BorderPane();

        Image welcomeImage = new Image("file:assets/welcome/1.png");

        Text enterText = new Text("PRESS ENTER TO START");
        enterText.setTranslateX(25*DuckHunt.SCALE);
        enterText.setTranslateY(165*DuckHunt.SCALE);
        enterText.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE*17));
        enterText.setFill(Color.ORANGE);

        Text escText = new Text("PRESS ESC TO EXIT");
        escText.setTranslateX(38*DuckHunt.SCALE);
        escText.setTranslateY(182*DuckHunt.SCALE);
        escText.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE*17));
        escText.setFill(Color.ORANGE);

        Media titleMusic = new Media(new File("assets/effects/Title.mp3").toURI().toString());
        titleMusicPlayer = new MediaPlayer(titleMusic);
        titleMusicPlayer.setCycleCount(Animation.INDEFINITE);
        titleMusicPlayer.setVolume(DuckHunt.VOLUME);

        flashingTextAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0.5), evt -> enterText.setVisible(false)),
                new KeyFrame(Duration.seconds( 1.2), evt -> enterText.setVisible(true)),
                new KeyFrame(Duration.seconds(0.5), evt -> escText.setVisible(false)),
                new KeyFrame(Duration.seconds( 1.2), evt -> escText.setVisible(true)));
        flashingTextAnimation.setCycleCount(Animation.INDEFINITE);


        ImageView iw = new ImageView(welcomeImage);
        iw.setFitHeight(DuckHunt.DEFAULT_HEIGHT*DuckHunt.SCALE);
        iw.setFitWidth(DuckHunt.DEFAULT_WIDTH*DuckHunt.SCALE);



        root.getChildren().addAll(iw, enterText, escText);

        enterScene = new Scene(root, DuckHunt.DEFAULT_WIDTH * DuckHunt.SCALE, DuckHunt.DEFAULT_HEIGHT * DuckHunt.SCALE);
        titleMusicPlayer.play();
        enterScene.setOnKeyPressed(this);
    }

    /**
     * Calling this method turns main screen into title screen
     */
    public void setStage() {
        stage.setScene(enterScene);
        stage.setTitle("Duck Hunt");
        stage.getIcons().add(new Image("file:assets/favicon/1.png"));
        flashingTextAnimation.play();
        stage.show();
    }

    /**
     * In titlescreen, when a keyboard key is pressed, this method is called
     * If ESC is pressed, game will be closed; if enter is pressed, background selection screen is run
     * @param keyEvent the event which occurred
     */
    @Override
    public void handle(KeyEvent keyEvent) {
        String key = keyEvent.getCode().toString();
        if (key.equals("ENTER")) {
            backgroundSelectionScreen.start(stage, titleMusicPlayer);
        }

        else if (key.equals("ESCAPE"))
            stage.close();
    }
}
