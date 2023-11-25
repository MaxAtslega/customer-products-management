package dev.atslega.cpmf.workspace;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class WorkspaceHome extends VBox {

    public WorkspaceHome() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.getStyleClass().clear();
        scrollPane.pannableProperty().set(true);
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);

        VBox contentVBox = new VBox();
        contentVBox.setStyle("-fx-background-color: #26262B");
        contentVBox.setPadding(new Insets(10, 10, 10, 10));

        Text headerTitle = new Text("Home");
        headerTitle.setFont(Font.font("Roboto", 25));
        headerTitle.setStyle("-fx-font-weight: bold; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        headerTitle.setFill(Color.WHITE);

        FlowPane statsFlowPane = createStatsFlowPane();
        FlowPane informationFlowPane = createInformationFlowPane();

        Text userManagementTitle = new Text("Users");
        userManagementTitle.setFont(Font.font("Roboto", 20));
        userManagementTitle.setStyle("-fx-font-weight: bold; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        userManagementTitle.setFill(Color.WHITE);

        VBox userFlowPane = createUsersPane();

        contentVBox.getChildren().addAll(headerTitle, statsFlowPane, informationFlowPane, userManagementTitle, userFlowPane);
        scrollPane.setContent(contentVBox);

        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        getChildren().add(scrollPane);
    }


    private FlowPane createStatsFlowPane() {
        FlowPane statsFlowPane = new FlowPane();
        statsFlowPane.setHgap(10);
        statsFlowPane.setVgap(10);
        statsFlowPane.setPadding(new Insets(10, 0, 10, 0));

        // Add listener to dynamically adjust the width of each box
        statsFlowPane.widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            double width = newValue.doubleValue() / 3 - 10; // Divide by 3 and account for spacing
            width = Math.max(width, 200); // Ensure minimum width of 200
            for (var child : statsFlowPane.getChildren()) {
                ((VBox) child).setPrefWidth(width);
            }
        });

        // Create and add boxes to the statsFlowPane
        VBox customersBox = createStatsBox("Customers", "100", Color.valueOf("EA3D2F"));
        VBox productsBox = createStatsBox("Products", "800", Color.valueOf("367BF5"));
        VBox ordersBox = createStatsBox("Orders", "1.009", Color.valueOf("2FA84F"));


        statsFlowPane.getChildren().addAll(customersBox, productsBox, ordersBox);

        return statsFlowPane;
    }

    private FlowPane createInformationFlowPane() {
        FlowPane informationFlowPane = new FlowPane();
        informationFlowPane.setHgap(10);
        informationFlowPane.setVgap(10);
        informationFlowPane.setPadding(new Insets(10, 0, 10, 0));

        // Add listener to dynamically adjust the width of each box
        informationFlowPane.widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            double width = newValue.doubleValue() / 2 - 10; // Divide by 2 and account for spacing
            width = Math.max(width, 300); // Ensure minimum width of 200
            for (var child : informationFlowPane.getChildren()) {
                ((VBox) child).setPrefWidth(width);
            }
        });

        // Create and add boxes to the informationFlowPane
        VBox companyBox = createCompanyBox();
        VBox currentStatsBox = createCurrentStatsBox();

        informationFlowPane.getChildren().addAll(companyBox, currentStatsBox);

        return informationFlowPane;
    }

    private VBox createUsersPane() {
        VBox usersPane = new VBox();

        // Create and add boxes to the usersPane
        VBox user1 = createUserBox("1", "Lasse Hüls", "hüls@atslega.de");
        VBox user2 = createUserBox("2", "Murma Kleis", "kleis@atslega.de");
        VBox user3 = createUserBox("3", "Stefan Test", "test@atslega.de");
        VBox user4 = createUserBox("4", "Samuel Beis", "beis@atslega.de");


        usersPane.getChildren().addAll(user1, user2, user3, user4);

        return usersPane;
    }


    private VBox createStatsBox(String titleText, String numberText, Color color) {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(5);
        vbox.setStyle("-fx-background-color: #18191C; -fx-background-radius: 5;");

        Text titleLabel = new Text(titleText);
        titleLabel.setFont(Font.font("Quicksand", 18));
        titleLabel.setFill(Color.WHITE);

        Text numberLabel = new Text(numberText);
        numberLabel.setFont(Font.font("Roboto", FontWeight.BOLD, 40));
        numberLabel.setFill(color);

        vbox.getChildren().addAll(titleLabel, numberLabel);

        return vbox;
    }

    private VBox createCompanyBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(5);
        vbox.setStyle("-fx-background-color: #18191C; -fx-background-radius: 5;");

        Text titleLabel = new Text("Information");
        titleLabel.setFont(Font.font("Quicksand", FontWeight.BOLD, 18));
        titleLabel.setFill(Color.WHITE);

        Text nameLabel = new Text("Name: Max Atslega");
        nameLabel.setFont(Font.font("Roboto", 16));
        nameLabel.setFill(Color.WHITE);

        Text emailLabel = new Text("Email: max@atslega.de");
        emailLabel.setFont(Font.font("Roboto", 16));
        emailLabel.setFill(Color.WHITE);

        Text roleLabel = new Text("Role: Admin");
        roleLabel.setFont(Font.font("Roboto", 16));
        roleLabel.setFill(Color.WHITE);

        Text companyLabel = new Text("Company: Atslega Media GmbH");
        companyLabel.setFont(Font.font("Roboto", 16));
        companyLabel.setFill(Color.WHITE);

        vbox.getChildren().addAll(titleLabel, nameLabel, emailLabel, roleLabel, companyLabel);

        return vbox;
    }

    private VBox createCurrentStatsBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(5);
        vbox.setStyle("-fx-background-color: #18191C; -fx-background-radius: 5;");

        Text titleLabel = new Text("Stats");
        titleLabel.setFont(Font.font("Quicksand", FontWeight.BOLD, 18));
        titleLabel.setFill(Color.WHITE);

        Text nameLabel = new Text("Latest Product: Dyson Vacuum cleaner");
        nameLabel.setFont(Font.font("Roboto", 16));
        nameLabel.setFill(Color.WHITE);

        Text emailLabel = new Text("Last Customer: Lasse");
        emailLabel.setFont(Font.font("Roboto", 16));
        emailLabel.setFill(Color.WHITE);

        Text roleLabel = new Text("Last Order: Order #10 by Lasse");
        roleLabel.setFont(Font.font("Roboto", 16));
        roleLabel.setFill(Color.WHITE);

        Text companyLabel = new Text("User Count: 4");
        companyLabel.setFont(Font.font("Roboto", 16));
        companyLabel.setFill(Color.WHITE);

        vbox.getChildren().addAll(titleLabel, nameLabel, emailLabel, roleLabel, companyLabel);

        return vbox;
    }

    private VBox createUserBox(String id, String name, String email) {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(5);
        vBox.setStyle("-fx-background-color: #18191C; -fx-background-radius: 5;");
        VBox.setMargin(vBox, new Insets(10, 0, 0, 0));

        HBox hBox = new HBox();

        Text title = new Text(id + " - " + name + " - " + email);
        title.setFont(Font.font("Roboto", 16));
        title.setFill(Color.WHITE);

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Text titleLabel = new Text("Show");
        titleLabel.setFont(Font.font("Quicksand", FontWeight.BOLD, 16));
        titleLabel.setFill(Color.WHITE);

        hBox.getChildren().addAll(title, spacer, titleLabel);
        vBox.getChildren().add(hBox);

        return vBox;
    }
}
