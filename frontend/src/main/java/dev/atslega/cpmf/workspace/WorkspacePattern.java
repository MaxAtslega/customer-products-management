package dev.atslega.cpmf.workspace;

import dev.atslega.cpmf.AppStyles;
import dev.atslega.cpmf.StageManager;
import dev.atslega.cpmf.components.WorkspaceHeader;
import javafx.scene.layout.BorderPane;

public class WorkspacePattern extends BorderPane {
    private final String USERNAME = "Max Atslega";
    private final StageManager stageManager;
    private WorkspaceNavigator workspaceNavigator;

    public WorkspacePattern(StageManager stageManager) {
        this.stageManager = stageManager;

        workspaceNavigator = new WorkspaceNavigator(this, AppStyles.SIDEBAR_WIDTH);

        // set top [User, Toolbar, Program-Info]
        setTop(new WorkspaceHeader(USERNAME, AppStyles.SIDEBAR_WIDTH));

        // set Center [Home, Costumer, Products,short´s,calendar] (switch function)
        setCenter(Workspaces.HOME.getPane());

        // set Left [Tab´s]
        setLeft(workspaceNavigator);
    }

    public StageManager getStageManager() {
        return stageManager;
    }

    public void setCenter(Workspaces workspace) {
        setCenter(workspace.getPane());
    }

    public void toggleNavigator() {
        setLeft(getLeft() == null ? workspaceNavigator : null);
    }
}
