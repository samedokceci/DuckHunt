 import javafx.animation.*;
 import javafx.scene.Cursor;
 import javafx.scene.Scene;
import javafx.scene.image.Image;
 import javafx.stage.Stage;
import javafx.util.Duration;
 import java.util.ArrayList;

 /**
  * Level 2 class
  */
 public class Level2 extends Level{
     /**
      * BlackDuck object
      */
    Duck blueDuck;

     /**
      * Level2 constructor
      * takes all the parameters which is same for all levels
      * @param stage
      * @param bgIndex
      * @param crosshairIndex
      */
    public Level2(Stage stage, int bgIndex, int crosshairIndex)  {
        //sets panes

        super(stage,bgIndex,crosshairIndex);

        blueDuck = new BlueDuck();

        //sets flying animation images
        blueDuck.flappingAnimationImages = new Image[]{blueDuck.image1,blueDuck.image2,blueDuck.image3};


        blueDuck.xVelocity = 15;
        blueDuck.yVelocity = -15;
        blueDuck.xPosition = 200;
        blueDuck.yPosition = 200;
        blueDuck.xScale = 1;
        blueDuck.yScale = 1;

        //bird initialisation
        blueDuck.duckIW.setX(blueDuck.xPosition*DuckHunt.SCALE);
        blueDuck.duckIW.setY(blueDuck.yPosition*DuckHunt.SCALE);


        blueDuck.flyingAnimation = new Timeline(new KeyFrame(Duration.millis(200), event -> blueDuck.flyingType2()));
        blueDuck.flyingAnimation.setCycleCount(Animation.INDEFINITE);

        ducks = new ArrayList<>();
        ducks.add(blueDuck);

        this.ammo = ducks.size()*3;
        ammoText.setText(String.format("Ammo Left: %d",ammo));

        levelText.setText("Level 2/6");


        //sets panes
        objects.getChildren().addAll(blueDuck.duckIW, fg, ammoText, levelText, enterToNextLevelText,enterToPlayAgainText, youWinText,escToExitText, gameOverText, crosshairIW);
        root.getChildren().addAll(bg, objects);

        scene = new Scene(root, DuckHunt.DEFAULT_WIDTH*DuckHunt.SCALE, DuckHunt.DEFAULT_HEIGHT*DuckHunt.SCALE);
        scene.setCursor(Cursor.NONE);

    }

     /**
      * To play next level this method is run
      */
    public void nextLevel() {
        Level3 level3 = new Level3( stage, bgIndex, crosshairIndex);
        level3.startGame();
    }
}
