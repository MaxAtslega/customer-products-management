package dev.atslega.cpmf.components.workspaceList;

import dev.atslega.cpmf.Main;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.layout.FlowPane;

public class WorkspaceListFlowPane extends FlowPane {
    private static final int PRODUCT_SIZE = 200;
    private static final int GAP_SIZE = 10;

    private final WorkspaceListFramework workspaceListFramework;

    public WorkspaceListFlowPane(WorkspaceListFramework workspaceListFramework) {
        this.workspaceListFramework = workspaceListFramework;

        setPadding(new Insets(GAP_SIZE));
        setHgap(GAP_SIZE);
        setVgap(GAP_SIZE);
        setMaxHeight(Double.MAX_VALUE);

        setStyle("-fx-background-color: #26262B");

        widthProperty().addListener(e -> updateProducts());
        Main.sceneWorkspace.heightProperty().addListener(e -> updateProducts());
    }


    private void updateProducts() {
        int maxProductsPerPage = calculateMaxProducts(getWidth(), Main.sceneWorkspace.getHeight()-120);

        int currentPage = workspaceListFramework.getCurrentPage();
        int totalProducts = workspaceListFramework.getTotalProducts();

        int start = (currentPage - 1) * maxProductsPerPage;
        int end = Math.min(start + maxProductsPerPage, totalProducts);

        getChildren().clear();
        for (int i = start; i < end; i++) {
            getChildren().add(workspaceListFramework.getPaneList().get(i));
        }

        int totalPages = (int) Math.ceil((double) totalProducts / maxProductsPerPage);
        workspaceListFramework.setTotalPages(totalPages);

        workspaceListFramework.getBtnBack().setDisable(currentPage <= 1);
        workspaceListFramework.getBtnBack().setCursor(currentPage <= 1 ? Cursor.DEFAULT : Cursor.HAND);

        workspaceListFramework.getBtnNext().setDisable(currentPage >= totalPages);
        workspaceListFramework.getBtnNext().setCursor(currentPage >= totalPages ? Cursor.DEFAULT : Cursor.HAND);
    }

    private int calculateMaxProducts(double width, double height) {
        int columns = Math.max(1, (int) (width - GAP_SIZE) / (PRODUCT_SIZE + GAP_SIZE));
        int rows = Math.max(1, (int) (height - GAP_SIZE*2) / (PRODUCT_SIZE + GAP_SIZE));

        return columns * rows;
    }
}