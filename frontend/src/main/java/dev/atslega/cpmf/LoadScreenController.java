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

public class LoadScreenController {
    @FXML
    private Label loadingText;
    @FXML
    private ImageView gearView;
    @FXML
    private ImageView background;
    public Image gear = new Image(getClass().getResource("Images/gear.png").openStream());
    public Image loadbackground = new Image(getClass().getResource("Images/loadbackground.png").openStream());
    public LoadScreenController() throws IOException {
    }

    public void initialize() throws InterruptedException {
        loadingText.setText("We are Working on it!");
        background.setImage(loadbackground);
        gearView.setImage(gear);
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(gearView);
        rotate.setDuration(Duration.millis(10000));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(360);
        rotate.play();

    }

    public void setWorkspace(){
        Main.primaryStageManager.setStageResizable(true);
        Main.primaryStageManager.setStageScene(Main.sceneWorkspace);
        Main.primaryStageManager.setStageCenter();
    }
}
