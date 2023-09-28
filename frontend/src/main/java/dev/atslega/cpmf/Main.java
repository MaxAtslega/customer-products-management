package dev.atslega.cpmf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;


public class Main extends Application {
    public Main() throws IOException {
    }

    public static void main(String[] args) {
        launch();
    }
    Image icon = new Image(Objects.requireNonNull(getClass().getResource("Images/CPMicon.png")).openStream());
    @Override
    public void start(Stage primaryStage) throws IOException {

        URL loadURL = getClass().getResource("loadScreen.fxml");
        FXMLLoader load = new FXMLLoader(loadURL);
        URL loginURL = getClass().getResource("login.fxml");
        FXMLLoader login = new FXMLLoader(loginURL);
        Scene sceneLogin = new Scene(login.load(),960,600);
        Scene sceneLoad = new Scene(load.load(), 888, 500);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("CPM Client");
        primaryStage.setScene(sceneLoad);
        primaryStage.show();

    }


}
