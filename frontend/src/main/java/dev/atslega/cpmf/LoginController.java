package dev.atslega.cpmf;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class LoginController {

    @FXML private ImageView eye, background;
    @FXML private PasswordField passwordField;
    @FXML private TextField passwordTextField, usernameTextField;
    @FXML private Label usernameFailure, passwordFailure;

    private final Image background0, eye1, eye2;
    private boolean isPasswordFieldVisible = true;

    public LoginController() throws IOException {
        background0 = loadImage("Images/Mountains960x600.png");
        eye1 = loadImage("Images/eyeOpen.png");
        eye2 = loadImage("Images/eyeClose.png");
    }

    private Image loadImage(String path) throws IOException {
        URL url = getClass().getResource(path);

        assert url != null;
        return new Image(url.openStream());
    }

    public void initialize() {
        background.setImage(background0);
        eye.setImage(eye1);
    }

    @FXML
    void toggleVisibility() {
        isPasswordFieldVisible = !isPasswordFieldVisible;
        if (isPasswordFieldVisible) {
            syncPasswordFields(passwordTextField, passwordField, eye1);
        } else {
            syncPasswordFields(passwordField, passwordTextField, eye2);
        }
    }

    private void syncPasswordFields(TextField from, TextField to, Image eyeImage) {
        to.setText(from.getText());
        from.setVisible(false);
        to.setVisible(true);
        eye.setImage(eyeImage);
    }

    @FXML
    void sendTo() {
        String username = usernameTextField.getText();
        String password = isPasswordFieldVisible ? passwordField.getText() : passwordTextField.getText();

        int failureCode = getFailure(username, password);
        handleFailure(failureCode);
    }

    private void handleFailure(int code) {
        switch (code) {
            case 0 -> changeScenes();
            case 1 -> setUsernameFailure("Enter a Username", true);
            case 2 -> setPasswordFailure("Enter a Password", true);
            case 3 -> setBothFailures();
            // other cases...
        }
    }

    private void changeScenes() {
        setUsernameFailure("", false);
        setPasswordFailure("", false);
        Main.primaryStageManager.setStageScene(Main.sceneLoadScreen);
        Main.primaryStageManager.setStageCenter();
    }

    private void setBothFailures() {
        setUsernameFailure("Enter a Username", true);
        setPasswordFailure("Enter a Password", true);
    }

    private void setPasswordFailure(String text, boolean visible) {
        passwordFailure.setText(text);
        passwordFailure.setVisible(visible);
    }

    private void setUsernameFailure(String text, boolean visible) {
        usernameFailure.setText(text);
        usernameFailure.setVisible(visible);
    }

    private int getFailure(String username, String password) {
        if (username.isEmpty()) return password.isEmpty() ? 3 : 1;
        return password.isEmpty() ? 2 : 0;
    }

}
