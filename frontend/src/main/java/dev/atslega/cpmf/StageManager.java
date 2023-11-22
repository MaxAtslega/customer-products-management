package dev.atslega.cpmf;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class StageManager {
    private Stage stage;
    //Contructor
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

    public void setStageMinWidth(double width){stage.setMinWidth(width);}

    public void setStageMinHight(double height) { stage.setMinHeight(height);}

    public void setStageScene(Scene scene){
        stage.setScene(scene);
    }

    public void setStageIcon(Image icon){
        stage.getIcons().add(icon);
    }

    public void setStageResizable(boolean resizeble){
        stage.setResizable(resizeble);
    }

    public void setStageCenter(){
        stage.centerOnScreen();
    }

    public void setStageMaximized(){
        stage.setMaximized(true);
    }
}


