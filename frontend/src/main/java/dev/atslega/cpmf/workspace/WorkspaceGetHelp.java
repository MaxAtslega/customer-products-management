package dev.atslega.cpmf.workspace;

import dev.atslega.cpmf.AppStyles;
import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.awt.*;
import java.net.URI;

public class WorkspaceGetHelp extends VBox {

    public WorkspaceGetHelp() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.getStyleClass().clear();
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);

        VBox contentVBox = new VBox(AppStyles.GAP_SIZE);
        contentVBox.setStyle("-fx-background-color: " + AppStyles.MAIN_BACKGROUND_COLOR);
        contentVBox.setPadding(new Insets(AppStyles.GAP_SIZE));

        // Title
        Label title = new Label("Get Help");
        title.setStyle("-fx-text-fill: " + AppStyles.DEFAULT_TEXT_COLOR + "; -fx-font-weight: bold; ");
        title.setFont(Font.font(AppStyles.FONT_FAMILY, AppStyles.TEXT_H1));

        Label subTitle = new Label("Need Assistance? We're Here to Help!");
        subTitle.setStyle("-fx-text-fill: " + AppStyles.DEFAULT_TEXT_COLOR + "; -fx-font-weight: bold; ");
        subTitle.setFont(Font.font(AppStyles.FONT_FAMILY, AppStyles.TEXT_H2));

        // Content text
        Label description = createLabel("If you're encountering challenges or have any questions about our services, our dedicated team is always ready to assist you. Here’s how you can get help:");

        // Hyperlinks and other information
        Hyperlink faqLink = new Hyperlink("Frequently Asked Questions (FAQs): Find answers on our FAQ page.");
        faqLink.setStyle("-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-text-fill: white; -fx-underline: true");
        faqLink.setFont(Font.font(AppStyles.FONT_FAMILY, AppStyles.TEXT_NORMAL));

        faqLink.setOnAction(e -> openFAQPage());

        Label userGuide = createLabel("UserData Guides and Tutorials: Explore our comprehensive guides and tutorials for step-by-step instructions.");

        Label supportForum = createLabel("Support Forum: Join the community in our forum. Share your experiences and get advice.");

        Label contactSupport = createLabel("Contact Customer Support: Need personalized assistance? Reach out to our support team through:\n" +
                "   Email: max@atslega.de\n" +
                "   Live Chat: Available on our website during business hours");

        Label socialMedia = createLabel("Social Media: Follow us on Twitter, Facebook, and Instagram for updates and tips.");

        // Adding all elements to the VBox
        contentVBox.getChildren().addAll(title, subTitle, description, faqLink, userGuide, supportForum, contactSupport, socialMedia);

        scrollPane.setContent(contentVBox);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        getChildren().add(scrollPane);
    }

    private void openFAQPage() {
        try {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI("https://github.com/MaxAtslega/customer-products-management"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Label createLabel(String text) {
        String labelStyle = "-fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-text-fill: " + AppStyles.DEFAULT_TEXT_COLOR;

        Label label = new Label(text);
        label.setStyle(labelStyle);
        label.setFont(Font.font(AppStyles.FONT_FAMILY, AppStyles.TEXT_NORMAL));
        label.setWrapText(true); // Enable text wrapping
        return label;
    }
}