package dev.atslega.cpmf.component;

import dev.atslega.cpmf.AppStyles;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class PaginationBar extends HBox {

    private final Button btnBack;
    private final Button btnNext;
    private final Label labelCurrentPage;

    public PaginationBar(int currentPage) {
        btnBack = new Button("<");
        btnBack.setCursor(Cursor.DEFAULT);
        btnBack.setStyle("-fx-background-color: " + AppStyles.SECONDARY_BACKGROUND_COLOR + "; -fx-background-radius: 5;");

        btnNext = new Button(">");
        btnNext.setCursor(Cursor.HAND);
        btnNext.setStyle("-fx-background-color: " + AppStyles.SECONDARY_BACKGROUND_COLOR + "; -fx-background-radius: 5;");

        labelCurrentPage = new Label("Page: " + currentPage);
        labelCurrentPage.setStyle("-fx-text-fill: white;");

        setStyle("-fx-background-color: " + AppStyles.MAIN_BACKGROUND_COLOR);
        setAlignment(Pos.CENTER);
        setPrefHeight(50);
        setPadding(new Insets(AppStyles.GAP_SIZE));
        setSpacing(AppStyles.GAP_SIZE);

        getChildren().addAll(btnBack, labelCurrentPage, btnNext);
    }

    public Button getBtnNext() {
        return btnNext;
    }

    public Button getBtnBack() {
        return btnBack;
    }

    public Label getLabelCurrentPage() {
        return labelCurrentPage;
    }
}
