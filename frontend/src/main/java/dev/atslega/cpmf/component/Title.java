package dev.atslega.cpmf.component;

import dev.atslega.cpmf.AppStyles;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Title extends HBox {

    public Title(String title) {
        Text headerTitle = new Text(title);
        headerTitle.setFont(Font.font(AppStyles.FONT_FAMILY, AppStyles.TEXT_H1));
        headerTitle.setStyle("-fx-font-weight: bold; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        headerTitle.setFill(Color.WHITE);

        setStyle("-fx-background-color: " + AppStyles.MAIN_BACKGROUND_COLOR);
        setMaxHeight(40);
        setPrefHeight(40);

        getChildren().add(headerTitle);
    }
}
