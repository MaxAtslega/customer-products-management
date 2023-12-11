package dev.atslega.cpmf;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    Toolkit toolkit;
    Dimension screenSize;

    public static void main(String[] args) {
        launch();
    }

    /*
     * Entry point for the JavaFX application: sets up and displays the primary stage */
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Initialize variables for screen size retrieval
        toolkit = Toolkit.getDefaultToolkit();
        screenSize = toolkit.getScreenSize();

        // Initialize the manager for the primaryStage to handle various stage operations
        StageManager primaryStageManager = new StageManager(primaryStage);

        // Configure primary stage properties
        primaryStageManager.setStageTitle("CPM Client");
        primaryStageManager.setStageIcon(new Image(Objects.requireNonNull(getClass().getResource("cpm.png")).openStream()));

        primaryStageManager.setStageCenter();
        primaryStageManager.setStageScene(primaryStageManager.getLoginScene());

        primaryStageManager.setStageMinHeight(AppStyles.WINDOW_HEIGHT);
        primaryStageManager.setStageMinWidth(AppStyles.WINDOW_WIDTH);

        // Display the primary stage to the user
        primaryStage.show();
    }
}
