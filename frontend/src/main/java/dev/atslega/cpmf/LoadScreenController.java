package dev.atslega.cpmf;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.util.Objects;

public class LoadScreenController {

    @FXML
    private ImageView getriebeGif0;
    @FXML
    private ImageView background;

    public LoadScreenController() throws IOException {
    }

    public void initialize() throws IOException {
        background.setImage(new Image(Objects.requireNonNull(getClass().getResource("Images/loadBackground.png")).openStream()));
        //gifBottom.setImage(gifBottomI);
    }
}
