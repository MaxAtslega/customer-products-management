package dev.atslega.cpmf.component.home;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.atslega.cpmf.AppStyles;
import dev.atslega.cpmf.component.PaginationBar;
import dev.atslega.cpmf.model.User;
import dev.atslega.cpmf.workspace.WorkspacePattern;
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

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class UsersListBox extends VBox {

    private final PaginationBar paginationBar;
    private final WorkspacePattern workspacePattern;
    private VBox userPane;
    private int currentPage = 1;
    private int totalPages;

    public UsersListBox(WorkspacePattern workspacePattern) throws ExecutionException, InterruptedException, JsonProcessingException {
        this.workspacePattern = workspacePattern;

        totalPages = (int) Math.ceil((((double) workspacePattern.getUserData().getCompany().getUserCount())) / 4);

        this.paginationBar = new PaginationBar(1);

        if ((workspacePattern.getUserData().getCompany().getUserCount() - 1) > 0) {
            paginationBar.getBtnBack().setOnAction(e -> changePage(-1));
            paginationBar.getBtnNext().setOnAction(e -> changePage(1));

            userPane = createUsersPane();
            getChildren().add(userPane);
        }

        getChildren().add(paginationBar);
    }

    private void changePage(int page) {
        currentPage = Math.max(1, Math.min(totalPages, currentPage + page));
        paginationBar.getLabelCurrentPage().setText("Page: " + currentPage);
        try {
            userPane = createUsersPane();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private VBox createUsersPane() throws ExecutionException, InterruptedException, JsonProcessingException {
        VBox usersPane = new VBox();

        List<User> users = fetchAllUsers(currentPage - 1, 4);

        for (User user : users) {
            usersPane.getChildren().add(createUserBox(user.getId() + "", user.getFirstName() + " " + user.getLastName(), user.getEmail()));
        }

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
        VBox.setMargin(vBox, new Insets(AppStyles.GAP_SIZE, 0, 0, 0));

        HBox hBox = new HBox();

        Text title = new Text(id + " - " + name + " - " + email);
        title.setFont(Font.font(AppStyles.FONT_FAMILY, AppStyles.TEXT_NORMAL));
        title.setFill(Color.valueOf(AppStyles.DEFAULT_TEXT_COLOR));

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Text titleLabel = new Text("Show");
        titleLabel.setFont(Font.font(AppStyles.FONT_FAMILY, FontWeight.BOLD, AppStyles.TEXT_NORMAL));
        titleLabel.setFill(Color.valueOf(AppStyles.DEFAULT_TEXT_COLOR));

        hBox.getChildren().addAll(title, spacer, titleLabel);
        vBox.getChildren().add(hBox);

        return vBox;
    }

    public List<User> fetchAllUsers(int page, int size) throws ExecutionException, InterruptedException, JsonProcessingException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/v1/users/" + "?page=" + page + "&size=" + size))
                .header("Authorization", "Bearer " + workspacePattern.getUserData().getToken())
                .header("Content-Type", "application/json")
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> responseFuture =
                client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        HttpResponse<String> response = responseFuture.get();

        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response.body(), new TypeReference<List<User>>() {
            });
        } else {
            return null;
        }
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public PaginationBar getPaginationBar() {
        return paginationBar;
    }
}
