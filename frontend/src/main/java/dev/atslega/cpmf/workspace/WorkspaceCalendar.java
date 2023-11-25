package dev.atslega.cpmf.workspace;

import dev.atslega.cpmf.AppStyles;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class WorkspaceCalendar extends VBox {

    public WorkspaceCalendar() {
        setStyle("-fx-background-color: " + AppStyles.MAIN_BACKGROUND_COLOR);
        setPadding(new Insets(AppStyles.GAP_SIZE));

        Text headerTitle = new Text("Calendar");
        headerTitle.setFont(Font.font(AppStyles.FONT_FAMILY, AppStyles.TEXT_H1));
        headerTitle.setStyle("-fx-font-weight: bold; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        headerTitle.setFill(Color.WHITE);

        getChildren().add(headerTitle);
    }
}