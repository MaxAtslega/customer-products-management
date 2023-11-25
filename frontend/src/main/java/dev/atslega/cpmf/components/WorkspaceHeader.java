package dev.atslega.cpmf.components;

import dev.atslega.cpmf.workspace.WorkspacePattern;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static dev.atslega.cpmf.workspace.WorkspacePattern.DEFAULT_TEXT_COLOR;
import static dev.atslega.cpmf.workspace.WorkspacePattern.HOOVER_TEXT_COLOR;

public class WorkspaceHeader extends VBox {

    public WorkspaceHeader(String username, int sidebarWidth) {
        setPadding(new Insets(0, 10, 0, 0));
        setStyle("-fx-background-color: #18191C;");

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setSpacing(10); // Spacing between elements

        // Title
        Text title = new Text("CPM");
        title.setFont(Font.font("Roboto", 20));
        title.setStyle("-fx-font-weight: bold");
        title.setFill(Color.WHITE);

        // Title HBox
        HBox hboxTitle = new HBox(title);
        hboxTitle.setPadding(new Insets(10, 10, 10, 10));
        hboxTitle.setAlignment(Pos.CENTER_LEFT);
        hboxTitle.setMinWidth(sidebarWidth); // Minimum width for the title
        hboxTitle.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 1, 0, 0))));


        // Close button
        SVGPath closeButtonSvgPath = new SVGPath();
        closeButtonSvgPath.setContent("M120-240v-80h520v80H120Zm664-40L584-480l200-200 56 56-144 144 144 144-56 56ZM120-440v-80h400v80H120Zm0-200v-80h520v80H120Z");

        final Region closeButtonSvgShape = new Region();
        closeButtonSvgShape.setShape(closeButtonSvgPath);
        closeButtonSvgShape.setMinSize(20, 15);
        closeButtonSvgShape.setPrefSize(20, 15);
        closeButtonSvgShape.setMaxSize(20, 15);
        closeButtonSvgShape.setStyle("-fx-background-color: #5E6673;");

        Button closeButton = new Button();
        closeButton.setCursor(Cursor.HAND);
        closeButton.setGraphic(closeButtonSvgShape);
        closeButton.setStyle("-fx-font-size: 11; -fx-font-weight: bold; -fx-background-color: transparent;");

        closeButton.setOnMouseClicked(event -> {
            if (WorkspacePattern.getBackPlate().getLeft() != null) {
                closeButtonSvgPath.setContent("M120-240v-80h720v80H120Zm0-200v-80h720v80H120Zm0-200v-80h720v80H120Z");
            } else {
                closeButtonSvgPath.setContent("M120-240v-80h520v80H120Zm664-40L584-480l200-200 56 56-144 144 144 144-56 56ZM120-440v-80h400v80H120Zm0-200v-80h520v80H120Z");
            }
            WorkspacePattern.toggleNavigator();
        });

        closeButton.setOnMouseEntered(event -> updateTextColor(closeButton, HOOVER_TEXT_COLOR));
        closeButton.setOnMouseExited(event -> updateTextColor(closeButton, DEFAULT_TEXT_COLOR));

        // Spacer
        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Username text
        Text account = new Text(username);
        account.setFont(Font.font("Roboto", 14));
        account.setStyle("-fx-font-weight: bold");
        account.setFill(Color.WHITE);

        // Adding elements to the main HBox
        hbox.getChildren().addAll(hboxTitle, closeButton, spacer, account);

        // Add HBox to VBox
        getChildren().add(hbox);
    }

    private void updateTextColor(Button button, Color color) {
        Region region = (Region) button.getGraphic();
        region.setStyle("-fx-background-color: " + color.toString().replace("0x", "#") + ";");
    }
}
