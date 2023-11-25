package dev.atslega.cpmf.workspace;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class WorkspacePattern {

    private static final String USERNAME = "Max Atslega";
    private static int sidebarWidth = 200;

    public static final Color DEFAULT_TEXT_COLOR = Color.valueOf("5E6673");
    public static final Color HOOVER_TEXT_COLOR = Color.WHITE;
    public static final Color PRIMARY_TEXT_COLOR = Color.valueOf("1976D2");

    private static BorderPane backPlate;

    public static BorderPane workspace() {
        backPlate = new BorderPane();

        // set top [User, Toolbar, Program-Info]
        backPlate.setTop(new WorkspaceWorkstation(USERNAME, sidebarWidth));

        // set Center [Home, Costumer, Products,short´s,calendar] (switch function)
        backPlate.setCenter(Workspaces.HOME.getPane());

        // set Left [Tab´s]
        backPlate.setLeft(new WorkspaceNavigator(sidebarWidth));

        return backPlate;
    }

    public static void setCenter(Workspaces workspace){
        backPlate.setCenter(workspace.getPane());
    }

    public static BorderPane getBackPlate() {
        return backPlate;
    }

    public static void toggleNavigator(){
        backPlate.setLeft(backPlate.getLeft() == null ? new WorkspaceNavigator(sidebarWidth) : null);
    }
}
