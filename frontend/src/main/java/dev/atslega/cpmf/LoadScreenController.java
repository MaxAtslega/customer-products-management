package dev.atslega.cpmf;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class LoadScreenController {

    @FXML
    private Label loadingText;

    @FXML
    private ImageView gearView, background;

    private final Image gear;
    private final Image loadBackground;

    public LoadScreenController() throws IOException {
        gear = loadImage("Images/gear.png");
        loadBackground = loadImage("Images/loadbackground.png");
    }

    private Image loadImage(String path) throws IOException {
        return new Image(Objects.requireNonNull(getClass().getResource(path)).openStream());
    }

    public void initialize() {
        loadingText.setText("We are Working on it!");

        setupBackground();
        setupGearAnimation();
    }

    private void setupBackground() {
        background.setImage(loadBackground);
        gearView.setImage(gear);
    }

    private void setupGearAnimation() {
        RotateTransition rotate = createRotateTransition(gearView, Duration.millis(10000), 360);
        rotate.play();
    }

    private RotateTransition createRotateTransition(ImageView imageView, Duration duration, double angle) {
        RotateTransition rotate = new RotateTransition(duration, imageView);
        rotate.setCycleCount(RotateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(angle);
        return rotate;
    }

    public void setWorkspace() {
        Main.primaryStageManager.setStageResizable(true);
        Main.primaryStageManager.setStageScene(Main.sceneWorkspace);
        Main.primaryStageManager.setStageCenter();
    }
}
