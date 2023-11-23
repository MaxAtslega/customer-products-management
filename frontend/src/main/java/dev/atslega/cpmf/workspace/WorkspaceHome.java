package dev.atslega.cpmf.workspace;

import dev.atslega.cpmf.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.io.IOException;

public class WorkspaceHome extends Pane {

    public WorkspaceHome (){
        setPrefHeight(400);
        setPrefWidth(400);

        // background picture
        Image backgroundImage = null;
        try {
            backgroundImage = new Image(getClass().getResource("Images/forestbackground.png").openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageView imageView = new ImageView(backgroundImage);
        imageView.fitWidthProperty().bind(widthProperty());
        imageView.fitHeightProperty().bind(heightProperty());

        Pane childPane = new Pane();
        childPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4);");
        // resizeable
        childPane.prefWidthProperty().bind(widthProperty().multiply(0.6));
        childPane.prefHeightProperty().bind(heightProperty().multiply(0.7));

        //Contant for the childPane
        Label title = new Label();
        title.setText("Home");
        // set Style
        title.styleProperty().bind(
                new SimpleStringProperty("-fx-font-size: ").concat(childPane.widthProperty().multiply(0.1))
                        .concat("; -fx-font-weight: bold; -fx-text-fill: black;")
        );
        title.layoutXProperty().bind(childPane.widthProperty().subtract(title.widthProperty()).divide(2));
        title.translateYProperty().bind(childPane.heightProperty().multiply(0.2).subtract(title.heightProperty().divide(2)));

        Label customerH = new Label("Customers:");
        customerH.styleProperty().bind(
            new SimpleStringProperty("-fx-font-size: ").concat(childPane.widthProperty().multiply(0.08)).concat("; -fx-font-weight: bold; -fx-text-fill: black;")
        );
        customerH.layoutXProperty().bind(childPane.widthProperty().subtract(customerH.widthProperty()).divide(9));
        customerH.translateYProperty().bind(childPane.heightProperty().multiply(0.4).subtract(customerH.heightProperty().divide(2)));
        Label customerCount = new Label(String.valueOf(Main.tempStorage.customerList.size()));
        customerCount.styleProperty().bind(
                new SimpleStringProperty("-fx-font-size: ").concat(childPane.widthProperty().multiply(0.08)).concat("; -fx-font-weight: bold; -fx-text-fill: black;")
        );
        customerCount.layoutXProperty().bind(childPane.widthProperty().subtract(customerCount.widthProperty()).divide(9));
        customerCount.translateYProperty().bind(childPane.heightProperty().multiply(0.5).subtract(customerCount.heightProperty().divide(2)));
        Label productH = new Label("Product:");
        productH.styleProperty().bind(
                new SimpleStringProperty("-fx-font-size: ").concat(childPane.widthProperty().multiply(0.08)).concat("; -fx-font-weight: bold; -fx-text-fill: black;")
        );
        productH.layoutXProperty().bind(childPane.widthProperty().subtract(productH.widthProperty()).divide(9).multiply(8));
        productH.translateYProperty().bind(childPane.heightProperty().multiply(0.4).subtract(productH.heightProperty().divide(2)));
        Label productCount = new Label(String.valueOf(Main.tempStorage.productList.size()));
        productCount.styleProperty().bind(
                new SimpleStringProperty("-fx-font-size: ").concat(childPane.widthProperty().multiply(0.08)).concat("; -fx-font-weight: bold; -fx-text-fill: black;")
        );
        productCount.layoutXProperty().bind(childPane.widthProperty().subtract(productCount.widthProperty()).divide(9).multiply(8));
        productCount.translateYProperty().bind(childPane.heightProperty().multiply(0.5).subtract(productCount.heightProperty().divide(2)));
        Label saleH = new Label("Sales:");
        saleH.styleProperty().bind(
                new SimpleStringProperty("-fx-font-size: ").concat(childPane.widthProperty().multiply(0.08))
                        .concat("; -fx-font-weight: bold; -fx-text-fill: black;")
        );
        saleH.layoutXProperty().bind(childPane.widthProperty().subtract(saleH.widthProperty()).divide(2));
        saleH.translateYProperty().bind(childPane.heightProperty().multiply(0.7).subtract(saleH.heightProperty().divide(2)));
        Label saleCount = new Label(String.valueOf(Main.tempStorage.saleList.size()));
        saleCount.styleProperty().bind(
                new SimpleStringProperty("-fx-font-size: ").concat(childPane.widthProperty().multiply(0.08))
                        .concat("; -fx-font-weight: bold; -fx-text-fill: black;")
        );
        saleCount.layoutXProperty().bind(childPane.widthProperty().subtract(saleCount.widthProperty()).divide(2));
        saleCount.translateYProperty().bind(childPane.heightProperty().multiply(0.8).subtract(saleCount.heightProperty().divide(2)));
        // listener for the Home Pane to change position of childPane
        ChangeListener<Number> listenerHomePane = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double childPaneX = (getWidth() - childPane.getPrefWidth()) / 2;
                double childPaneY = (getHeight() - childPane.getPrefHeight()) / 2;
                childPane.setLayoutX(childPaneX);
                childPane.setLayoutY(childPaneY);
            }
        };

        // listener bind to Property of Panes
        widthProperty().addListener(listenerHomePane);
        heightProperty().addListener(listenerHomePane);

        // add Contant to Home Pane
        getChildren().add(imageView);
        getChildren().add(childPane);
        childPane.getChildren().addAll(title,customerH,customerCount,productH,productCount,saleH,saleCount);
    }

}
