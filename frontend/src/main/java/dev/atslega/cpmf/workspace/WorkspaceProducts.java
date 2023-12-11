package dev.atslega.cpmf.workspace;

import dev.atslega.cpmf.TempStorage;
import dev.atslega.cpmf.component.workspace.list.WorkspaceListFramework;
import dev.atslega.cpmf.model.Product;
import dev.atslega.cpmf.panes.ProductContentPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class WorkspaceProducts extends WorkspaceListFramework {

    public WorkspaceProducts(WorkspacePattern workspacePattern) {
        super(workspacePattern, initializePaneList(), "Products");
    }

    private static List<Pane> initializePaneList() {
        List<Pane> paneList = new ArrayList<>();

        TempStorage tempStorage = new TempStorage();
        for (Product product : tempStorage.productList) {
            paneList.add(new ProductContentPane(product));
        }

        return paneList;
    }
}
