package dev.atslega.cpmf.controller;

import dev.atslega.cpmf.AppStyles;
import dev.atslega.cpmf.StageManager;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.SVGPath;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Executors;

public class LoginController {

    private static final String ERROR_ENTER_USERNAME = "Enter a Username";
    private static final String ERROR_ENTER_PASSWORD = "Enter a Password";
    private final Image background0;

    @FXML
    private PasswordField passwordField;
    @FXML
    private GridPane rootPane;
    @FXML
    private ImageView background;
    @FXML
    private Button eye;

    private boolean isPasswordFieldVisible = true;
    @FXML
    private HBox passwordFieldContainer;
    @FXML
    private TextField passwordTextField, emailTextField;
    @FXML
    private Label emailFailure, passwordFailure;
    private Region eyeSvgShape;

    private StageManager stageManager;

    public LoginController(StageManager stageManager) {
        this.stageManager = stageManager;
        background0 = loadImage();
    }

    private static SVGPath getOpenEyeSvg() {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent("M480-320q75 0 127.5-52.5T660-500q0-75-52.5-127.5T480-680q-75 0-127.5 52.5T300-500q0 75 52.5 127.5T480-320Zm0-72q-45 0-76.5-31.5T372-500q0-45 31.5-76.5T480-608q45 0 76.5 31.5T588-500q0 45-31.5 76.5T480-392Zm0 192q-146 0-266-81.5T40-500q54-137 174-218.5T480-800q146 0 266 81.5T920-500q-54 137-174 218.5T480-200Zm0-300Zm0 220q113 0 207.5-59.5T832-500q-50-101-144.5-160.5T480-720q-113 0-207.5 59.5T128-500q50 101 144.5 160.5T480-280Z"); // Set the SVG path dat
        return svgPath;
    }

    private static SVGPath getCloseEyeSvg() {
        SVGPath svgPath = new SVGPath();
        svgPath.setContent("m644-428-58-58q9-47-27-88t-93-32l-58-58q17-8 34.5-12t37.5-4q75 0 127.5 52.5T660-500q0 20-4 37.5T644-428Zm128 126-58-56q38-29 67.5-63.5T832-500q-50-101-143.5-160.5T480-720q-29 0-57 4t-55 12l-62-62q41-17 84-25.5t90-8.5q151 0 269 83.5T920-500q-23 59-60.5 109.5T772-302Zm20 246L624-222q-35 11-70.5 16.5T480-200q-151 0-269-83.5T40-500q21-53 53-98.5t73-81.5L56-792l56-56 736 736-56 56ZM222-624q-29 26-53 57t-41 67q50 101 143.5 160.5T480-280q20 0 39-2.5t39-5.5l-36-38q-11 3-21 4.5t-21 1.5q-75 0-127.5-52.5T300-500q0-11 1.5-21t4.5-21l-84-82Zm319 93Zm-151 75Z"); // Set the SVG path dat
        return svgPath;
    }

    private Image loadImage() {
        URL url = getClass().getResource("Images/Mountains960x600.png");
        if (url == null) {
            return null;
        }
        try {
            return new Image(url.openStream());
        } catch (IOException e) {
            return null;
        }
    }

    @FXML
    public void initialize() {
        configureBackground();
        configurePasswordField();
        configureEyeButton();
    }

    private void configureBackground() {
        background.fitWidthProperty().bind(rootPane.widthProperty());
        background.fitHeightProperty().bind(rootPane.heightProperty());
        background.setImage(background0);
    }

    private void configurePasswordField() {
        passwordFieldContainer.setPrefHeight(24);
        passwordFieldContainer.setMaxHeight(24);
        passwordTextField.setManaged(false);
    }

    private void configureEyeButton() {
        SVGPath eyeSvgPath = getOpenEyeSvg();
        eyeSvgShape = createEyeSvgShape(eyeSvgPath);

        StackPane stackPane = new StackPane(eyeSvgShape);
        configureStackPane(stackPane);

        eye.setCursor(Cursor.HAND);
        configureEyeButtonStyle();
        eye.setGraphic(stackPane);
        eye.setOnMouseClicked(event -> toggleVisibility());
    }

    private void configureEyeButtonStyle() {
        eye.setPrefHeight(30);
        eye.setMaxHeight(30);
        eye.setStyle("-fx-font-weight: bold; -fx-background-color: transparent; -fx-border-color: #ffffff; -fx-border-width: 1px 1px 1px 0px;");
    }

    private void configureStackPane(StackPane stackPane) {
        stackPane.setMinSize(20, 15);
        stackPane.setPrefSize(20, 15);
        stackPane.setMaxSize(20, 15);
    }

    private Region createEyeSvgShape(SVGPath svgPath) {
        Region region = new Region();
        region.setShape(svgPath);
        region.setMinSize(20, 15);
        region.setPrefSize(20, 15);
        region.setMaxSize(20, 15);
        region.setStyle("-fx-background-color: " + AppStyles.DEFAULT_TEXT_COLOR + ";");
        return region;
    }

    @FXML
    private void toggleVisibility() {
        isPasswordFieldVisible = !isPasswordFieldVisible;
        syncPasswordFields();
    }

    private void syncPasswordFields() {
        TextField from = isPasswordFieldVisible ? passwordTextField : passwordField;
        TextField to = isPasswordFieldVisible ? passwordField : passwordTextField;
        SVGPath svgPath = isPasswordFieldVisible ? getOpenEyeSvg() : getCloseEyeSvg();

        to.setText(from.getText());
        from.setVisible(false);
        from.setManaged(false);

        to.setVisible(true);
        to.setManaged(true);
        eyeSvgShape.setShape(svgPath);
    }

    @FXML
    void login() {
        String email = emailTextField.getText();
        String password = isPasswordFieldVisible ? passwordField.getText() : passwordTextField.getText();

        boolean isEmailValid = !email.isEmpty();
        boolean isPasswordValid = !password.isEmpty();

        setFailureLabel(emailFailure, !isEmailValid, "Invalid email");
        setFailureLabel(passwordFailure, !isPasswordValid, "Invalid password");

        if (isEmailValid && isPasswordValid) {
            Executors.newSingleThreadExecutor().execute(() -> sendLoginRequest(email, password));
        }
    }

    private void sendLoginRequest(String email, String password) {
        stageManager.setStageScene(stageManager.getWorkspaceScene());
        stageManager.setStageCenter();
    }


    @FXML
    void registration() throws IOException {
        stageManager.setStageScene(stageManager.getRegistrationScene());
    }


    private void setFailureLabel(Label label, boolean visible, String text) {
        label.setText(text);
        label.setVisible(visible);
    }
}
