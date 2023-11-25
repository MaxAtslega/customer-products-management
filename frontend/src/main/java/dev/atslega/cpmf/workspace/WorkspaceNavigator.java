package dev.atslega.cpmf.workspace;

import dev.atslega.cpmf.AppStyles;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;

import java.util.Objects;

import static dev.atslega.cpmf.workspace.WorkspacePattern.*;

public class WorkspaceNavigator extends VBox {

    private static final String BUTTON_STYLE = "-fx-font-size: 14; -fx-background-radius: 5px; -fx-font-weight: bold; -fx-background-color: "+AppStyles.MAIN_BACKGROUND_COLOR;
    private static final Insets BUTTON_PADDING = new Insets(AppStyles.GAP_SIZE, AppStyles.GAP_SIZE*2, AppStyles.GAP_SIZE, AppStyles.GAP_SIZE*2);
    private static final Insets BUTTON_MARGIN = new Insets(AppStyles.GAP_SIZE, AppStyles.GAP_SIZE, 0, AppStyles.GAP_SIZE);


    private Button lastClickedButton = null;
    private final int sidebarWidth;

    public WorkspaceNavigator(int sidebarWidth) {
        this.sidebarWidth = sidebarWidth;

        setStyle("-fx-background-color: " + AppStyles.SECONDARY_BACKGROUND_COLOR);
        initializeButtons();
    }

    private void initializeButtons() {
        int i = 0;
        for (Workspaces workspaceEnum : Workspaces.values()) {
            if (Objects.equals(workspaceEnum.getIcon(), "")) continue;

            Button button = createButton(workspaceEnum.getName(), workspaceEnum.getIcon(), workspaceEnum);

            if (i == 0){
                Label label = (Label) ((HBox) button.getGraphic()).getChildren().get(1);
                label.setTextFill(Paint.valueOf(AppStyles.PRIMARY_TEXT_COLOR));

                Region region = (Region) ((HBox) button.getGraphic()).getChildren().get(0);
                region.setStyle("-fx-background-color: "+AppStyles.PRIMARY_TEXT_COLOR+";");

                lastClickedButton = button;
            }

            getChildren().add(button);

            i++;
        }

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        // Logout Text
        Button settingsButton = createControlButton("Settings", "m370-80-16-128q-13-5-24.5-12T307-235l-119 50L78-375l103-78q-1-7-1-13.5v-27q0-6.5 1-13.5L78-585l110-190 119 50q11-8 23-15t24-12l16-128h220l16 128q13 5 24.5 12t22.5 15l119-50 110 190-103 78q1 7 1 13.5v27q0 6.5-2 13.5l103 78-110 190-118-50q-11 8-23 15t-24 12L590-80H370Zm70-80h79l14-106q31-8 57.5-23.5T639-327l99 41 39-68-86-65q5-14 7-29.5t2-31.5q0-16-2-31.5t-7-29.5l86-65-39-68-99 42q-22-23-48.5-38.5T533-694l-13-106h-79l-14 106q-31 8-57.5 23.5T321-633l-99-41-39 68 86 64q-5 15-7 30t-2 32q0 16 2 31t7 30l-86 65 39 68 99-42q22 23 48.5 38.5T427-266l13 106Zm42-180q58 0 99-41t41-99q0-58-41-99t-99-41q-59 0-99.5 41T342-480q0 58 40.5 99t99.5 41Zm-2-140Z");
        setupEventHandlers(settingsButton, Workspaces.SETTINGS);

        Button helpButton = createControlButton("Get Help", "M478-240q21 0 35.5-14.5T528-290q0-21-14.5-35.5T478-340q-21 0-35.5 14.5T428-290q0 21 14.5 35.5T478-240Zm-36-154h74q0-33 7.5-52t42.5-52q26-26 41-49.5t15-56.5q0-56-41-86t-97-30q-57 0-92.5 30T342-618l66 26q5-18 22.5-39t53.5-21q32 0 48 17.5t16 38.5q0 20-12 37.5T506-526q-44 39-54 59t-10 73Zm38 314q-83 0-156-31.5T197-197q-54-54-85.5-127T80-480q0-83 31.5-156T197-763q54-54 127-85.5T480-880q83 0 156 31.5T763-763q54 54 85.5 127T880-480q0 83-31.5 156T763-197q-54 54-127 85.5T480-80Zm0-80q134 0 227-93t93-227q0-134-93-227t-227-93q-134 0-227 93t-93 227q0 134 93 227t227 93Zm0-320Z");
        setupEventHandlers(helpButton, Workspaces.GET_HELP);

        Button logoutButton = createControlButton("Log out", "M200-120q-33 0-56.5-23.5T120-200v-560q0-33 23.5-56.5T200-840h280v80H200v560h280v80H200Zm440-160-55-58 102-102H360v-80h327L585-622l55-58 200 200-200 200Z");
        setupLogoutEventHandlers(logoutButton);

        getChildren().addAll(spacer, settingsButton, helpButton, logoutButton);
    }

    private Button createButton(String text, String icon, Workspaces workspace) {
        Label label = createLabel(text);
        Region svgPath = createSvgPath(icon);

        HBox hbox = new HBox((double) sidebarWidth / AppStyles.GAP_SIZE, svgPath, label);
        HBox.setHgrow(label, Priority.ALWAYS);
        hbox.setMaxWidth(Double.MAX_VALUE);
        hbox.setStyle("-fx-alignment: center-left;");

        Button button = new Button();
        button.setCursor(Cursor.HAND);
        button.setPadding(BUTTON_PADDING);
        button.setPrefWidth(sidebarWidth-AppStyles.GAP_SIZE*2);
        button.setGraphic(hbox);
        button.setStyle(BUTTON_STYLE);
        VBox.setMargin(button, BUTTON_MARGIN);

        setupEventHandlers(button, workspace);

        return button;
    }

    private Button createControlButton(String text, String icon) {
        Label label = createLabel(text);
        Region svgPath = createSvgPath(icon);

        HBox hbox = new HBox((double) sidebarWidth / AppStyles.GAP_SIZE, svgPath, label);
        HBox.setHgrow(label, Priority.ALWAYS);
        hbox.setMaxWidth(Double.MAX_VALUE);
        hbox.setStyle("-fx-alignment: center-left;");

        Button button = new Button();
        button.setCursor(Cursor.HAND);
        button.setPrefWidth(sidebarWidth-AppStyles.GAP_SIZE*2);
        button.setGraphic(hbox);
        button.setStyle("-fx-font-size: 11; -fx-font-weight: bold; -fx-background-color: transparent;");
        VBox.setMargin(button, new Insets(0, AppStyles.GAP_SIZE, AppStyles.GAP_SIZE, AppStyles.GAP_SIZE));

        return button;
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font(AppStyles.FONT_FAMILY, AppStyles.TEXT_H1));
        label.setStyle("-fx-font-weight: bold;");
        label.setTextFill(Paint.valueOf(AppStyles.INACTIVE_TEXT_COLOR));
        return label;
    }

    private Region createSvgPath(String icon) {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent(icon); // Set the SVG path dat

        final Region svgShape = new Region();
        svgShape.setShape(svgPath);
        svgShape.setMinSize(15, 15);
        svgShape.setPrefSize(15, 15);
        svgShape.setMaxSize(15, 15);
        svgShape.setStyle("-fx-background-color: "+AppStyles.INACTIVE_TEXT_COLOR+";");

        return svgShape;
    }

    private void setupEventHandlers(Button button, Workspaces workspace) {
        button.setOnMouseEntered(event -> updateTextColor(button, Color.valueOf(AppStyles.DEFAULT_TEXT_COLOR.replace("#", ""))));
        button.setOnMouseExited(event -> updateTextColor(button, Color.valueOf(AppStyles.INACTIVE_TEXT_COLOR.replace("#", ""))));
        button.setOnMouseClicked(event -> handleButtonClick(workspace, button));
    }

    private void setupLogoutEventHandlers(Button button) {
        button.setOnMouseEntered(event -> updateTextColor(button, Color.valueOf(AppStyles.DEFAULT_TEXT_COLOR.replace("#", ""))));
        button.setOnMouseExited(event -> updateTextColor(button, Color.valueOf(AppStyles.INACTIVE_TEXT_COLOR.replace("#", ""))));
        button.setOnMouseClicked(event -> {
            // TODO: Logout
            System.out.println("TODO: Logout");
        });
    }

    private void updateTextColor(Button button, Color color) {
        if (!button.equals(lastClickedButton)) {
            Label label = (Label) ((HBox) button.getGraphic()).getChildren().get(1);
            label.setTextFill(color);

            Region region = (Region) ((HBox) button.getGraphic()).getChildren().get(0);
            region.setStyle("-fx-background-color: "+color.toString().replace("0x", "#")+";");
        }
    }

    private void handleButtonClick(Workspaces workspaceEnum, Button clickedButton) {
        if (lastClickedButton != null && !lastClickedButton.equals(clickedButton)) {
            Label lastClickedButtonLabel = (Label) ((HBox) lastClickedButton.getGraphic()).getChildren().get(1);
            lastClickedButtonLabel.setTextFill(Color.valueOf(AppStyles.INACTIVE_TEXT_COLOR.replace("#", "")));

            Region region = (Region) ((HBox) lastClickedButton.getGraphic()).getChildren().get(0);
            region.setStyle("-fx-background-color: "+ AppStyles.INACTIVE_TEXT_COLOR+";");

            WorkspacePattern.setCenter(workspaceEnum);
        }
        lastClickedButton = clickedButton;

        Label clickedButtonLabel = (Label) ((HBox) lastClickedButton.getGraphic()).getChildren().get(1);
        clickedButtonLabel.setTextFill(Color.valueOf(AppStyles.PRIMARY_TEXT_COLOR.replace("#", "")));

        Region region = (Region) ((HBox) lastClickedButton.getGraphic()).getChildren().get(0);
        region.setStyle("-fx-background-color: "+ AppStyles.PRIMARY_TEXT_COLOR +";");
    }
}
