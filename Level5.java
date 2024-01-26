import javafx.animation.*;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;

public class Level5 extends Level{

    /**
     * Type1 BlackDuck object
     */
    Duck blackDuckType1;

    /**
     * Type2 BlackDuck object
     */
    Duck blackDuckType2;

    /**
     * RedDuck object
     */
    Duck redDuckType2;

    /**
     * BlueDuck object
     */
    Duck blueDuckType1;

    /**
     * Level5 constructor
     * takes all the parameters which is same for all levels
     * @param stage
     * @param bgIndex
     * @param crosshairIndex
     */
    public Level5(Stage stage, int bgIndex, int crosshairIndex)  {
        super(stage, bgIndex, crosshairIndex);

        //blackduck type 1
        blackDuckType1 = new BlackDuck();
        blackDuckType1.flappingAnimationImages = new Image[]{blackDuckType1.image4, blackDuckType1.image5, blackDuckType1.image6};
        blackDuckType1.xVelocity = 20;
        blackDuckType1.yVelocity = 0;
        blackDuckType1.xPosition = 20;
        blackDuckType1.yPosition = 35;
        blackDuckType1.flyingAnimation = new Timeline(new KeyFrame(Duration.millis(150), event ->blackDuckType1.flyingType1()));
        blackDuckType1.flyingAnimation.setCycleCount(Timeline.INDEFINITE);


        //blueduck type 1
        blueDuckType1 = new BlueDuck();
        blueDuckType1.flappingAnimationImages = new Image[]{blueDuckType1.image4, blueDuckType1.image5, blueDuckType1.image6};
        blueDuckType1.xVelocity = -20;
        blueDuckType1.yVelocity = 0;
        blueDuckType1.xPosition = 225;
        blueDuckType1.yPosition = 85;
        blueDuckType1.xScale = -1;
        blueDuckType1.scaled = true;
        blueDuckType1.duckIW.setScaleX(blueDuckType1.xScale);
        blueDuckType1.flyingAnimation = new Timeline(new KeyFrame(Duration.millis(150), event -> blueDuckType1.flyingType1()));
        blueDuckType1.flyingAnimation.setCycleCount(Timeline.INDEFINITE);


        //redduck type 2
        redDuckType2 = new RedDuck();
        redDuckType2.flappingAnimationImages = new Image[]{redDuckType2.image1, redDuckType2.image2, redDuckType2.image3};
        redDuckType2.xVelocity = 15;
        redDuckType2.yVelocity = -15;
        redDuckType2.xPosition = 200;
        redDuckType2.yPosition = 200;

        redDuckType2.duckIW.setX(redDuckType2.xPosition*DuckHunt.SCALE);
        redDuckType2.duckIW.setY(redDuckType2.yPosition*DuckHunt.SCALE);


        redDuckType2.flyingAnimation = new Timeline(new KeyFrame(Duration.millis(150), event ->redDuckType2.flyingType2()));
        redDuckType2.flyingAnimation.setCycleCount(Timeline.INDEFINITE);

        //blackduck type2
        blackDuckType2 = new BlackDuck();
        blackDuckType2.flappingAnimationImages = new Image[]{blackDuckType2.image1, blackDuckType2.image2, blackDuckType2.image3};
        blackDuckType2.xVelocity = -15;
        blackDuckType2.yVelocity = -15;
        blackDuckType2.xPosition = 40;
        blackDuckType2.yPosition = 150;
        blackDuckType2.xScale = -1;

        blackDuckType2.duckIW.setX(blackDuckType2.xPosition*DuckHunt.SCALE);
        blackDuckType2.duckIW.setY(blackDuckType2.yPosition*DuckHunt.SCALE);
        blackDuckType2.duckIW.setScaleX(blackDuckType2.xScale);

        blackDuckType2.flyingAnimation = new Timeline(new KeyFrame(Duration.millis(150), event ->blackDuckType2.flyingType2()));
        blackDuckType2.flyingAnimation.setCycleCount(Timeline.INDEFINITE);


        ducks = new ArrayList<>();
        ducks.add(blackDuckType1);
        ducks.add(blackDuckType2);
        ducks.add(redDuckType2);
        ducks.add(blueDuckType1);

        this.ammo = ducks.size() * 3;
        ammoText.setText(String.format("Ammo Left: %d",ammo));

        levelText.setText("Level 5/6");

        //sets panes
        objects.getChildren().addAll(blackDuckType1.duckIW, blueDuckType1.duckIW, redDuckType2.duckIW,blackDuckType2.duckIW , fg, ammoText, levelText, enterToNextLevelText,enterToPlayAgainText, youWinText,escToExitText, gameOverText, crosshairIW);
        root.getChildren().addAll(bg, objects);

        scene = new Scene(root, DuckHunt.DEFAULT_WIDTH*DuckHunt.SCALE, DuckHunt.DEFAULT_HEIGHT*DuckHunt.SCALE);

        scene.setCursor(Cursor.NONE);

    }

    /**
     * To play next level this method is run
     */
    public void nextLevel() {
        Level6 level6 = new Level6( stage, bgIndex, crosshairIndex);
        level6.startGame();
    }
}
