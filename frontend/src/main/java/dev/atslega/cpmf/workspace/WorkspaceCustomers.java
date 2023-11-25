package dev.atslega.cpmf.workspace;

import dev.atslega.cpmf.Main;
import dev.atslega.cpmf.TableOfPanesInCustomerViewer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


import java.util.ArrayList;

public class WorkspaceCustomers extends Pane {

    private final TableOfPanesInCustomerViewer table = new TableOfPanesInCustomerViewer();

    private final ArrayList<Integer> sideBoundsBegin = new ArrayList<>();
    private final ArrayList<Integer> sideBoundsEnd = new ArrayList<>();

    private final Button bt1, bt2, bt3;

    private boolean jumpToPaneVisible = false;
    private final Label currentSide = new Label();
    private final Button jumpToFirstSide,jumpToLastSide,jumpTeenSidesForward,jumpTeenSidesBackward;

    private int sideCount =1;
    private int sideOnViewer=1;

    public WorkspaceCustomers() {
        setPrefHeight(400);
        setPrefWidth(500);
        setStyle("-fx-background-color: #26262B");

        //Icons
        Image nextButtonRightIcon = new Image(getClass().getResourceAsStream("Images/nextsideButtonRight.png"));
        Image nextButtonLeftIcon = new Image(getClass().getResourceAsStream("Images/nextsideButtonLeft.png"));
        Image listViewerButtonIcon = new Image(getClass().getResourceAsStream("Images/sidelistViewerButton.png"));

        //Buttons
        bt1 = creatButton(40,10,2,nextButtonLeftIcon);
        bt2 = creatButton(40,60,2,listViewerButtonIcon);
        bt3 = creatButton(40,120, 2,nextButtonRightIcon);

        // Viewer Pane
        Pane customerViewer = new Pane();
        customerViewer.setPrefWidth(getPrefWidth());
        customerViewer.setPrefHeight(getPrefHeight());

        // buttomPane
        Pane buttomPane = new Pane();
        buttomPane.setPrefWidth(customerViewer.getPrefWidth());
        buttomPane.setPrefHeight(50);
        buttomPane.layoutYProperty().bind(customerViewer.heightProperty().subtract(buttomPane.getPrefHeight()));
        buttomPane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.15);");

        //jumpPane
        Pane jumpToPane = new Pane();
        jumpToPane.setPrefSize(250,50);
        jumpToPane.setStyle("-fx-background-color: white; -fx-border-color: rgba(0, 0, 0, 0.5);  -fx-border-width: 3px;");
        jumpToPane.layoutYProperty().bind(customerViewer.heightProperty().subtract(100));
        jumpToPane.setVisible(false);
        //show current side
        currentSide.setText("1");
        currentSide.setPrefSize(50,50);
        currentSide.setLayoutY(0);
        currentSide.setLayoutX(125);
        currentSide.setStyle("-fx-font-size: 16; -fx-font-weight: bold; -fx-alignment: center;");

        //Buttons of JumpPane
        jumpToFirstSide = new Button("1");
        jumpToFirstSide.setPrefSize(50,50);
        jumpToFirstSide.setLayoutX(0);
        jumpToFirstSide.setStyle("-fx-background-color: transparent; -fx-font-size: 14; -fx-font-weight: bold; -fx-alignment: center;");
        jumpTeenSidesForward = new Button("+10");
        jumpTeenSidesForward.setPrefSize(50,50);
        jumpTeenSidesForward.setLayoutX(150);
        jumpTeenSidesForward.setStyle("-fx-background-color: transparent;  -fx-font-size: 14; -fx-font-weight: bold; -fx-alignment: center;");
        jumpTeenSidesBackward = new Button("-10");
        jumpTeenSidesBackward.setPrefSize(50,50);
        jumpTeenSidesBackward.setLayoutX(50);
        jumpTeenSidesBackward.setStyle("-fx-background-color: transparent;  -fx-font-size: 14; -fx-font-weight: bold; -fx-alignment: center;");
        jumpToLastSide = new Button();
        jumpToLastSide.setPrefSize(50,50);
        jumpToLastSide.setLayoutX(200);
        jumpToLastSide.setStyle("-fx-background-color: transparent;  -fx-font-size: 14; -fx-font-weight: bold; -fx-alignment: center;");
        //Button Action
        jumpToFirstSide.setOnMouseClicked(event -> {
            setSide('F');
        });
        jumpToLastSide.setOnMouseClicked(event -> {
            setSide('L');
        });
        jumpTeenSidesForward.setOnMouseClicked(event -> {
            setSide('1');
        });
        jumpTeenSidesBackward.setOnMouseClicked(event -> {
            setSide('0');
        });

        //set table x and y
        table.setLayoutX(10);
        table.setLayoutY(10);

        // Button action
        bt1.setOnMouseClicked(event -> setSide('-'));
        bt2.setOnMouseClicked(event ->{
            if(!jumpToPaneVisible){
                jumpToPane.setVisible(true);
                jumpToPaneVisible=true;
            }else {
                jumpToPane.setVisible(false);
                jumpToPaneVisible =false;
            }
        });
        bt2.setOnMouseEntered(event -> {
            if(!jumpToPaneVisible) {
                jumpToPane.setVisible(true);
            }
        });
        bt2.setOnMouseExited(event ->{
            if(!jumpToPaneVisible){
                jumpToPane.setVisible(false);
            }
        });
        bt3.setOnMouseClicked(event -> setSide('+'));

        ChangeListener<Number> listenerCustomerPane = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double customerViewerX = (getWidth());
                double customerViewerY = (getHeight());
                customerViewer.setPrefWidth(customerViewerX);
                customerViewer.setPrefHeight(customerViewerY);
                buttomPane.setPrefWidth(customerViewerX);

                //set table Size
                table.setPrefSize(customerViewerX-20,customerViewerY-70);
                //set sideBounds for Pane Convert
                findPageBounds();
                jumpToLastSide.setText(String.valueOf(sideCount));
                if(sideOnViewer>sideCount){
                    sideOnViewer--;
                }
                table.setBoundsOfCustomerIndex(sideBoundsBegin.get(sideOnViewer - 1), sideBoundsEnd.get(sideOnViewer - 1));
            }
        };

        ChangeListener<Number> listenerButtomPane = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double jumpToPaneX = ((buttomPane.getWidth())/2)-127;
                double bt2X = ((buttomPane.getWidth())/2)-30;
                double bt1X = bt2X -45;
                bt2.setLayoutX(bt2X);
                double bt3X = bt2X +45;
                bt1.setLayoutX(bt1X);
                bt2.setLayoutX(bt2X);
                bt3.setLayoutX(bt3X);
                jumpToPane.setLayoutX(jumpToPaneX);
                currentSide.setLayoutX((jumpToPane.getWidth()/2)-25);
            }
        };

        widthProperty().addListener(listenerCustomerPane);
        heightProperty().addListener(listenerCustomerPane);
        buttomPane.widthProperty().addListener(listenerButtomPane);
        buttomPane.heightProperty().addListener(listenerButtomPane);

        getChildren().add(customerViewer);
        customerViewer.getChildren().add(buttomPane);
        customerViewer.getChildren().add(table);
        getChildren().add(jumpToPane);
        buttomPane.getChildren().addAll(bt1,bt2,bt3);
        jumpToPane.getChildren().addAll(jumpToFirstSide,jumpTeenSidesForward,currentSide,jumpTeenSidesBackward,jumpToLastSide);

    }

    private Button creatButton(int widthAndHeight, int x, int y, Image icon){
        Button bt = new Button();
        bt.setPrefWidth(widthAndHeight);
        bt.setPrefHeight(widthAndHeight);
        bt.setLayoutX(x);
        bt.setLayoutY(y);
        bt.setStyle("-fx-background-color: transparent");

        ImageView imageView = new ImageView(icon);
        imageView.setFitHeight(widthAndHeight);
        imageView.setFitWidth(widthAndHeight);
        bt.setGraphic(imageView);

        return bt;
    }

    private void setSide(char a){
        switch (a){
            //function to set next side
            case'+':
                if(sideOnViewer<sideCount) {
                    sideOnViewer++;
                    currentSide.setText(String.valueOf(sideOnViewer));
                    table.setBoundsOfCustomerIndex(sideBoundsBegin.get(sideOnViewer - 1), sideBoundsEnd.get(sideOnViewer - 1));
                }
                break;
            //function to set last side
            case'-':
                if(sideOnViewer>1) {
                    sideOnViewer--;
                    currentSide.setText(String.valueOf(sideOnViewer));
                    table.setBoundsOfCustomerIndex(sideBoundsBegin.get(sideOnViewer - 1), sideBoundsEnd.get(sideOnViewer - 1));
                }
                break;
            //function to set first side
            case'F':
                sideOnViewer=1;
                currentSide.setText(String.valueOf(sideOnViewer));
                table.setBoundsOfCustomerIndex(sideBoundsBegin.get(sideOnViewer - 1), sideBoundsEnd.get(sideOnViewer - 1));
                break;
            //function to set last side
            case'L':
                sideOnViewer=sideCount;
                currentSide.setText(String.valueOf(sideOnViewer));
                table.setBoundsOfCustomerIndex(sideBoundsBegin.get(sideOnViewer - 1), sideBoundsEnd.get(sideOnViewer - 1));
                break;
            //function to set sides +10 or first side
            case'1':
                if((sideOnViewer+10)<sideCount){
                    sideOnViewer =sideOnViewer+ 10;
                    currentSide.setText(String.valueOf(sideOnViewer));
                    table.setBoundsOfCustomerIndex(sideBoundsBegin.get(sideOnViewer - 1), sideBoundsEnd.get(sideOnViewer - 1));
                }else{
                    setSide('L');
                }
                break;
            //function to set sides -10 or last side
            case'0':
                if((sideOnViewer-10)>1){
                    sideOnViewer =sideOnViewer-10;
                    currentSide.setText(String.valueOf(sideOnViewer));
                    table.setBoundsOfCustomerIndex(sideBoundsBegin.get(sideOnViewer - 1), sideBoundsEnd.get(sideOnViewer - 1));
                }else{
                    setSide('F');
                }
                break;

        }
        //test
        /*
        System.out.println("side set to: "+sideOnViewer);
        System.out.println("B: "+sideBoundsBegin.get(sideOnViewer-1)+"\nE: "+sideBoundsEnd.get(sideOnViewer-1));
        System.out.println(table.getBoundsOfCustomerIndex());
        System.out.println("_____________________________________");
        */

    }

    private void findPageBounds() {
        //clear all Bounds
        sideBoundsEnd.clear();
        sideBoundsBegin.clear();
        //get All numbers that we need
        int itemsPerPage=table.getMaxPanesCount();
        int customerListSize = Main.tempStorage.customerList.size();

        //System.out.println("items per Page: "+itemsPerPage+"\nAll Items: "+customerListSize);

        //set Page if only one page is available
        if (customerListSize <= itemsPerPage) {
            sideCount = 1;
            sideBoundsBegin.add(0);
            sideBoundsEnd.add(customerListSize-1);
        } else {
            //get Side's
            double a = (double) customerListSize / itemsPerPage;
            //System.out.println(a);
            if(hasDecimal(a)){
                sideCount = (int)  a+1;
            }else{
                sideCount = (int)  a;
            }
            //System.out.println(sideCount);
            //set all side Bounds
            for (int i = 0; i < sideCount; i++) {
                int begin = i * itemsPerPage;
                int end = (i + 1) * itemsPerPage - 1;
                if (end > customerListSize - 1) {
                    end = customerListSize - 1;
                }
                //set it in the arraylist
                sideBoundsBegin.add(begin);
                sideBoundsEnd.add(end);
            }
        }
        /*
        //test
        for(int e=0;e<sideBoundsEnd.size();e++){
            System.out.println("for side: "+e);
            System.out.println(sideBoundsBegin.get(e));
            System.out.println(sideBoundsEnd.get(e));
            System.out.println("_______________________________");
        }
         */
    }

    private boolean hasDecimal(double number) {
        return number % 1 != 0;
    }
}

