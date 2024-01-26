import javafx.animation.*;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Level 3 class
 */
public class Level3 extends Level{
    /**
     * BlackDuck object
     */
    Duck blackDuck;

    /**
     * BlueDuck object
     */
    Duck blueDuck;

    /**
     * RedDuck object
     */
    Duck redDuck;

    /**
     * Level3 constructor
     * takes all the parameters which is same for all levels
     * @param stage
     * @param bgIndex
     * @param crosshairIndex
     */
    public Level3(Stage stage, int bgIndex, int crosshairIndex)  {
        //sets panes
        super(stage, bgIndex, crosshairIndex);

        blackDuck = new BlackDuck();
        blackDuck.flappingAnimationImages = new Image[]{blackDuck.image4, blackDuck.image5, blackDuck.image6};
        blackDuck.xVelocity = 20;
        blackDuck.yVelocity = 0;
        blackDuck.xPosition = 20;
        blackDuck.yPosition = 35;
        blackDuck.xScale = 1;
        blackDuck.yScale = 1;
        blackDuck.flyingAnimation = new Timeline(new KeyFrame(Duration.millis(200), event ->blackDuck.flyingType1()));
        blackDuck.flyingAnimation.setCycleCount(Timeline.INDEFINITE);

        blueDuck = new BlueDuck();
        blueDuck.flappingAnimationImages = new Image[]{blueDuck.image4, blueDuck.image5, blueDuck.image6};
        blueDuck.xVelocity = -20;
        blueDuck.yVelocity = 0;
        blueDuck.xPosition = 225;
        blueDuck.yPosition = 85;
        blueDuck.xScale = -1;
        blueDuck.yScale = 1;
        blueDuck.scaled = true;
        blueDuck.duckIW.setScaleX(blueDuck.xScale);
        blueDuck.flyingAnimation = new Timeline(new KeyFrame(Duration.millis(200), event -> blueDuck.flyingType1()));
        blueDuck.flyingAnimation.setCycleCount(Timeline.INDEFINITE);

        //redduck 2
        redDuck = new RedDuck();
        redDuck.flappingAnimationImages = new Image[]{redDuck.image1, redDuck.image2, redDuck.image3};
        redDuck.xVelocity = -15;
        redDuck.yVelocity = -15;
        redDuck.xPosition = 119;
        redDuck.yPosition = 186;
        redDuck.xScale = -1;
        redDuck.duckIW.setScaleX(redDuck.xScale);


        redDuck.duckIW.setX(redDuck.xPosition*DuckHunt.SCALE);
        redDuck.duckIW.setY(redDuck.yPosition*DuckHunt.SCALE);


        redDuck.flyingAnimation = new Timeline(new KeyFrame(Duration.millis(150), event -> redDuck.flyingType2()));
        redDuck.flyingAnimation.setCycleCount(Timeline.INDEFINITE);

        ducks.add(redDuck);
        ducks.add(blueDuck);
        ducks.add(blackDuck);

        this.ammo = ducks.size()*3;
        ammoText.setText(String.format("Ammo Left: %d",ammo));

        levelText.setText("Level 3/6");

        //sets panes
        objects.getChildren().addAll(blackDuck.duckIW, blueDuck.duckIW, redDuck.duckIW, fg, ammoText, levelText, enterToNextLevelText,enterToPlayAgainText, youWinText,escToExitText, gameOverText, crosshairIW);
        root.getChildren().addAll(bg, objects);

        scene = new Scene(root, DuckHunt.DEFAULT_WIDTH*DuckHunt.SCALE, DuckHunt.DEFAULT_HEIGHT*DuckHunt.SCALE);
        scene.setCursor(Cursor.NONE);

    }

    /**
     * To play next level this method is run
     */
    public void nextLevel() {
        Level4 level4 = new Level4( stage, bgIndex, crosshairIndex);
        level4.startGame();
    }
}
