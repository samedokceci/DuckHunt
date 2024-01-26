import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Line;
import javafx.util.Duration;
;import java.io.File;
import java.nio.file.Paths;


/**
 * Java abstract Duck class, which is ancestor of all duck types
 */
public abstract class Duck {

    /**
     * Images of all duck types
     */
    Image image1;
    Image image2;
    Image image3;
    Image image4;
    Image image5;
    Image image6;
    Image image7;
    Image image8;

    /**
     * Duck imageview object
     */
    ImageView duckIW;

    /**
     * Flying animation image array which provides wing flapping
     */
    Image[] flappingAnimationImages;

    /**
     * Timeline object for flying animation
     */
    Timeline flyingAnimation;

    /**
     * Integer for current using images index for flapping animation
     */
    int currentImageIndex = 0;

    /**
     * Current X position of bird
     */
    int xPosition;

    /**
     * Current Y position of bird
     */
    int yPosition;

    /**
     * Current X velocity of bird
     */
    int xVelocity;

    /**
     * Current Y velocity of bird
     */
    int yVelocity;

    /**
     * Current Scale value for x
     */
    int xScale = 1;

    /**
     * Current Scale value for y
     */
    int yScale = 1;

    /**
     * Bool value for if bird scaled in flyingType1
     */
    boolean scaled = false;

    /**
     * counter value for to count in flyingType3
     */
    int counter = 0;

    /**
     * boolean value for checking if bird alive is
     */
    boolean isAlive = true;


    public Duck() {
        duckIW = new ImageView();
    }

    /**
     * Creates a dying animation for every bird by taking coordinates of the bird, when it is shot
     * @param shotX
     * @param shotY
     */
    public void dyingAnimation(double shotX, double shotY) {
        flyingAnimation.stop();
        changeBirdImage(image7);

        //bird shocked animation
        Timeline shockedAnimation = new Timeline(new KeyFrame(Duration.seconds(0.13), event -> changeBirdImage(image8)));

        //bird falling animation
        Line line = new Line(shotX, shotY, shotX, DuckHunt.DEFAULT_HEIGHT* DuckHunt.SCALE);
        PathTransition pt = new PathTransition();
        pt.setPath(line);
        pt.setNode(duckIW);
        pt.setDuration(Duration.seconds(4));
        pt.setInterpolator(Interpolator.LINEAR);

        Media duckFallsAudioEffect = new Media(new File("assets/effects/DuckFalls.mp3").toURI().toString());
        MediaPlayer duckFallsAudioEffectPlayer = new MediaPlayer(duckFallsAudioEffect);
        duckFallsAudioEffectPlayer.setVolume(DuckHunt.VOLUME);
        duckFallsAudioEffectPlayer.play();
        //executing animations
        shockedAnimation.play();
        shockedAnimation.setOnFinished(e -> pt.play());
    }

    /**
     * Changes birds image by incrementing currentImageIndex variable by one. It is used in all animation types
     */
    public void changeBirdImage() {
        currentImageIndex = (currentImageIndex + 1) % flappingAnimationImages.length;
        Image imageToChange = flappingAnimationImages[currentImageIndex];
        duckIW.setFitHeight(imageToChange.getHeight()*DuckHunt.SCALE);
        duckIW.setFitWidth(imageToChange.getWidth()*DuckHunt.SCALE);
        duckIW.setImage(imageToChange);
    }

    /**
     * Override to providing image parameter
     * @param imageToChange
     */
    public void changeBirdImage(Image imageToChange) {
        duckIW.setFitHeight(imageToChange.getHeight()*DuckHunt.SCALE);
        duckIW.setFitWidth(imageToChange.getWidth()*DuckHunt.SCALE);
        duckIW.setImage(imageToChange);
    }


    /**
     * Horizontal flying animation for birds
     */
    public void flyingType1() {
        if (xPosition >= 220 && scaled == false) {
            scaled = true;
            xVelocity *= -1;
            xScale *= -1;
            duckIW.setScaleX(xScale);
        }
        else if (xPosition <= 6  && scaled == false) {
            scaled = true;
            xVelocity *= -1;
            xScale *= -1;
            duckIW.setScaleX(xScale);
        }
        else {
            scaled = false;
            changeBirdImage();
            xPosition += xVelocity;
            duckIW.setX(xPosition * DuckHunt.SCALE);
            duckIW.setY(yPosition * DuckHunt.SCALE);
        }
    }

    /**
     * Basic flying type which when a bird touches to the screen frame it gets reflected
     */
    public void flyingType2() {
        changeBirdImage();

        if (xPosition>220) {
            xVelocity *= -1;
            xScale *= -1;
            duckIW.setScaleX(xScale);
        }
        if (xPosition<8) {
            xVelocity *= -1;
            xScale *= -1;
            duckIW.setScaleX(xScale);
        }
        if (yPosition>220) {
            yVelocity *= -1;
            yScale *= -1;
            duckIW.setScaleY(yScale);

        }
        if (yPosition<8) {
            yVelocity *= -1;
            yScale *= -1;
            duckIW.setScaleY(yScale);
        }
        xPosition += xVelocity;
        yPosition += yVelocity;
        duckIW.setX(xPosition * DuckHunt.SCALE);
        duckIW.setY(yPosition * DuckHunt.SCALE);

    }

    /**
     * Specialized flying type which is used by level 4 birds, it uses base of flying type 2
     * @param toRight
     */
    public void flyingType3(boolean toRight) {

        changeBirdImage();

        if (xPosition>=240) {
            xVelocity *= -1;
            xScale *= -1;
            duckIW.setScaleX(xScale);
            if (counter==0 && toRight) {
                counter++;
                xPosition -= 30;
                duckIW.setX(xPosition * DuckHunt.SCALE);
            }
        }
        if (xPosition<=0) {
            xVelocity *= -1;
            xScale *= -1;
            duckIW.setScaleX(xScale);
            if (counter==0 && !toRight) {
                counter++;
                xPosition += 30;
                duckIW.setX(xPosition * DuckHunt.SCALE);
            }
        }
        if (yPosition>=240) {
            yVelocity *= -1;
            yScale *= -1;
            duckIW.setScaleY(yScale);

        }
        if (yPosition<=0) {
            yVelocity *= -1;
            yScale *= -1;
            duckIW.setScaleY(yScale);
        }
        xPosition += xVelocity;
        yPosition += yVelocity;
        duckIW.setX(xPosition * DuckHunt.SCALE);
        duckIW.setY(yPosition * DuckHunt.SCALE);
    }
}


