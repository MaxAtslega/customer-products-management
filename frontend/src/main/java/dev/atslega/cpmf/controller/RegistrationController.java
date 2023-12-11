package dev.atslega.cpmf.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Executors;

public class RegistrationController {

    private final Image background0;
    @FXML
    private GridPane rootPane;
    @FXML
    private ImageView background;

    private Region eyeSvgShape;

    @FXML
    private Button eye;

    @FXML
    private PasswordField passwordField;

    @FXML
    private HBox passwordFieldContainer;

    @FXML
    private TextField passwordTextField, emailTextField;

    @FXML
    private Label emailFailure, passwordFailure;

    @FXML
    private TextField lastNameTextField, firstNameTextField, companyNameTextField, companyAddressTextField;
    @FXML
    private Label lastNameFailure, firstNameFailure, companyNameFailure, companyAddressFailure, registrationFailure;

    private boolean isPasswordFieldVisible = true;

    private StageManager stageManager;

    public RegistrationController(StageManager stageManager) throws IOException {
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

    private Image loadImage() throws IOException {
        URL url = getClass().getResource("Images/Mountains960x600.png");

        assert url != null;
        return new Image(url.openStream());
    }

    @FXML
    public void initialize() {
        background.fitWidthProperty().bind(rootPane.widthProperty());
        background.fitHeightProperty().bind(rootPane.heightProperty());

        background.setImage(background0);

        SVGPath eyeSvgPath = getOpenEyeSvg();

        passwordFieldContainer.setPrefHeight(24);
        passwordFieldContainer.setMaxHeight(24);

        passwordTextField.setManaged(false);

        eyeSvgShape = new Region();
        eyeSvgShape.setShape(eyeSvgPath);
        eyeSvgShape.setMinSize(20, 15);
        eyeSvgShape.setPrefSize(20, 15);
        eyeSvgShape.setMaxSize(20, 15);
        eyeSvgShape.setStyle("-fx-background-color: " + AppStyles.DEFAULT_TEXT_COLOR + ";");

        StackPane stackPane = new StackPane(eyeSvgShape);
        stackPane.setMinSize(20, 15);
        stackPane.setPrefSize(20, 15);
        stackPane.setMaxSize(20, 15);

        eye.setCursor(Cursor.HAND);
        eye.setPrefHeight(30);
        eye.setMaxHeight(30);
        eye.setStyle("-fx-font-weight: bold; -fx-background-color: transparent; -fx-border-color: #ffffff; -fx-border-width: 1px 1px 1px 0px;");
        eye.setGraphic(stackPane);

        eye.setOnMouseClicked(event -> toggleVisibility());

    }

    @FXML
    void toggleVisibility() {
        isPasswordFieldVisible = !isPasswordFieldVisible;
        if (isPasswordFieldVisible) {
            syncPasswordFields(passwordTextField, passwordField, getOpenEyeSvg());
        } else {
            syncPasswordFields(passwordField, passwordTextField, getCloseEyeSvg());
        }
    }

    private void syncPasswordFields(TextField from, TextField to, SVGPath svgPath) {
        to.setText(from.getText());
        from.setVisible(false);
        from.setManaged(false);

        to.setVisible(true);
        to.setManaged(true);
        eyeSvgShape.setShape(svgPath);
    }

    @FXML
    void login() throws IOException {
        stageManager.setStageScene(stageManager.getLoginScene());
    }

    @FXML
    void registration() {
        String lastName = lastNameTextField.getText();
        String firstName = firstNameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordField.getText();
        String companyName = companyNameTextField.getText();
        String companyAddress = companyAddressTextField.getText();

        // Add more complex validation logic as needed
        boolean isLastNameValid = !lastName.isEmpty();
        boolean isFirstNameValid = !firstName.isEmpty();
        boolean isEmailValid = !email.isEmpty();
        boolean isPasswordValid = !password.isEmpty();
        boolean isCompanyNameValid = !companyName.isEmpty();
        boolean isCompanyAddressValid = !companyAddress.isEmpty();

        setFailureLabel(lastNameFailure, !isLastNameValid, "Invalid last name");
        setFailureLabel(firstNameFailure, !isFirstNameValid, "Invalid first name");
        setFailureLabel(emailFailure, !isEmailValid, "Invalid email");
        setFailureLabel(passwordFailure, !isPasswordValid, "Invalid password");
        setFailureLabel(companyNameFailure, !isCompanyNameValid, "Invalid company name");
        setFailureLabel(companyAddressFailure, !isCompanyAddressValid, "Invalid company address");

        if (isLastNameValid && isFirstNameValid && isEmailValid && isPasswordValid && isCompanyNameValid && isCompanyAddressValid) {
            Executors.newSingleThreadExecutor().execute(() -> sendRegistrationRequest(lastName, firstName, email, password, companyName, companyAddress));
        }
    }

    private void sendRegistrationRequest(String lastName, String firstName, String email, String password, String companyName, String companyAddress) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/v1/auth/register"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(
                            "{\"lastName\":\"" + lastName + "\", \"firstName\":\"" + firstName + "\", \"email\":\"" + email + "\", \"password\":\"" + password + "\", \"company_name\":\"" + companyName + "\", \"company_address\":\"" + companyAddress + "\"}"
                    ))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 201) {
                javafx.application.Platform.runLater(() -> {
                    try {
                        stageManager.setStageScene(stageManager.getLoginScene());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            } else {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(response.body());
                // if node is null, then the response body is empty
                if (node == null || node.isEmpty() || node.get("message") == null) {
                    javafx.application.Platform.runLater(() -> {
                        registrationFailure.setText("Registration failed. Please try later again.");
                        registrationFailure.setVisible(true);
                    });
                    return;
                }

                String errorMessage = node.get("message").asText();

                javafx.application.Platform.runLater(() -> {
                    registrationFailure.setText(errorMessage);
                    registrationFailure.setVisible(true);
                });
            }
        } catch (IOException | InterruptedException e) {
            javafx.application.Platform.runLater(() -> {
                registrationFailure.setText("Registration failed. Please try later again.");
                registrationFailure.setVisible(true);
            });
        }
    }

    private void setFailureLabel(Label label, boolean visible, String text) {
        label.setText(text);
        label.setVisible(visible);
    }
}
