package dev.atslega.cpmf.components.workspaceList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.List;

public class WorkspaceListFramework extends BorderPane {

    private Label lblCurrentPage;
    private Button btnNext;
    private Button btnBack;

    private int currentPage = 1;
    private final int totalProducts;
    private int totalPages = 1;

    private final List<Pane> paneList;


    public WorkspaceListFramework(List<Pane> paneList) {
        this.paneList = paneList;
        this.totalProducts = paneList.size();

        this.setCenter(new WorkspaceListFlowPane(this));
        this.setBottom(createPaginationBar());
    }


    private void changePage(int delta) {
        currentPage = Math.max(1, Math.min(totalPages, currentPage + delta));
        lblCurrentPage.setText("Page: " + currentPage);
        this.setCenter(new WorkspaceListFlowPane(this));
    }

    private HBox createPaginationBar() {
        btnBack = new Button("<");
        btnBack.setCursor(Cursor.DEFAULT);
        btnBack.setOnAction(e -> changePage(-1));
        btnBack.setStyle("-fx-background-color: #18191C; -fx-background-radius: 5;");

        btnNext = new Button(">");
        btnNext.setCursor(Cursor.HAND);
        btnNext.setOnAction(e -> changePage(1));
        btnNext.setStyle("-fx-background-color: #18191C; -fx-background-radius: 5;");

        lblCurrentPage = new Label("Page: " + currentPage);
        lblCurrentPage.setStyle("-fx-text-fill: white;"); // Set label text color to white

        HBox paginationBar = new HBox(10, btnBack, lblCurrentPage, btnNext);
        paginationBar.setStyle("-fx-background-color: #26262B");
        paginationBar.setAlignment(Pos.CENTER); // Center the contents of the HBox
        paginationBar.setPrefHeight(50);
        paginationBar.setPadding(new Insets(10));

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


