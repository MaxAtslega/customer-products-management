package dev.atslega.cpmf.workspace;

import dev.atslega.cpmf.AppStyles;
import dev.atslega.cpmf.StageManager;
import dev.atslega.cpmf.component.WorkspaceHeader;
import dev.atslega.cpmf.model.User;
import javafx.scene.layout.BorderPane;

public class WorkspacePattern extends BorderPane {
    private final StageManager stageManager;
    private WorkspaceNavigator workspaceNavigator;
    private final User user;

    public WorkspacePattern(StageManager stageManager, User user) {
        this.stageManager = stageManager;
        this.user = user;

        workspaceNavigator = new WorkspaceNavigator(this, AppStyles.SIDEBAR_WIDTH);

        // set top [User, Toolbar, Program-Info]
        setTop(new WorkspaceHeader(this, AppStyles.SIDEBAR_WIDTH));

        // set Center [Home, Costumer, Products,short´s,calendar] (switch function)
        setCenter(Workspaces.HOME.getPane(this));

        // set Left [Tab´s]
        setLeft(workspaceNavigator);
    }

    public User getUser() {
        return user;
    }

    public StageManager getStageManager() {
        return stageManager;
    }

    public void setCenter(Workspaces workspace) {
        setCenter(workspace.getPane(this));
    }

    public void toggleNavigator() {
        setLeft(getLeft() == null ? workspaceNavigator : null);
    }
}
