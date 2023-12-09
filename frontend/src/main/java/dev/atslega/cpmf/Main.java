package dev.atslega.cpmf;

import dev.atslega.cpmf.workspace.WorkspacePattern;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Main extends Application {

    public static StageManager primaryStageManager;
    public static Scene sceneLogin, sceneRegistration, sceneWorkspace;

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
        primaryStageManager = new StageManager(primaryStage);

        // Load different scenes for the application
        sceneLogin = sceneLoginLoader();
        sceneRegistration = sceneRegistrationLoader();
        sceneWorkspace = sceneWorkspaceLoader();

        // Configure primary stage properties
        primaryStageManager.setStageTitle("CPM Client");
        primaryStageManager.setStageIcon(new Image(Objects.requireNonNull(getClass().getResource("cpm.png")).openStream()));

        primaryStageManager.setStageCenter();
        primaryStageManager.setStageScene(sceneLogin);
        //primaryStageManager.setStageResizable(false);

        primaryStageManager.setStageMinHeight(AppStyles.WINDOW_HEIGHT);
        primaryStageManager.setStageMinWidth(AppStyles.WINDOW_WIDTH);

        // Display the primary stage to the user
        primaryStage.show();
    }

    public Scene sceneLoginLoader() throws IOException {
        URL loginURL = getClass().getResource("login.fxml");
        FXMLLoader login = new FXMLLoader(loginURL);
        return new Scene(login.load(), AppStyles.WINDOW_WIDTH, AppStyles.WINDOW_HEIGHT);
    }

    public Scene sceneRegistrationLoader() throws IOException {
        URL loginURL = getClass().getResource("registration.fxml");
        FXMLLoader login = new FXMLLoader(loginURL);
        return new Scene(login.load(), AppStyles.WINDOW_WIDTH, AppStyles.WINDOW_HEIGHT);
    }

    public Scene sceneWorkspaceLoader() {
        return new Scene(WorkspacePattern.workspace(), AppStyles.WINDOW_WIDTH, AppStyles.WINDOW_HEIGHT);
    }
}
