import javafx.animation.*;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;

/**
 * Level1 class
 */
public class Level1 extends Level{
    /**
     * BlackDuck object
     */
    Duck blackDuck;

    /**
     * Level1 constructor
     * takes all the parameters which is same for all levels
     * @param stage
     * @param bgIndex
     * @param crosshairIndex
     */
    public Level1(Stage stage, int bgIndex, int crosshairIndex) {
        //sets panes
        super(stage, bgIndex, crosshairIndex);

        blackDuck = new BlackDuck();

        //sets flying animation images
        blackDuck.flappingAnimationImages = new Image[]{blackDuck.image4, blackDuck.image5, blackDuck.image6};

        blackDuck.xVelocity = 20;
        blackDuck.yVelocity = 0;
        blackDuck.xPosition = 20;
        blackDuck.yPosition = 30;
        blackDuck.xScale = 1;
        blackDuck.yScale = 1;

        blackDuck.duckIW.setScaleX(blackDuck.xScale);
        blackDuck.duckIW.setScaleY(blackDuck.yScale);

        blackDuck.duckIW.setX(blackDuck.xPosition * DuckHunt.SCALE);
        blackDuck.duckIW.setY(blackDuck.yPosition * DuckHunt.SCALE);
        blackDuck.flyingAnimation = new Timeline(new KeyFrame(Duration.millis(333), event -> blackDuck.flyingType1()));
        blackDuck.flyingAnimation.setCycleCount(Timeline.INDEFINITE);

        ducks = new ArrayList<>();
        ducks.add(blackDuck);

        this.ammo = ducks.size()*3;
        ammoText.setText(String.format("Ammo Left: %d",ammo));

        levelText.setText("Level 1/6");

        objects.getChildren().addAll(blackDuck.duckIW, fg, ammoText, levelText, enterToNextLevelText, enterToPlayAgainText, youWinText, escToExitText, gameOverText, crosshairIW);
        root.getChildren().addAll(bg, objects);

        scene = new Scene(root, DuckHunt.DEFAULT_WIDTH * DuckHunt.SCALE, DuckHunt.DEFAULT_HEIGHT * DuckHunt.SCALE);
        scene.setCursor(Cursor.NONE);

    }

    /**
     * To play next level this method is run
     */
    public void nextLevel() {
        Level2 level2 = new Level2( stage, bgIndex, crosshairIndex);
        level2.startGame();
    }
}


