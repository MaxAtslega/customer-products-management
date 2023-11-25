package dev.atslega.cpmf.workspace;


import dev.atslega.cpmf.TempStorage;
import dev.atslega.cpmf.components.workspaceList.WorkspaceListFramework;
import dev.atslega.cpmf.model.Sale;
import dev.atslega.cpmf.panes.SaleSidePane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class WorkspaceSaleList extends WorkspaceListFramework {

    public WorkspaceSaleList() {
        super(initializePaneList(), "Sale List");
    }

    private static List<Pane> initializePaneList() {
        List<Pane> paneList = new ArrayList<>();

        TempStorage tempStorage = new TempStorage();
        for (Sale sale : tempStorage.saleList) {
            paneList.add(new SaleSidePane(sale));
        }

        return paneList;
    }

}
