import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.nio.file.Paths;

/**
 * Background and cursor selection screen class
 */
public class BackgroundSelectionScreen implements EventHandler<KeyEvent> {
    /**
     * The main and only stage
     */
    Stage stage;

    /**
     * Main pane of the scene
     */
    BorderPane root;

    /**
     * Objects pane of the scene, all objects are atteched to this pane
     */
    AnchorPane objects;

    /**
     * Main scene of the screen
     */
    Scene scene;

    /**
     * Current crosshairs index
     */
    int crosshairIndex = 1;

    /**
     * Current backgrounds index
     */
    int backAndForegroundIndex= 1;

    /**
     * Foreground imageview object
     */
    ImageView fg;

    /**
     * Background imageview object
     */
    ImageView bg;

    /**
     * Crosshair imageview object
     */
    ImageView crosshair;

    /**
     * TitleScreen object which is passed from previous screen (titlescreen), it is used when ESC is pressed.
     */
    TitleScreen ts;

    /**
     * MediaPlayer object for playing title music
     */
    MediaPlayer titleMusicPlayer;

    /**
     * MediaPlayer object for playing intro music
     */
    MediaPlayer introMusicPlayer;

    /**
     * boolean value that is set true when enter is pressed
     * With this variable, when enter is clicked, it is stopped getting another keyboard interaction from player
     */
    boolean isEnterClicked = false;


    /**
     * Main constructor for background selection screen
     * @param ts it takes previous titlescreen object as parameter
     */
    public BackgroundSelectionScreen(TitleScreen ts)  {
        //sets panes
        root = new BorderPane();
        objects = new AnchorPane();
        this.ts = ts;

        //sets background image
        bg = new ImageView(new Image(String.format("file:assets/background/%d.png",backAndForegroundIndex)));
        bg.setFitHeight(DuckHunt.DEFAULT_HEIGHT * DuckHunt.SCALE);
        bg.setFitWidth(DuckHunt.DEFAULT_WIDTH * DuckHunt.SCALE);

        //sets foreground image
        fg = new ImageView(new Image(String.format("file:assets/foreground/%d.png",backAndForegroundIndex)));
        fg.setFitHeight(DuckHunt.DEFAULT_HEIGHT * DuckHunt.SCALE);
        fg.setFitWidth(DuckHunt.DEFAULT_WIDTH * DuckHunt.SCALE);

        //sets crosshair image
        Image crosshairImage = new Image(String.format("file:assets/crosshair/%d.png",crosshairIndex));
        crosshair = new ImageView(new Image(String.format("file:assets/crosshair/%d.png",crosshairIndex)));
        crosshair.setFitWidth(crosshairImage.getWidth()*DuckHunt.SCALE);
        crosshair.setFitHeight(crosshairImage.getHeight()*DuckHunt.SCALE);

        crosshair.setY((DuckHunt.DEFAULT_HEIGHT-13) * DuckHunt.SCALE /2);
        crosshair.setX((DuckHunt.DEFAULT_WIDTH-13) * DuckHunt.SCALE / 2);

        Text arrowKeysText = new Text("USE ARROW KEYS TO NAVIGATE");
        arrowKeysText.setTranslateX(65*DuckHunt.SCALE);
        arrowKeysText.setTranslateY(17*DuckHunt.SCALE);
        arrowKeysText.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE*8));
        arrowKeysText.setFill(Color.ORANGE);

        Text enterText = new Text("PRESS ENTER TO START");
        enterText.setTranslateX(81*DuckHunt.SCALE);
        enterText.setTranslateY(25*DuckHunt.SCALE);
        enterText.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE*8));
        enterText.setFill(Color.ORANGE);


        Text escText = new Text("PRESS ESC TO EXIT");
        escText.setTranslateX(90*DuckHunt.SCALE);
        escText.setTranslateY(33*DuckHunt.SCALE);
        escText.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE*8));
        escText.setFill(Color.ORANGE);


        //sets panes
        objects.getChildren().addAll(fg, crosshair, arrowKeysText, enterText, escText);
        root.getChildren().addAll(bg, objects);
        setCrosshair();
        scene = new Scene(root, DuckHunt.DEFAULT_WIDTH*DuckHunt.SCALE, DuckHunt.DEFAULT_HEIGHT*DuckHunt.SCALE);
    }

    /**
     * This method should be called to set background selection screen to stage
     * @param stage
     * @param titleMusicPlayer
     */
    public void start(Stage stage, MediaPlayer titleMusicPlayer) {
        this.stage = stage;
        stage.setScene(scene);
        this.titleMusicPlayer = titleMusicPlayer;
        scene.setOnKeyPressed(this);
    }


    /**
     * In backgroundselectionscreen, when a keyboard key is pressed, this method is called
     * If ESC is pressed, the stage is set to previous scene (title screen), and all the differences that are made will be undoed
     * If UP and DOWN is pressed crosshair changes
     * If LEFT and RIGHT is pressed background changes
     * If ENTER is pressed Level1 is on the stage
     * @param keyEvent the event which occurred
     */
    @Override
    public void handle(KeyEvent keyEvent) {
        if (!isEnterClicked) {

            String key = keyEvent.getCode().toString();
            if (key.equals("UP")) {
                crosshairIndex++;
                setCrosshair();
            }
            else if (key.equals("DOWN")) {
                crosshairIndex--;
                setCrosshair();
            }
            else if (key.equals("RIGHT")) {
                backAndForegroundIndex++;
                setBackAndForeground();
            }
            else if (key.equals("LEFT")) {
                backAndForegroundIndex--;
                setBackAndForeground();
            }
            else if (key.equals("ENTER")) {
                isEnterClicked = true;
                titleMusicPlayer.stop();

                Media introMusic = new Media(new File("assets/effects/Intro.mp3").toURI().toString());
                introMusicPlayer = new MediaPlayer(introMusic);
                introMusicPlayer.setVolume(DuckHunt.VOLUME);
                introMusicPlayer.play();
                introMusicPlayer.setOnEndOfMedia(() -> {
                    Level1 level1 = new Level1(stage,backAndForegroundIndex, crosshairIndex);
                    level1.startGame();
                });

            }
            else if (key.equals("ESCAPE")) {
                crosshairIndex = 1;
                backAndForegroundIndex = 1;
                setCrosshair();
                setBackAndForeground();
                ts.setStage();
            }
        }
    }

    /**
     * A method to change background and foreground
     */
    public void setBackAndForeground() {
        if (backAndForegroundIndex==0)
            backAndForegroundIndex = 6;
        else if (backAndForegroundIndex==7)
            backAndForegroundIndex = 1;

        Image background = new Image(String.format("file:assets/background/%d.png",backAndForegroundIndex));
        Image foreground = new Image(String.format("file:assets/foreground/%d.png",backAndForegroundIndex));

        bg.setImage(background);
        fg.setImage(foreground);
    }


    /**
     * A method to change crosshair
     */
    public void setCrosshair() {
        if (crosshairIndex==0)
            crosshairIndex = 7;
        else if (crosshairIndex==8)
            crosshairIndex = 1;

        Image newCrosshair = new Image(String.format("file:assets/crosshair/%d.png",crosshairIndex));
        crosshair.setImage(newCrosshair);
    }
}
