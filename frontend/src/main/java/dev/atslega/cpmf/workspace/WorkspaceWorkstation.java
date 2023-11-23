package dev.atslega.cpmf.workspace;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

import java.awt.*;

public class WorkspaceWorkstation extends VBox {

    public WorkspaceWorkstation(String username) {
        setPadding(new Insets(10, 10, 10,10));
        setStyle("-fx-background-color: #18191C;");


        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);

        Text title = new Text("CPM");
        title.setTextOrigin(VPos.TOP);
        title.setBoundsType(TextBoundsType.VISUAL);

        title.setFont(Font.font("Calibri", 25));
        title.setStyle("-fx-font-weight: bold");
        title.setFill(Color.WHITE);

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Text account = new Text(username);
        account.setTextOrigin(VPos.TOP);
        account.setBoundsType(TextBoundsType.VISUAL);

        account.setFont(Font.font("Calibri", 16));
        account.setStyle("-fx-font-weight: bold");
        account.setFill(Color.WHITE);

        hbox.getChildren().addAll(title, spacer, account);
        HBox.setHgrow(title, Priority.ALWAYS);

        getChildren().addAll(hbox);
    }
}
