package dev.atslega.cpmf.panes;

import dev.atslega.cpmf.model.Sale;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class SaleSidePane extends Pane {

    private Sale sale;

    int sideCounter=0;

    SaleSideOne saleSideOne;
    SaleSideTwo saleSideTwo;
    SaleSideThree saleSideThree;
    SaleSideFour saleSideFour ;


    public SaleSidePane(Sale sale){
        this.sale = sale;
        setPrefSize(200,220);
        setStyle("-fx-background-radius: 15; -fx-background-color: #FF6A6A;");
        setStyle("-fx-background-radius: 15; -fx-background-color: transparent;");

        saleSideOne = new SaleSideOne(sale);
        saleSideTwo = new SaleSideTwo(sale);
        saleSideTwo.setVisible(false);
        saleSideThree = new SaleSideThree(sale.getCustomer());
        saleSideThree.setVisible(false);
        saleSideFour = new SaleSideFour(sale.getProduct());
        saleSideFour.setVisible(false);

        Image switchButton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Images/switchSideButton.png")));
        ImageView infoIconView = new ImageView(switchButton);
        infoIconView.setLayoutY(190);
        infoIconView.setLayoutX(170);
        infoIconView.setOnMouseClicked(event -> {
        /*
        setOnMouseClicked(event -> {
            switch(sideCounter){
                case 0:
                    saleSideFour.setVisible(false);
                    saleSideOne.setVisible(true);
                    sideCounter++;
                    break;
                case 1:
                    saleSideOne.setVisible(false);
                    saleSideTwo.setVisible(true);
                    sideCounter++;
                    break;
                case 2:
                    saleSideTwo.setVisible(false);
                    saleSideThree.setVisible(true);
                    sideCounter++;
                    break;
                case 3:
                    saleSideThree.setVisible(false);
                    saleSideFour.setVisible(true);
                    sideCounter=0;
                    break;
            }
            */

        });



        getChildren().addAll(saleSideOne,saleSideTwo,saleSideThree,saleSideFour);
    }

    public Sale getSale() {
        return sale;
    }

}
