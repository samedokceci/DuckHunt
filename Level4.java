import javafx.animation.*;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Level4 extends Level{
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
     * Level4 constructor
     * takes all the parameters which is same for all levels
     * @param stage
     * @param bgIndex
     * @param crosshairIndex
     */
    public Level4(Stage stage, int bgIndex, int crosshairIndex)  {
        super(stage,bgIndex,crosshairIndex);

        blueDuck = new BlueDuck();


        blueDuck.flappingAnimationImages = new Image[]{blueDuck.image1,blueDuck.image2,blueDuck.image3};


        blueDuck.xVelocity = -12;
        blueDuck.yVelocity = -12;
        blueDuck.xPosition = 160;
        blueDuck.yPosition = 172;
        blueDuck.xScale = -1;
        blueDuck.yScale = 1;

        blueDuck.duckIW.setScaleX(blueDuck.xScale);
        blueDuck.duckIW.setScaleY(blueDuck.yScale);

        blueDuck.duckIW.setX(blueDuck.xPosition*DuckHunt.SCALE);
        blueDuck.duckIW.setY(blueDuck.yPosition*DuckHunt.SCALE);

        blueDuck.flyingAnimation = new Timeline(new KeyFrame(Duration.millis(150), event -> blueDuck.flyingType3(false)));
        blueDuck.flyingAnimation.setCycleCount(Animation.INDEFINITE);


        blackDuck = new BlackDuck();


        blackDuck.flappingAnimationImages = new Image[]{blackDuck.image1,blackDuck.image2,blackDuck.image3};


        blackDuck.xVelocity = 12;
        blackDuck.yVelocity = -12;
        blackDuck.xPosition = 94;
        blackDuck.yPosition = 172;
        blackDuck.xScale = 1;
        blackDuck.yScale = 1;

        blackDuck.duckIW.setScaleX(blackDuck.xScale);
        blackDuck.duckIW.setScaleY(blackDuck.yScale);

        blackDuck.duckIW.setX(blackDuck.xPosition*DuckHunt.SCALE);
        blackDuck.duckIW.setY(blackDuck.yPosition*DuckHunt.SCALE);



        blackDuck.flyingAnimation = new Timeline(new KeyFrame(Duration.millis(150), event -> blackDuck.flyingType3(true)));
        blackDuck.flyingAnimation.setCycleCount(Animation.INDEFINITE);

        //redduck 2
        redDuck = new RedDuck();
        redDuck.flappingAnimationImages = new Image[]{redDuck.image1, redDuck.image2, redDuck.image3};
        redDuck.xVelocity = -20;
        redDuck.yVelocity = -10;
        redDuck.xPosition = 50;
        redDuck.yPosition = 186;
        redDuck.xScale = -1;
        redDuck.duckIW.setScaleX(redDuck.xScale);


        redDuck.duckIW.setX(redDuck.xPosition*DuckHunt.SCALE);
        redDuck.duckIW.setY(redDuck.yPosition*DuckHunt.SCALE);


        redDuck.flyingAnimation = new Timeline(new KeyFrame(Duration.millis(150), event -> redDuck.flyingType2()));
        redDuck.flyingAnimation.setCycleCount(Timeline.INDEFINITE);

        ducks.add(redDuck);
        ducks.add(blackDuck);
        ducks.add(blueDuck);

        this.ammo = ducks.size() * 3;
        ammoText.setText(String.format("Ammo Left: %d",ammo));

        levelText.setText("Level 4/6");




        objects.getChildren().addAll(blueDuck.duckIW, blackDuck.duckIW,redDuck.duckIW, fg, ammoText, levelText, enterToNextLevelText,enterToPlayAgainText, youWinText,escToExitText, gameOverText,crosshairIW);
        root.getChildren().addAll(bg, objects);

        scene = new Scene(root, DuckHunt.DEFAULT_WIDTH*DuckHunt.SCALE, DuckHunt.DEFAULT_HEIGHT*DuckHunt.SCALE);
        scene.setCursor(Cursor.NONE);

    }

    /**
     * To play next level this method is run
     */
    public void nextLevel() {
        Level5 level5 = new Level5( stage, bgIndex, crosshairIndex);
        level5.startGame();
    }
}
