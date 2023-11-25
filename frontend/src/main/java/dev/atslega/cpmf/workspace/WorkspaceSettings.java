package dev.atslega.cpmf.workspace;

import dev.atslega.cpmf.AppStyles;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class WorkspaceSettings extends VBox {

    public WorkspaceSettings() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.getStyleClass().clear();
        scrollPane.pannableProperty().set(true);
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);

        VBox contentVBox = new VBox();
        contentVBox.setStyle("-fx-background-color: " + AppStyles.MAIN_BACKGROUND_COLOR);
        contentVBox.setPadding(new Insets(AppStyles.GAP_SIZE, AppStyles.GAP_SIZE, AppStyles.GAP_SIZE, AppStyles.GAP_SIZE));

        Text headerTitle = new Text("Settings");
        headerTitle.setFont(Font.font(AppStyles.FONT_FAMILY, AppStyles.TEXT_H1));
        headerTitle.setStyle("-fx-font-weight: bold; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        headerTitle.setFill(Color.WHITE);

        contentVBox.getChildren().add(headerTitle);

        scrollPane.setContent(contentVBox);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        getChildren().add(scrollPane);
    }
}