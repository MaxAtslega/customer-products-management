package dev.atslega.cpmf.component.workspace.list;

import dev.atslega.cpmf.AppStyles;
import dev.atslega.cpmf.component.PaginationBar;
import dev.atslega.cpmf.component.Title;
import dev.atslega.cpmf.workspace.WorkspacePattern;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.List;

public class WorkspaceListFramework extends BorderPane {

    private final int totalProducts;
    private final List<Pane> paneList;
    private int currentPage = 1;
    private int totalPages = 1;

    private final PaginationBar paginationBar;

    private final WorkspacePattern workspacePattern;

    public WorkspaceListFramework(WorkspacePattern workspacePattern, List<Pane> paneList, String title) {
        this.workspacePattern = workspacePattern;
        this.paneList = paneList;
        this.totalProducts = paneList.size();

        Title titel = new Title(title);
        titel.setPadding(new Insets(AppStyles.GAP_SIZE, 0, 0, AppStyles.GAP_SIZE));
        this.setTop(titel);

        this.setCenter(new WorkspaceListFlowPane(workspacePattern, this));

        paginationBar = new PaginationBar(currentPage);
        paginationBar.getBtnBack().setOnAction(e -> changePage(-1));
        paginationBar.getBtnNext().setOnAction(e -> changePage(1));
        this.setBottom(paginationBar);
    }


    private void changePage(int page) {
        currentPage = Math.max(1, Math.min(totalPages, currentPage + page));
        paginationBar.getLabelCurrentPage().setText("Page: " + currentPage);
        this.setCenter(new WorkspaceListFlowPane(workspacePattern, this));
    }

    public PaginationBar getPaginationBar() {
        return paginationBar;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public List<Pane> getPaneList() {
        return paneList;
    }
}


