package dev.atslega.cpmf.workspace;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class WorkspaceCalendar extends VBox {

    public WorkspaceCalendar () {
        setStyle("-fx-background-color: #26262B");
        setPadding(new Insets(10));

        Text headerTitle = new Text("Calendar");
        headerTitle.setFont(Font.font("Roboto", 25));
        headerTitle.setStyle("-fx-font-weight: bold; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        headerTitle.setFill(Color.WHITE);

        getChildren().add(headerTitle);
    }
}