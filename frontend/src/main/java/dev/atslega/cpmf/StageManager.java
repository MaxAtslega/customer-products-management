package dev.atslega.cpmf;

import dev.atslega.cpmf.controller.LoginController;
import dev.atslega.cpmf.workspace.WorkspacePattern;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class StageManager {
    private final Stage stage;

    public StageManager(Stage stage) {
        this.stage = stage;
    }

    public void setStageTitle(String title) {
        stage.setTitle(title);
    }

    public void setStageWidth(double width) {
        stage.setWidth(width);
    }

    public void setStageHeight(double height) {
        stage.setHeight(height);
    }

    public void setStageMinWidth(double width) {
        stage.setMinWidth(width);
    }

    public void setStageMinHeight(double height) {
        stage.setMinHeight(height);
    }

    public void setStageScene(Scene scene) {
        stage.setScene(scene);
    }

    public void setStageIcon(Image icon) {
        stage.getIcons().add(icon);
    }

    public void setStageResizable(boolean resizable) {
        stage.setResizable(resizable);
    }

    public void setStageCenter() {
        stage.centerOnScreen();
    }

    public void setStageMaximized() {
        stage.setMaximized(true);
    }


    public Scene getLoginScene() throws IOException {
        URL loginURL = this.getClass().getResource("login.fxml");
        FXMLLoader loader = new FXMLLoader(loginURL);

        LoginController loginController = new LoginController(this);
        loader.setController(loginController);

        return new Scene(loader.load(), AppStyles.WINDOW_WIDTH, AppStyles.WINDOW_HEIGHT);
    }

    public Scene getRegistrationScene() throws IOException {
        URL loginURL = getClass().getResource("registration.fxml");
        FXMLLoader loader = new FXMLLoader(loginURL);

        LoginController loginController = new LoginController(this);
        loader.setController(loginController);

        return new Scene(loader.load(), AppStyles.WINDOW_WIDTH, AppStyles.WINDOW_HEIGHT);
    }

    public Scene getWorkspaceScene() {
        return new Scene(new WorkspacePattern(this), AppStyles.WINDOW_WIDTH, AppStyles.WINDOW_HEIGHT);
    }
}


