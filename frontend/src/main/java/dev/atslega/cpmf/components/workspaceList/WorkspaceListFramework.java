package dev.atslega.cpmf.components.workspaceList;

import dev.atslega.cpmf.AppStyles;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;

public class WorkspaceListFramework extends BorderPane {

    private final int totalProducts;
    private final List<Pane> paneList;
    private Label lblCurrentPage;
    private Button btnNext;
    private Button btnBack;
    private int currentPage = 1;
    private int totalPages = 1;


    public WorkspaceListFramework(List<Pane> paneList, String title) {
        this.paneList = paneList;
        this.totalProducts = paneList.size();

        this.setTop(createTitle(title));
        this.setCenter(new WorkspaceListFlowPane(this));
        this.setBottom(createPaginationBar());
    }


    private void changePage(int delta) {
        currentPage = Math.max(1, Math.min(totalPages, currentPage + delta));
        lblCurrentPage.setText("Page: " + currentPage);
        this.setCenter(new WorkspaceListFlowPane(this));
    }

    private HBox createTitle(String title) {
        Text headerTitle = new Text(title);
        headerTitle.setFont(Font.font(AppStyles.FONT_FAMILY, AppStyles.TEXT_H1));
        headerTitle.setStyle("-fx-font-weight: bold; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        headerTitle.setFill(Color.WHITE);

        HBox titleBar = new HBox(headerTitle);
        titleBar.setStyle("-fx-background-color: " + AppStyles.MAIN_BACKGROUND_COLOR);
        titleBar.setPadding(new Insets(AppStyles.GAP_SIZE, 0, 0, AppStyles.GAP_SIZE));
        titleBar.setMaxHeight(40);
        titleBar.setPrefHeight(40);
        return titleBar;
    }

    private HBox createPaginationBar() {
        btnBack = new Button("<");
        btnBack.setCursor(Cursor.DEFAULT);
        btnBack.setOnAction(e -> changePage(-1));
        btnBack.setStyle("-fx-background-color: " + AppStyles.SECONDARY_BACKGROUND_COLOR + "; -fx-background-radius: 5;");

        btnNext = new Button(">");
        btnNext.setCursor(Cursor.HAND);
        btnNext.setOnAction(e -> changePage(1));
        btnNext.setStyle("-fx-background-color: " + AppStyles.SECONDARY_BACKGROUND_COLOR + "; -fx-background-radius: 5;");

        lblCurrentPage = new Label("Page: " + currentPage);
        lblCurrentPage.setStyle("-fx-text-fill: white;"); // Set label text color to white

        HBox paginationBar = new HBox(AppStyles.GAP_SIZE, btnBack, lblCurrentPage, btnNext);
        paginationBar.setStyle("-fx-background-color: " + AppStyles.MAIN_BACKGROUND_COLOR);
        paginationBar.setAlignment(Pos.CENTER); // Center the contents of the HBox
        paginationBar.setPrefHeight(50);
        paginationBar.setPadding(new Insets(AppStyles.GAP_SIZE));

        return paginationBar;
    }


    public int getCurrentPage() {
        return currentPage;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public Button getBtnBack() {
        return btnBack;
    }

    public Button getBtnNext() {
        return btnNext;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public List<Pane> getPaneList() {
        return paneList;
    }
}


