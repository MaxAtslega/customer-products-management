package dev.atslega.cpmf.component.home;

import dev.atslega.cpmf.AppStyles;
import dev.atslega.cpmf.component.PaginationBar;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class UsersList extends VBox {

    private final PaginationBar paginationBar;
    private VBox userPane;

    private int currentPage = 1;
    private int totalPages = 1;

    public UsersList() {
        this.paginationBar = new PaginationBar(1);
        paginationBar.getBtnBack().setOnAction(e -> changePage(-1));
        paginationBar.getBtnNext().setOnAction(e -> changePage(1));

        userPane = createUsersPane();

        getChildren().addAll(userPane, paginationBar);
    }

    private void changePage(int page) {
        currentPage = Math.max(1, Math.min(totalPages, currentPage + page));
        paginationBar.getLabelCurrentPage().setText("Page: " + currentPage);
        userPane = createUsersPane();
    }

    private VBox createUsersPane() {
        VBox usersPane = new VBox();

        // Create and add boxes to the usersPane
        VBox user1 = createUserBox("1", "Lasse Hüls", "hüls@atslega.de");
        VBox user2 = createUserBox("2", "Murma Kleis", "kleis@atslega.de");
        VBox user3 = createUserBox("3", "Stefan Test", "test@atslega.de");
        VBox user4 = createUserBox("4", "Samuel Beis", "beis@atslega.de");

        usersPane.getChildren().addAll(user1, user2, user3, user4);

        setTotalPages(currentPage + 1);

        getPaginationBar().getBtnBack().setDisable(currentPage <= 1);
        getPaginationBar().getBtnBack().setCursor(currentPage <= 1 ? Cursor.DEFAULT : Cursor.HAND);

        getPaginationBar().getBtnNext().setDisable(currentPage >= totalPages);
        getPaginationBar().getBtnNext().setCursor(currentPage >= totalPages ? Cursor.DEFAULT : Cursor.HAND);


        return usersPane;
    }

    private VBox createUserBox(String id, String name, String email) {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(AppStyles.GAP_SIZE));
        vBox.setSpacing(5);
        vBox.setStyle("-fx-background-color: " + AppStyles.SECONDARY_BACKGROUND_COLOR + "; -fx-background-radius: 5;");
        VBox.setMargin(vBox, new Insets(10, 0, 0, 0));

        HBox hBox = new HBox();

        Text title = new Text(id + " - " + name + " - " + email);
        title.setFont(Font.font("Roboto", AppStyles.TEXT_NORMAL));
        title.setFill(Color.WHITE);

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Text titleLabel = new Text("Show");
        titleLabel.setFont(Font.font("Quicksand", FontWeight.BOLD, 16));
        titleLabel.setFill(Color.WHITE);

        hBox.getChildren().addAll(title, spacer, titleLabel);
        vBox.getChildren().add(hBox);

        return vBox;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public PaginationBar getPaginationBar() {
        return paginationBar;
    }
}
