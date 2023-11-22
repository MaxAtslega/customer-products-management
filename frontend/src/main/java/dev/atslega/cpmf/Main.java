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

public class Main extends Application {
    public Main() throws IOException {
    }
    //Main funktion
    public static void main(String[] args) {
       launch();
    }

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    public int screenWidth = screenSize.width;
    public int screenHeight = screenSize.height;
    //Objekt
    public static StageManager primaryStageManager,secondaryStageManager;
    public static Scene sceneLogin,sceneLoadScreen,sceneWorkspace;
    //Images in Main
    Image programmicon = new Image(getClass().getResource("Images/CPMicon.png").openStream());

    public static TempStorage tempStorage =new TempStorage();

    //start screen
    @Override
    public void start(Stage primaryStage) throws IOException {
        //Manager for the primaryStage
        primaryStageManager = new StageManager(primaryStage);
        // load scene
        sceneLogin = sceneLoginLoader();
        sceneLoadScreen = sceneLoadScreenLoader();
        sceneWorkspace = sceneWorkspaceLoader();
        //primaryStage settings
        primaryStageManager.setStageTitle("CPM Client");
        primaryStageManager.setStageIcon(programmicon);

        primaryStageManager.setStageCenter();
        //intro to login
        //primaryStageManager.setStageScene(sceneLogin);
        //primaryStageManager.setStageResizable(false);
        //temp
        primaryStageManager.setStageScene(sceneWorkspace);
        primaryStageManager.setStageMinHight(430);
        primaryStageManager.setStageMinWidth(650);
        // show the stage
        primaryStage.show();
    }

    public Scene sceneLoginLoader() throws IOException {
        Scene l;
        URL loginURL = getClass().getResource("login.fxml");
        FXMLLoader login = new FXMLLoader(loginURL);
        l= new Scene(login.load(),960,600);
        return l;
    }

    public Scene sceneLoadScreenLoader() throws IOException {
        Scene l;
        URL loadScreenURL = getClass().getResource("loadScreen.fxml");
        FXMLLoader load = new FXMLLoader(loadScreenURL);
        l = new Scene(load.load(), 253, 450);
        return l;
    }

    public Scene sceneWorkspaceLoader() {
        Scene l = new Scene(WorkspacePattern.workspace());
        return l;
    }
}
