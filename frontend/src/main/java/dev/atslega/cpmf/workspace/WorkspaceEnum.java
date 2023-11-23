package dev.atslega.cpmf.workspace;

import javafx.scene.layout.Pane;

public enum WorkspaceEnum {
    HOME("Home", "homeButtonIcon.png", new WorkspaceHome()),
    PRODUCTS("Products", "productsButtonIcon.png", new WorkspaceProducts()),
    CUSTOMERS("Customers", "customersButtonIcon.png", new WorkspaceCustomers()),
    SALE_LIST("Sale List", "soldlistButtonIcon.png", new WorkspaceSaleList()),
    SHORTS("Shorts", "shortButtonIcon.png", new WorkspaceShorts()),
    CALENDAR("Calendar", "calendarButtonIcon.png", new WorkspaceCalendar());


    private final Pane pane;
    private final String name;
    private final String icon;

    WorkspaceEnum(String name, String icon, Pane pane) {
        this.name = name;
        this.icon = icon;
        this.pane = pane;
    }

    public Pane getPane() {
        return pane;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }
}
