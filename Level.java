import javafx.animation.*;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
import java.util.ArrayList;


/**
 * Abstract Level class which is ancestor of all levels in the game
 */
public abstract class Level {
    /**
     * The main and only stage
     */
    Stage stage;

    /**
     * Scene of the current level
     */
    Scene scene;

    /**
     * Main pane of the current level
     */
    BorderPane root;

    /**
     * All objects except background image is attached to this pane, and this pane is attached to main pane
     */
    AnchorPane objects;

    /**
     * TitleScreen object
     */
    TitleScreen ts;

    /**
     * Crosshair object
     */
    ImageView crosshairIW;

    /**
     * Foreground object
     */
    ImageView fg;

    /**
     * Background object
     */
    ImageView bg;

    /**
     * MediaPlayer object to shot audio
     */
    MediaPlayer shotAudioEffectPlayer;

    /**
     * MediaPlayer object to game over audio
     */
    MediaPlayer gameOverAudioEffectPlayer;

    /**
     * MediaPlayer object to level completed audio
     */
    MediaPlayer levelCompletedAudioEffectPlayer;

    /**
     * Timeline object to play flashing text for win texts
     */
    Timeline winFlashTextAnimation;

    /**
     * Timeline object to play flashing text for lose texts
     */
    Timeline loseFlashTextAnimation;

    /**
     * Text object that indicates left ammos
     */
    Text ammoText;

    /**
     * Text announces that you win
     */
    Text youWinText;

    /**
     * Text announces that you lose
     */
    Text gameOverText;

    /**
     * Text announces you to press enter to play next level
     */
    Text enterToNextLevelText;

    /**
     * Text announces you to press enter to play again
     */
    Text enterToPlayAgainText;

    /**
     * Text announces pressing esc to return title screen
     */
    Text escToExitText;

    /**
     * Text indicates current level
     */
    Text levelText;

    /**
     * ArrayList of all ducks in the current level
     */
    ArrayList<Duck> ducks = new ArrayList<>();

    /**
     * Index of the current background image
     */
    int bgIndex;

    /**
     * Index of the current crosshair
     */
    int crosshairIndex;

    /**
     * Counter for the shot birds
     */
    int shotBirdCount = 0;

    /**
     * boolean variable to check if game is finished
     */
    boolean isFinished = false;

    /**
     * ammo count that is 3 per duck
     */
    int ammo;

    /**
     * Level constructor inculdes all code lines that a level should have
     * @param stage
     * @param bgIndex
     * @param crosshairIndex
     */
    public Level(Stage stage, int bgIndex, int crosshairIndex) {
        //sets panes
        this.stage = stage;
        this.bgIndex = bgIndex;
        this.crosshairIndex = crosshairIndex;

        root = new BorderPane();
        objects = new AnchorPane();


        //sets background image
        bg = new ImageView(new Image(String.format("file:assets/background/%d.png", bgIndex)));
        bg.setFitHeight(DuckHunt.DEFAULT_HEIGHT * DuckHunt.SCALE);
        bg.setFitWidth(DuckHunt.DEFAULT_WIDTH * DuckHunt.SCALE);

        //sets foreground image
        fg = new ImageView(new Image(String.format("file:assets/foreground/%d.png", bgIndex)));
        fg.setFitHeight(DuckHunt.DEFAULT_HEIGHT * DuckHunt.SCALE);
        fg.setFitWidth(DuckHunt.DEFAULT_WIDTH * DuckHunt.SCALE);

        //sets crosshair


        ammoText = new Text(String.format("Ammo Left: %d", ammo));
        ammoText.setTranslateX(195 * DuckHunt.SCALE);
        ammoText.setTranslateY(10 * DuckHunt.SCALE);
        ammoText.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 8));
        ammoText.setFill(Color.ORANGE);

        youWinText = new Text("YOU WIN!");
        youWinText.setTranslateX(93 * DuckHunt.SCALE);
        youWinText.setTranslateY(110 * DuckHunt.SCALE);
        youWinText.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 15));
        youWinText.setFill(Color.ORANGE);
        youWinText.setVisible(false);

        enterToNextLevelText = new Text("Press ENTER to play next level");
        enterToNextLevelText.setTranslateX(20 * DuckHunt.SCALE);
        enterToNextLevelText.setTranslateY(124 * DuckHunt.SCALE);
        enterToNextLevelText.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 15));
        enterToNextLevelText.setFill(Color.ORANGE);
        enterToNextLevelText.setVisible(false);

        gameOverText = new Text("GAME OVER!");
        gameOverText.setTranslateX(80 * DuckHunt.SCALE);
        gameOverText.setTranslateY(115 * DuckHunt.SCALE);
        gameOverText.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 15));
        gameOverText.setFill(Color.ORANGE);
        gameOverText.setVisible(false);


        enterToPlayAgainText = new Text("Press ENTER to play again");
        enterToPlayAgainText.setTranslateX(30 * DuckHunt.SCALE);
        enterToPlayAgainText.setTranslateY(129 * DuckHunt.SCALE);
        enterToPlayAgainText.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 15));
        enterToPlayAgainText.setFill(Color.ORANGE);
        enterToPlayAgainText.setVisible(false);


        escToExitText = new Text("Press ESC to exit");
        escToExitText.setTranslateX(65 * DuckHunt.SCALE);
        escToExitText.setTranslateY(142 * DuckHunt.SCALE);
        escToExitText.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE * 15));
        escToExitText.setFill(Color.ORANGE);
        escToExitText.setVisible(false);

        levelText = new Text("Level 1/6");
        levelText.setTranslateX(112*DuckHunt.SCALE);
        levelText.setTranslateY(10*DuckHunt.SCALE);
        levelText.setFont(Font.font("Arial", FontWeight.BOLD, DuckHunt.SCALE*8));
        levelText.setFill(Color.ORANGE);

        loseFlashTextAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0.5), evt -> enterToPlayAgainText.setVisible(false)),
                new KeyFrame(Duration.seconds(0.2), evt -> enterToPlayAgainText.setVisible(true)),
                new KeyFrame(Duration.seconds(0.5), evt -> escToExitText.setVisible(false)),
                new KeyFrame(Duration.seconds(0.2), evt -> escToExitText.setVisible(true)));
        loseFlashTextAnimation.setCycleCount(Animation.INDEFINITE);

        winFlashTextAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0.5), evt -> enterToNextLevelText.setVisible(false)),
                new KeyFrame(Duration.seconds(0.2), evt -> enterToNextLevelText.setVisible(true)));
        winFlashTextAnimation.setCycleCount(Animation.INDEFINITE);

        Image newCrosshair = new Image(String.format("file:assets/crosshair/%d.png", crosshairIndex));

//        root.setCursor(new ImageCursor(newCrosshair));
        crosshairIW = new ImageView(newCrosshair);
        crosshairIW.setFitHeight(newCrosshair.getHeight() * DuckHunt.SCALE);
        crosshairIW.setFitWidth(newCrosshair.getWidth() * DuckHunt.SCALE);



        root.setOnMouseMoved(e -> {
            crosshairIW.setX(e.getX());
            crosshairIW.setY(e.getY());
        });

        //when bird is shot
        root.setOnMouseClicked(e -> {
            if (!isFinished) {
                shoot(e.getSceneX(), e.getSceneY());
            }
        });
    }

    /**
     * A method which is called when user clicks to screen. This method checks if birds are shot or not. Also, it is responsible for deciding when to finish the game
     * @param shotX
     * @param shotY
     */
    public void shoot (double shotX, double shotY) {
        Media shotAudioEffect = new Media(new File("assets\\effects\\Gunshot.mp3").toURI().toString());
        shotAudioEffectPlayer = new MediaPlayer(shotAudioEffect);
        shotAudioEffectPlayer.setVolume(DuckHunt.VOLUME);
        shotAudioEffectPlayer.play();
        ammoText.setText(String.format("Ammo Left: %d",--ammo));

        for (Duck duck:ducks) {
            Bounds duckBounds = duck.duckIW.localToScene(duck.duckIW.getBoundsInLocal());
            if ((duckBounds.getMinX()<=shotX && shotX<=duckBounds.getMaxX()) && (duckBounds.getMinY()<=shotY && shotY<=duckBounds.getMaxY()) && duck.isAlive) {
                duck.dyingAnimation(shotX, shotY);
                shotBirdCount++;
                duck.isAlive = false;
            }
        }

        if (shotBirdCount==ducks.size()) {
            gameFinished();
        }

        else if (ammo<=0) {
            isFinished = true;
            Media gameoverAudioEffect = new Media(new File("assets\\effects\\GameOver.mp3").toURI().toString());
            gameOverAudioEffectPlayer = new MediaPlayer(gameoverAudioEffect);
            gameOverAudioEffectPlayer.setVolume(DuckHunt.VOLUME);
            gameOverAudioEffectPlayer.play();

            enterToPlayAgainText.setVisible(true);
            escToExitText.setVisible(true);
            gameOverText.setVisible(true);

            loseFlashTextAnimation.play();
            scene.setOnKeyPressed(keyEvent -> {
                String key = keyEvent.getCode().toString();
                if (key.equals("ENTER")) {
                    Level1 level1 = new Level1( stage, bgIndex, crosshairIndex);
                    level1.startGame();
                }
                else if (key.equals("ESCAPE")) {
                    TitleScreen ts = new TitleScreen(stage);
                    ts.setStage();                }
            });
        }
    }

    /**
     * A method which is called when the current level is passed
     */
    public void nextLevel() {
        Level2 level2 = new Level2( stage, bgIndex, crosshairIndex);
        level2.startGame();
    }

    /**
     * A helper method for shoot method, which is called when the game is finished
     */
    public void gameFinished() {
        isFinished = true;

        Media levelCompletedAudioEffect = new Media(new File("assets\\effects\\LevelCompleted.mp3").toURI().toString());
        levelCompletedAudioEffectPlayer = new MediaPlayer(levelCompletedAudioEffect);
        levelCompletedAudioEffectPlayer.setVolume(DuckHunt.VOLUME);
        levelCompletedAudioEffectPlayer.play();

        enterToNextLevelText.setVisible(true);
        youWinText.setVisible(true);
        winFlashTextAnimation.play();
        scene.setOnKeyPressed(keyEvent -> {
            String key = keyEvent.getCode().toString();
            if (key.equals("ENTER")) {
                levelCompletedAudioEffectPlayer.stop();
                nextLevel();
            }
        });
    }

    /**
     * Must be called to play next level, sets the scene of next level and makes birds fly
     */
    public void startGame() {
        stage.setScene(scene);
        for (Duck duck : ducks) {
            duck.flyingAnimation.play();
        }
    }
}


