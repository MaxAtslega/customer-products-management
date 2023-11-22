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
    @FXML
    private ImageView eye;
    @FXML
    private ImageView background;

    private final URL backgroundURL = getClass().getResource("Images/Mountains960x600.png");
    private final URL eye1URL = getClass().getResource("Images/eyeOpen.png");
    private final URL eye2URL = getClass().getResource("Images/eyeClose.png");
    private final Image background0 = new Image(backgroundURL.openStream());
    private final Image eye1 = new Image(eye1URL.openStream());
    private final Image eye2 = new Image(Objects.requireNonNull(eye2URL).openStream());

    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField passwordTextField;
    private boolean isPasswordFieldVisible = true;

    public LoginController() throws IOException {
    }

    public void initialize() {
        background.setImage(background0);
        eye.setImage(eye1);
    }

    @FXML
    void toggleVisibility() {
        if(isPasswordFieldVisible){
            String a = passwordField.getText();
            passwordField.setVisible(false);
            passwordTextField.setVisible(true);
            passwordTextField.setText(a);
            eye.setImage(eye2);
            isPasswordFieldVisible = !isPasswordFieldVisible;
        }else{
            String a = passwordTextField.getText();
            passwordTextField.setVisible(false);
            passwordField.setVisible(true);
            passwordField.setText(a);
            eye.setImage(eye1);
            isPasswordFieldVisible = !isPasswordFieldVisible;
        }
    }


    @FXML
    private TextField usernameTextField;
    @FXML
    private Label usernameFailure,passwordFailure;
    @FXML
    private void setPasswordFailure(String text, boolean visible){
        passwordFailure.setText(text);
        passwordFailure.setVisible(visible);
    }
    @FXML
    private void setUsernameFailure(String text, boolean visible){
        usernameFailure.setText(text);
        usernameFailure.setVisible(visible);
    }
    @FXML
    void sendTo(){
        String username,password;
        username = usernameTextField.getText();
        if(isPasswordFieldVisible){
            password= passwordField.getText();
        }else{
            password = passwordTextField.getText();
        }
        switch (getFailure(username, password)) {
            case 0 -> {
                //
                setUsernameFailure("", false);
                setPasswordFailure("", false);
                System.out.println(username + "\n" + password);
                Main.primaryStageManager.setStageScene(Main.sceneLoadScreen);
                Main.primaryStageManager.setStageCenter();

            }
            case 1 -> {
                setUsernameFailure("Enter a Username", true);
                setPasswordFailure("", false);
            }
            case 2 -> {
                setUsernameFailure("", false);
                setPasswordFailure("Enter a Password", true);
            }
            case 3 -> {
                setUsernameFailure("Enter a Username", true);
                setPasswordFailure("Enter a Password", true);
            }
        }
    }
    private int getFailure(String username,String password){
        if(username.compareTo("")==0 && !(password.compareTo("")==0)){
            return 1;
        }else if(!(username.compareTo("")==0) && password.compareTo("")==0) {
            return 2;
        }else if(username.compareTo("")==0 && password.compareTo("")==0) {
            return 3;
        }else if(getFailureInStringUsername(username)){
            return 4;
        }else{
            return 0;
        }
    }
    private boolean getFailureInStringUsername (String username){
        //unexpected char at Username like [ , ; @ . ! " § $ % & / ( ) = ? { } - _ > < | ^ ´ ` ° ]
        return false;
    }


}
