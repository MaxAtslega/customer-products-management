package dev.atslega.cpmf.workspace;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.atslega.cpmf.AppStyles;
import dev.atslega.cpmf.component.Title;
import dev.atslega.cpmf.component.home.UsersListBox;
import dev.atslega.cpmf.model.Company;
import dev.atslega.cpmf.model.Order;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class WorkspaceHome extends VBox {

    private final WorkspacePattern workspacePattern;
    private final Company company;

    public WorkspaceHome(WorkspacePattern workspacePattern) {
        this.workspacePattern = workspacePattern;
        this.company = workspacePattern.getUserData().getCompany();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.getStyleClass().clear();
        scrollPane.pannableProperty().set(true);
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);

        VBox contentVBox = new VBox();
        contentVBox.setStyle("-fx-background-color: " + AppStyles.MAIN_BACKGROUND_COLOR);
        contentVBox.setPadding(new Insets(AppStyles.GAP_SIZE));

        FlowPane statsFlowPane = createStatsFlowPane();
        FlowPane informationFlowPane = createInformationFlowPane();

        Text userManagementTitle = new Text("Users");
        userManagementTitle.setFont(Font.font(AppStyles.FONT_FAMILY, AppStyles.TEXT_H2));
        userManagementTitle.setStyle("-fx-font-weight: bold; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        userManagementTitle.setFill(Color.WHITE);

        contentVBox.getChildren().addAll(new Title("Home"), statsFlowPane, informationFlowPane, userManagementTitle);

        if (Objects.equals(workspacePattern.getUserData().getRole(), "ADMIN")) {
            VBox userFlowPane = null;
            try {
                userFlowPane = new UsersListBox(workspacePattern);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            contentVBox.getChildren().add(userFlowPane);
        }

        scrollPane.setContent(contentVBox);

        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        getChildren().add(scrollPane);
    }


    private FlowPane createStatsFlowPane() {
        FlowPane statsFlowPane = new FlowPane();
        statsFlowPane.setHgap(AppStyles.GAP_SIZE);
        statsFlowPane.setVgap(AppStyles.GAP_SIZE);
        statsFlowPane.setPadding(new Insets(AppStyles.GAP_SIZE, 0, AppStyles.GAP_SIZE, 0));

        // Add listener to dynamically adjust the width of each box
        statsFlowPane.widthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            double width = newValue.doubleValue() / 3 - AppStyles.GAP_SIZE; // Divide by 3 and account for spacing
            width = Math.max(width, 200); // Ensure minimum width of 200
            for (var child : statsFlowPane.getChildren()) {
                ((VBox) child).setPrefWidth(width);
            }
        });

        // Create and add boxes to the statsFlowPane
        VBox customersBox = createStatsBox("Customers", company.getCustomerCount() + "", Color.valueOf("EA3D2F"));
        VBox productsBox = createStatsBox("Products", company.getProductCount() + "", Color.valueOf("367BF5"));
        VBox ordersBox = createStatsBox("Orders", company.getOrderCount() + "", Color.valueOf("2FA84F"));


        statsFlowPane.getChildren().addAll(customersBox, productsBox, ordersBox);

        return statsFlowPane;
    }

    private FlowPane createInformationFlowPane() {
        FlowPane informationFlowPane = new FlowPane();
        informationFlowPane.setHgap(AppStyles.GAP_SIZE);
        informationFlowPane.setVgap(AppStyles.GAP_SIZE);
        informationFlowPane.setPadding(new Insets(AppStyles.GAP_SIZE, 0, AppStyles.GAP_SIZE, 0));

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


    private VBox createStatsBox(String titleText, String numberText, Color color) {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(AppStyles.GAP_SIZE));
        vbox.setSpacing(5);
        vbox.setStyle("-fx-background-color: " + AppStyles.SECONDARY_BACKGROUND_COLOR + "; -fx-background-radius: 5;");

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
        vbox.setPadding(new Insets(AppStyles.GAP_SIZE));
        vbox.setSpacing(5);
        vbox.setStyle("-fx-background-color: " + AppStyles.SECONDARY_BACKGROUND_COLOR + "; -fx-background-radius: 5;");

        Text titleLabel = new Text("Information");
        titleLabel.setFont(Font.font("Quicksand", FontWeight.BOLD, 18));
        titleLabel.setFill(Color.WHITE);

        Text nameLabel = new Text("Name: " + workspacePattern.getUserData().getLastName() + ", "
                + workspacePattern.getUserData().getFirstName());
        nameLabel.setFont(Font.font("Roboto", AppStyles.TEXT_NORMAL));
        nameLabel.setFill(Color.WHITE);

        Text emailLabel = new Text("Email: " + workspacePattern.getUserData().getEmail());
        emailLabel.setFont(Font.font("Roboto", AppStyles.TEXT_NORMAL));
        emailLabel.setFill(Color.WHITE);

        Text roleLabel = new Text("Role: " + workspacePattern.getUserData().getRole());
        roleLabel.setFont(Font.font("Roboto", AppStyles.TEXT_NORMAL));
        roleLabel.setFill(Color.WHITE);

        Text companyLabel = new Text("Company: " + company.getCompanyName());
        companyLabel.setFont(Font.font("Roboto", AppStyles.TEXT_NORMAL));
        companyLabel.setFill(Color.WHITE);

        Text companyAddressLabel = new Text("Company Address: " + company.getCompanyAddress());
        companyAddressLabel.setFont(Font.font("Roboto", AppStyles.TEXT_NORMAL));
        companyAddressLabel.setFill(Color.WHITE);

        vbox.getChildren().addAll(titleLabel, nameLabel, emailLabel, roleLabel, companyLabel, companyAddressLabel);

        return vbox;
    }

    private VBox createCurrentStatsBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(AppStyles.GAP_SIZE));
        vbox.setSpacing(5);
        vbox.setStyle("-fx-background-color: " + AppStyles.SECONDARY_BACKGROUND_COLOR + "; -fx-background-radius: 5;");

        Text titleLabel = new Text("Stats");
        titleLabel.setFont(Font.font("Quicksand", FontWeight.BOLD, 18));
        titleLabel.setFill(Color.WHITE);

        Text nameLabel = new Text("Latest Product: " + (company.getLatestProduct() != null ?
                company.getLatestProduct().getProductName() : "null"));

        nameLabel.setFont(Font.font("Roboto", AppStyles.TEXT_NORMAL));
        nameLabel.setFill(Color.WHITE);

        Text emailLabel = new Text();
        if (company.getLatestCustomer() != null) {
            emailLabel.setText("Last Customer: " + company.getLatestCustomer().getFirstName() + " " + company.getLatestCustomer().getLastName());
        } else {
            emailLabel.setText("Last Customer: null");
        }

        emailLabel.setFont(Font.font("Roboto", AppStyles.TEXT_NORMAL));
        emailLabel.setFill(Color.WHITE);

        Text roleLabel = getRoleLabelText();
        roleLabel.setFont(Font.font("Roboto", AppStyles.TEXT_NORMAL));
        roleLabel.setFill(Color.WHITE);

        Text companyLabel = new Text("UserData Count: " + company.getUserCount());
        companyLabel.setFont(Font.font("Roboto", AppStyles.TEXT_NORMAL));
        companyLabel.setFill(Color.WHITE);

        vbox.getChildren().addAll(titleLabel, nameLabel, emailLabel, roleLabel, companyLabel);

        return vbox;
    }

    private Text getRoleLabelText() {
        Text roleLabel;
        Order latestOrder = company.getLatestOrder();

        if (latestOrder == null || latestOrder.getCustomer() == null) {
            roleLabel = new Text("Last Order: null");
        } else {
            String customerId = String.valueOf(latestOrder.getId());
            String customerFirstName = latestOrder.getCustomer().getFirstName();
            String customerLastName = latestOrder.getCustomer().getLastName();
            roleLabel = new Text("Last Order: Order #" + customerId + " by " + customerFirstName + " " + customerLastName);
        }
        return roleLabel;
    }
}
