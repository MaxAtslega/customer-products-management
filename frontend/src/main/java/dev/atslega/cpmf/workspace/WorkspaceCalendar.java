package dev.atslega.cpmf.workspace;

import dev.atslega.cpmf.AppStyles;
import dev.atslega.cpmf.component.Title;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class WorkspaceCalendar extends VBox {

    public WorkspaceCalendar() {
        setStyle("-fx-background-color: " + AppStyles.MAIN_BACKGROUND_COLOR);
        setPadding(new Insets(AppStyles.GAP_SIZE));

        getChildren().add(new Title("Calendar"));
    }
}