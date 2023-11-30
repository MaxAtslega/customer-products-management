package dev.atslega.cpmf.workspace;

import dev.atslega.cpmf.AppStyles;
import dev.atslega.cpmf.components.WorkspaceHeader;
import javafx.scene.layout.BorderPane;

public class WorkspacePattern {

    private static final String USERNAME = "Max Atslega";

    private static BorderPane backPlate;

    private static WorkspaceNavigator workspaceNavigator;

    public static BorderPane workspace() {
        backPlate = new BorderPane();
        workspaceNavigator = new WorkspaceNavigator(AppStyles.SIDEBAR_WIDTH);

        // set top [User, Toolbar, Program-Info]
        backPlate.setTop(new WorkspaceHeader(USERNAME, AppStyles.SIDEBAR_WIDTH));

        // set Center [Home, Costumer, Products,short´s,calendar] (switch function)
        backPlate.setCenter(Workspaces.HOME.getPane());

        // set Left [Tab´s]
        backPlate.setLeft(workspaceNavigator);

        return backPlate;
    }

    public static void setCenter(Workspaces workspace) {
        backPlate.setCenter(workspace.getPane());
    }

    public static BorderPane getBackPlate() {
        return backPlate;
    }

    public static void toggleNavigator() {
        backPlate.setLeft(backPlate.getLeft() == null ? workspaceNavigator : null);
    }
}
