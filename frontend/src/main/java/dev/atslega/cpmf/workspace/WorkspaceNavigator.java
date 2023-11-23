package dev.atslega.cpmf.workspace;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Objects;

public class WorkspaceNavigator extends VBox {

    private static final String BUTTON_STYLE = "-fx-font-size: 14; -fx-background-radius: 10px; -fx-font-weight: bold; -fx-background-color: #202225;";
    private static final String FONT_FAMILY = "Calibri";
    private static final double ICON_SIZE = 20;
    private static final Insets BUTTON_PADDING = new Insets(10, 20, 10, 20);
    private static final Insets BUTTON_MARGIN = new Insets(10, 10, 0, 10);
    private static final Color DEFAULT_TEXT_COLOR = Color.GREY;
    private static final Color ACTIVE_TEXT_COLOR = Color.WHITE;

    private Button lastClickedButton = null;

    public WorkspaceNavigator() {
        setStyle("-fx-background-color: #18191C");
        initializeButtons();
    }

    private void initializeButtons() {
        int i = 0;
        for (WorkspaceEnum workspaceEnum : WorkspaceEnum.values()) {
            Image workspaceIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/" + workspaceEnum.getIcon())));
            Button button = createButton(workspaceEnum.getName(), workspaceIcon, workspaceEnum);
            VBox.setMargin(button, BUTTON_MARGIN);

            if (i == 0){
                Label label = (Label) ((HBox) button.getGraphic()).getChildren().get(1);
                label.setTextFill(ACTIVE_TEXT_COLOR);

                lastClickedButton = button;
            }

            getChildren().add(button);

            i++;
        }
    }

    private Button createButton(String text, Image icon, WorkspaceEnum workspace) {
        Label label = createLabel(text);
        ImageView imageView = createImageView(icon);
        HBox hbox = new HBox(20, imageView, label);
        HBox.setHgrow(label, Priority.ALWAYS);
        hbox.setMaxWidth(Double.MAX_VALUE);
        hbox.setStyle("-fx-alignment: center-left;");

        Button button = new Button();
        button.setPadding(BUTTON_PADDING);
        button.setPrefWidth(185);
        button.setGraphic(hbox);
        button.setStyle(BUTTON_STYLE);
        setupEventHandlers(button, workspace);

        return button;
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font(FONT_FAMILY, 25));
        label.setStyle("-fx-font-weight: bold;");
        label.setTextFill(DEFAULT_TEXT_COLOR);
        return label;
    }

    private ImageView createImageView(Image icon) {
        ImageView imageView = new ImageView(icon);
        imageView.setFitHeight(ICON_SIZE);
        imageView.setFitWidth(ICON_SIZE);
        return imageView;
    }

    private void setupEventHandlers(Button button, WorkspaceEnum workspace) {
        button.setOnMouseEntered(event -> updateTextColor(button, ACTIVE_TEXT_COLOR));
        button.setOnMouseExited(event -> updateTextColor(button, DEFAULT_TEXT_COLOR));
        button.setOnMouseClicked(event -> handleButtonClick(workspace, button));
    }

    private void updateTextColor(Button button, Color color) {
        if (!button.equals(lastClickedButton)) {
            Label label = (Label) ((HBox) button.getGraphic()).getChildren().get(1);
            label.setTextFill(color);
        }
    }

    private void handleButtonClick(WorkspaceEnum workspaceEnum, Button clickedButton) {
        if (lastClickedButton != null && !lastClickedButton.equals(clickedButton)) {
            Label lastClickedButtonLabel = (Label) ((HBox) lastClickedButton.getGraphic()).getChildren().get(1);
            lastClickedButtonLabel.setTextFill(DEFAULT_TEXT_COLOR);

            WorkspacePattern.setCenter(workspaceEnum);
        }
        lastClickedButton = clickedButton;

        Label clickedButtonLabel = (Label) ((HBox) lastClickedButton.getGraphic()).getChildren().get(1);
        clickedButtonLabel.setTextFill(ACTIVE_TEXT_COLOR);
    }
}
