package dev.atslega.cpmf;

import dev.atslega.cpmf.workspace.WorkspacePattern;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.Dimension;
import java.awt.Toolkit;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Main extends Application {

    Toolkit toolkit;
    Dimension screenSize;
    
    public static StageManager primaryStageManager;
    public static Scene sceneLogin, sceneLoadScreen, sceneWorkspace;

    public static TempStorage tempStorage = new TempStorage();
    
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
        sceneLoadScreen = sceneLoadScreenLoader();
        sceneWorkspace = sceneWorkspaceLoader();

        // Configure primary stage properties
        primaryStageManager.setStageTitle("CPM Client");
        primaryStageManager.setStageIcon(new Image(Objects.requireNonNull(getClass().getResource("Images/CPMicon.png")).openStream()));

        primaryStageManager.setStageCenter();
        primaryStageManager.setStageScene(sceneWorkspace);
        //primaryStageManager.setStageResizable(false);

        primaryStageManager.setStageMinHeight(430);
        primaryStageManager.setStageMinWidth(650);

        // Display the primary stage to the user
        primaryStage.show();
    }

    public Scene sceneLoginLoader() throws IOException {
        URL loginURL = getClass().getResource("login.fxml");
        FXMLLoader login = new FXMLLoader(loginURL);
        return new Scene(login.load(),960,600);
    }

    public Scene sceneLoadScreenLoader() throws IOException {
        URL loadScreenURL = getClass().getResource("loadScreen.fxml");
        FXMLLoader load = new FXMLLoader(loadScreenURL);
        return new Scene(load.load(), 960, 600);
    }

    public Scene sceneWorkspaceLoader() {
        return new Scene(WorkspacePattern.workspace(), 960, 600);
    }
}
