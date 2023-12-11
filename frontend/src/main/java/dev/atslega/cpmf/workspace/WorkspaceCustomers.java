package dev.atslega.cpmf.workspace;

import dev.atslega.cpmf.TempStorage;
import dev.atslega.cpmf.component.workspace.list.WorkspaceListFramework;
import dev.atslega.cpmf.model.Customer;
import dev.atslega.cpmf.panes.CustomerContentPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class WorkspaceCustomers extends WorkspaceListFramework {

    public WorkspaceCustomers(WorkspacePattern workspacePattern) {
        super(workspacePattern, initializePaneList(), "Customers");
    }

    private static List<Pane> initializePaneList() {
        List<Pane> paneList = new ArrayList<>();

        TempStorage tempStorage = new TempStorage();
        for (Customer customer : tempStorage.customerList) {
            paneList.add(new CustomerContentPane(customer));
        }

        return paneList;
    }
}


