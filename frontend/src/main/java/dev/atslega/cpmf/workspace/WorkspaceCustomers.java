package dev.atslega.cpmf.workspace;

import dev.atslega.cpmf.Main;
import dev.atslega.cpmf.TableOfPanesInCustomerViewer;
import dev.atslega.cpmf.TempStorage;
import dev.atslega.cpmf.components.workspaceList.WorkspaceListFramework;
import dev.atslega.cpmf.model.Customer;
import dev.atslega.cpmf.model.Product;
import dev.atslega.cpmf.panes.CustomerSidePane;
import dev.atslega.cpmf.panes.ProductContentPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


import java.util.ArrayList;
import java.util.List;

public class WorkspaceCustomers extends WorkspaceListFramework {

    public WorkspaceCustomers() {
        super(initializePaneList());
    }

    private static List<Pane> initializePaneList() {
        List<Pane> paneList = new ArrayList<>();

        TempStorage tempStorage = new TempStorage();
        for(Customer customer : tempStorage.customerList){
            paneList.add(new CustomerSidePane(customer));
        }

        return paneList;
    }
}


