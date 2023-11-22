package dev.atslega.cpmf.workspace;

import javafx.scene.layout.BorderPane;

public class WorkspacePattern {

    public static BorderPane backPlate;
    public static WorkspaceWorkstation workstation = new WorkspaceWorkstation();
    public static WorkspaceHome home = new WorkspaceHome();
    public static WorkspaceCustomers customers = new WorkspaceCustomers();
    public static WorkspaceProducts products = new WorkspaceProducts();
    public static WorkspaceSaleList soldlist = new WorkspaceSaleList();
    public static WorkspaceShorts shorts = new WorkspaceShorts();
    public static WorkspaceCalendar calendar = new WorkspaceCalendar();
    public static WorkspaceNavigater navigater = new WorkspaceNavigater();

    public static BorderPane workspace() {
        backPlate = new BorderPane();

        //set top [User, Toolbar, Program-Info]
        backPlate.setTop(workstation);
        // set Center [Home, Costumer, Products,short´s,calendar] (switch function)
        backPlate.setCenter(home);
        // set Left [Tab´s]
        backPlate.setLeft(navigater);

        return backPlate;
    }

    public static void setCenter(int numberOfButton){
        switch (numberOfButton){
            case 1:
                backPlate.setCenter(home);
                break;
            case 2:
                backPlate.setCenter(customers);
                break;
            case 3:
                backPlate.setCenter(products);
                break;
            case 4:
                backPlate.setCenter(soldlist);
                break;
            case 5:
                backPlate.setCenter(shorts);
                break;
            case 6:
                backPlate.setCenter(calendar);
                break;
        }
    }
}
