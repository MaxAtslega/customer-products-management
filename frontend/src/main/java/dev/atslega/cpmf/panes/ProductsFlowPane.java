package dev.atslega.cpmf.panes;

import dev.atslega.cpmf.model.Product;
import dev.atslega.cpmf.workspace.WorkspaceProducts;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.layout.FlowPane;
import javafx.util.Duration;

public class ProductsFlowPane extends FlowPane {
    private static final int PRODUCT_SIZE = 200;
    private static final int GAP_SIZE = 10;

    private WorkspaceProducts workspaceProducts;
    private PauseTransition pauseTransition;

    public ProductsFlowPane(WorkspaceProducts workspaceProducts) {
        this.workspaceProducts = workspaceProducts;

        setPadding(new Insets(GAP_SIZE));
        setHgap(GAP_SIZE);
        setVgap(GAP_SIZE);
        setMaxHeight(Double.MAX_VALUE);

        setStyle("-fx-background-color: #26262B");

        pauseTransition = new PauseTransition(Duration.seconds(0.1));
        pauseTransition.setOnFinished(event -> updateProducts());

        widthProperty().addListener(e -> restartCooldown());
        heightProperty().addListener(e -> restartCooldown());
    }

    private void restartCooldown() {
        pauseTransition.stop();
        pauseTransition.playFromStart();
    }

    private void updateProducts() {
        int maxProductsPerPage = calculateMaxProducts(getWidth(), getHeight());

        int currentPage = workspaceProducts.getCurrentPage();
        int totalProducts = workspaceProducts.getTotalProducts();

        int start = (currentPage - 1) * maxProductsPerPage;
        int end = Math.min(start + maxProductsPerPage, totalProducts);

        getChildren().clear();
        for (int i = start; i < end; i++) {
            getChildren().add(new ProductContentPane(new Product("Test", i+"", "ewwe", "ewwe", 100, 10.5)));
        }

        int totalPages = (int) Math.ceil((double) totalProducts / maxProductsPerPage);
        workspaceProducts.setTotalPages(totalPages);

        workspaceProducts.getBtnBack().setDisable(currentPage <= 1);
        workspaceProducts.getBtnBack().setCursor(currentPage <= 1 ? Cursor.DEFAULT : Cursor.HAND);

        workspaceProducts.getBtnNext().setDisable(currentPage >= totalPages);
        workspaceProducts.getBtnNext().setCursor(currentPage >= totalPages ? Cursor.DEFAULT : Cursor.HAND);
    }

    private int calculateMaxProducts(double width, double height) {
        int columns = Math.max(1, (int) (width - GAP_SIZE) / (PRODUCT_SIZE + GAP_SIZE));
        int rows = Math.max(1, (int) (height - GAP_SIZE*2) / (PRODUCT_SIZE + GAP_SIZE));

        return columns * rows;
    }
}