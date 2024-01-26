import javafx.animation.*;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.nio.file.Paths;

public class Level6 extends Level{
    /**
     * First Type2 BlackDuck object
     */
    Duck blackDuck1Type2;

    /**
     * Second Type2 BlackDuck object
     */
    Duck blackDuck2Type2;

    /**
     * First Type2 RedDuck object
     */
    Duck redDuck1Type2;

    /**
     * Second Type2 RedDuck object
     */
    Duck redDuck2Type2;

    /**
     * First Type2 BlueDuck object
     */
    Duck blueDuck1Type2;

    /**
     * Second Type2 BlueDuck object
     */
    Duck blueDuck2Type2;

    /**
     * Level6 constructor
     * takes all the parameters which is same for all levels
     * @param stage
     * @param bgIndex
     * @param crosshairIndex
     */
    public Level6(Stage stage, int bgIndex, int crosshairIndex)  {
        super(stage, bgIndex, crosshairIndex);
        //blackduck 1
        blackDuck1Type2 = new BlackDuck();
        blackDuck1Type2.flappingAnimationImages = new Image[]{blackDuck1Type2.image1, blackDuck1Type2.image2, blackDuck1Type2.image3};
        blackDuck1Type2.xVelocity = 20;
        blackDuck1Type2.yVelocity = -10;
        blackDuck1Type2.xPosition = 214;
        blackDuck1Type2.yPosition = 70;

        blackDuck1Type2.flyingAnimation = new Timeline(new KeyFrame(Duration.millis(100), event -> blackDuck1Type2.flyingType2()));
        blackDuck1Type2.flyingAnimation.setCycleCount(Timeline.INDEFINITE);

        //blackduck 2
        blackDuck2Type2 = new BlackDuck();
        blackDuck2Type2.flappingAnimationImages = new Image[]{blackDuck2Type2.image1, blackDuck2Type2.image2, blackDuck2Type2.image3};
        blackDuck2Type2.xVelocity = -25;
        blackDuck2Type2.yVelocity = -13;
        blackDuck2Type2.xPosition = 200;
        blackDuck2Type2.yPosition = 210;
        blackDuck2Type2.xScale = -1;
        blackDuck2Type2.duckIW.setScaleX(blackDuck2Type2.xScale);

        blackDuck2Type2.flyingAnimation = new Timeline(new KeyFrame(Duration.millis(100), event -> blackDuck2Type2.flyingType2()));
        blackDuck2Type2.flyingAnimation.setCycleCount(Timeline.INDEFINITE);


        //blueduck 1
        blueDuck1Type2 = new BlueDuck();
        blueDuck1Type2.flappingAnimationImages = new Image[]{blueDuck1Type2.image1, blueDuck1Type2.image2, blueDuck1Type2.image3};
        blueDuck1Type2.xVelocity = 20;
        blueDuck1Type2.yVelocity = -12;
        blueDuck1Type2.xPosition = 15;
        blueDuck1Type2.yPosition = 214;


        blueDuck1Type2.flyingAnimation = new Timeline(new KeyFrame(Duration.millis(100), event -> blueDuck1Type2.flyingType2()));
        blueDuck1Type2.flyingAnimation.setCycleCount(Timeline.INDEFINITE);


        //blueduck 2
        blueDuck2Type2 = new BlueDuck();
        blueDuck2Type2.flappingAnimationImages = new Image[]{blueDuck2Type2.image1, blueDuck2Type2.image2, blueDuck2Type2.image3};
        blueDuck2Type2.xVelocity = -20;
        blueDuck2Type2.yVelocity = -10;
        blueDuck2Type2.xPosition = 177;
        blueDuck2Type2.yPosition = 176;
        blueDuck2Type2.xScale = -1;
        blueDuck2Type2.duckIW.setScaleX(blueDuck2Type2.xScale);

        blueDuck2Type2.flyingAnimation = new Timeline(new KeyFrame(Duration.millis(100), event -> blueDuck2Type2.flyingType2()));
        blueDuck2Type2.flyingAnimation.setCycleCount(Timeline.INDEFINITE);



        //redduck 2
        redDuck1Type2 = new RedDuck();
        redDuck1Type2.flappingAnimationImages = new Image[]{redDuck1Type2.image1, redDuck1Type2.image2, redDuck1Type2.image3};
        redDuck1Type2.xVelocity = -15;
        redDuck1Type2.yVelocity = -14;
        redDuck1Type2.xPosition = 119;
        redDuck1Type2.yPosition = 186;
        redDuck1Type2.xScale = -1;
        redDuck1Type2.duckIW.setScaleX(redDuck1Type2.xScale);


        redDuck1Type2.duckIW.setX(redDuck1Type2.xPosition*DuckHunt.SCALE);
        redDuck1Type2.duckIW.setY(redDuck1Type2.yPosition*DuckHunt.SCALE);


        redDuck1Type2.flyingAnimation = new Timeline(new KeyFrame(Duration.millis(100), event -> redDuck1Type2.flyingType2()));
        redDuck1Type2.flyingAnimation.setCycleCount(Timeline.INDEFINITE);

        //redduck 2
        redDuck2Type2 = new RedDuck();
        redDuck2Type2.flappingAnimationImages = new Image[]{redDuck2Type2.image1, redDuck2Type2.image2, redDuck2Type2.image3};
        redDuck2Type2.xVelocity = 20;
        redDuck2Type2.yVelocity = -11;
        redDuck2Type2.xPosition = 123;
        redDuck2Type2.yPosition = 212;

        redDuck2Type2.duckIW.setX(redDuck2Type2.xPosition*DuckHunt.SCALE);
        redDuck2Type2.duckIW.setY(redDuck2Type2.yPosition*DuckHunt.SCALE);

        redDuck2Type2.flyingAnimation = new Timeline(new KeyFrame(Duration.millis(100), event -> redDuck2Type2.flyingType2()));
        redDuck2Type2.flyingAnimation.setCycleCount(Timeline.INDEFINITE);

        ducks.add(redDuck1Type2);
        ducks.add(redDuck2Type2);
        ducks.add(blueDuck1Type2);
        ducks.add(blueDuck2Type2);
        ducks.add(blackDuck1Type2);
        ducks.add(blackDuck2Type2);

        this.ammo = ducks.size() * 3;
        ammoText.setText(String.format("Ammo Left: %d",ammo));

        levelText.setText("Level 6/6");



        //sets panes
        objects.getChildren().addAll(blackDuck1Type2.duckIW, blackDuck2Type2.duckIW,blueDuck1Type2.duckIW, blueDuck2Type2.duckIW, redDuck1Type2.duckIW,redDuck2Type2.duckIW , fg, ammoText, levelText, enterToNextLevelText,enterToPlayAgainText, youWinText,escToExitText, gameOverText, crosshairIW);
        root.getChildren().addAll(bg, objects);

        scene = new Scene(root, DuckHunt.DEFAULT_WIDTH*DuckHunt.SCALE, DuckHunt.DEFAULT_HEIGHT*DuckHunt.SCALE);
        scene.setCursor(Cursor.NONE);
    }

    /**
     * This is executed only for Level6, all other levels use default gameFinished method of Level abstract class
     */
    public void gameFinished() {
        isFinished = true;

        youWinText.setText("You have completed the game!");
        youWinText.setTranslateX(14 * DuckHunt.SCALE);
        youWinText.setTranslateY(110 * DuckHunt.SCALE);

        enterToNextLevelText.setText("Press ENTER to play again");
        enterToNextLevelText.setTranslateX(29 * DuckHunt.SCALE);
        enterToNextLevelText.setTranslateY(126 * DuckHunt.SCALE);

        winFlashTextAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0.5), evt -> enterToNextLevelText.setVisible(false)),
                new KeyFrame(Duration.seconds(0.2), evt -> enterToNextLevelText.setVisible(true)),
                new KeyFrame(Duration.seconds(0.5), evt -> escToExitText.setVisible(false)),
                new KeyFrame(Duration.seconds(0.2), evt -> escToExitText.setVisible(true)));
        winFlashTextAnimation.setCycleCount(Animation.INDEFINITE);


        Media levelCompletedAudioEffect = new Media(new File("assets/effects/GameCompleted.mp3").toURI().toString());
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
            else if (key.equals("ESCAPE")) {
                TitleScreen ts = new TitleScreen(stage);
                ts.setStage();
            }
        });
    }

    /**
     * To play next level this method is run
     */
    public void nextLevel() {
        Level1 level1 = new Level1( stage, bgIndex, crosshairIndex);
        level1.startGame();
    }
}
