package dev.atslega.cpmf.workspace;

import dev.atslega.cpmf.components.WorkspaceHeader;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class WorkspacePattern {

    private static final String USERNAME = "Max Atslega";
    private static int sidebarWidth = 200;

    public static final Color DEFAULT_TEXT_COLOR = Color.valueOf("5E6673");
    public static final Color HOOVER_TEXT_COLOR = Color.WHITE;
    public static final Color PRIMARY_TEXT_COLOR = Color.valueOf("1976D2");

    private static BorderPane backPlate;

    private static WorkspaceNavigator workspaceNavigator;

    public static BorderPane workspace() {
        backPlate = new BorderPane();
        workspaceNavigator = new WorkspaceNavigator(sidebarWidth);

        // set top [User, Toolbar, Program-Info]
        backPlate.setTop(new WorkspaceHeader(USERNAME, sidebarWidth));

        // set Center [Home, Costumer, Products,short´s,calendar] (switch function)
        backPlate.setCenter(Workspaces.HOME.getPane());

        // set Left [Tab´s]
        backPlate.setLeft(workspaceNavigator);

        return backPlate;
    }

    public static void setCenter(Workspaces workspace){
        backPlate.setCenter(workspace.getPane());
    }

    public static BorderPane getBackPlate() {
        return backPlate;
    }

    public static void toggleNavigator(){
        backPlate.setLeft(backPlate.getLeft() == null ? workspaceNavigator : null);
    }
}
