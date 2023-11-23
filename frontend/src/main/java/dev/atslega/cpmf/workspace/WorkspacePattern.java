package dev.atslega.cpmf.workspace;

import javafx.scene.layout.BorderPane;

public class WorkspacePattern {

    private static final String USERNAME = "Max Atslega";

    public static BorderPane backPlate;

    public static BorderPane workspace() {
        backPlate = new BorderPane();

        // set top [User, Toolbar, Program-Info]
        backPlate.setTop(new WorkspaceWorkstation(USERNAME));

        // set Center [Home, Costumer, Products,short´s,calendar] (switch function)
        backPlate.setCenter(new WorkspaceHome());

        // set Left [Tab´s]
        backPlate.setLeft(new WorkspaceNavigator());

        return backPlate;
    }

    public static void setCenter(WorkspaceEnum workspace){
        backPlate.setCenter(workspace.getPane());
    }
}
