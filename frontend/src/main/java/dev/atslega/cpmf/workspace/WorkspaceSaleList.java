package dev.atslega.cpmf.workspace;

import dev.atslega.cpmf.Main;
import dev.atslega.cpmf.panes.CustomerSidePane;
import dev.atslega.cpmf.panes.SaleSidePane;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.util.ArrayList;

public class WorkspaceSaleList extends Pane {

    private GridPane grid = new GridPane();

    private final ArrayList<Integer> sideBoundsBegin = new ArrayList<>();
    private final ArrayList<Integer> sideBoundsEnd = new ArrayList<>();

    private final Button bt1, bt2, bt3;
    private final Button jumpToFirstSide,jumpToLastSide,jumpTeenSidesForward,jumpTeenSidesBackward;

    private int boundsOfBegin =0;
    private int boundsOfEnd =9;

    private Pane saleListViewer;

    private IntegerProperty boundsOfBeginProperty = new SimpleIntegerProperty(boundsOfBegin);
    private IntegerProperty boundsOfEndProperty = new SimpleIntegerProperty(boundsOfEnd);

    private final int paneWidth = 200;
    private final int paneHeight = 220;
    private final int spacing = 10;

    private final Label currentSide = new Label();

    private boolean jumpToPaneVisible = false;

    private int sideCount =1;
    private int sideOnViewer=1;


    public WorkspaceSaleList () {
        setPrefHeight(400);
        setPrefWidth(400);
        setStyle("-fx-background-color: #26262B");

        grid.setLayoutX(10);
        grid.setLayoutY(10);
        grid.setHgap(10);
        grid.setVgap(10);

        for(int i=0; i<2;i++){
            grid.add(new SaleSidePane(Main.tempStorage.saleList.get(i)),i,0);
        }

        //Icons
        Image nextButtonRightIcon = new Image(getClass().getResourceAsStream("Images/nextsideButtonRight.png"));
        Image nextButtonLeftIcon = new Image(getClass().getResourceAsStream("Images/nextsideButtonLeft.png"));
        Image listViewerButtonIcon = new Image(getClass().getResourceAsStream("Images/sidelistViewerButton.png"));
        //Buttons
        bt1 = creatButton(40,10,2,nextButtonLeftIcon);
        bt2 = creatButton(40,60,2,listViewerButtonIcon);
        bt3 = creatButton(40,120, 2,nextButtonRightIcon);


        //Viewer Pane
        saleListViewer = new Pane();
        saleListViewer.setPrefWidth(getPrefWidth());
        saleListViewer.setPrefHeight(getPrefHeight());

        // buttomPane
        Pane buttomPane = new Pane();
        buttomPane.setPrefWidth(saleListViewer.getPrefWidth());
        buttomPane.setPrefHeight(50);
        buttomPane.layoutYProperty().bind(saleListViewer.heightProperty().subtract(buttomPane.getPrefHeight()));
        buttomPane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.15);");

        Pane jumpToPane = new Pane();
        jumpToPane.setPrefSize(250,50);
        jumpToPane.setStyle("-fx-background-color: white; -fx-border-color: rgba(0, 0, 0, 0.5);  -fx-border-width: 3px;");
        jumpToPane.layoutYProperty().bind(saleListViewer.heightProperty().subtract(100));
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
        /*
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

         */


        ChangeListener<Number> listenerSalePane = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double saleListViewerX = (getWidth());
                double saleListViewerY = (getHeight());
                saleListViewer.setPrefWidth(saleListViewerX);
                saleListViewer.setPrefHeight(saleListViewerY);
                buttomPane.setPrefWidth(saleListViewerX);

                //set sideBounds for Pane Convert
                findPageBounds();
                jumpToLastSide.setText(String.valueOf(sideCount));
                if(sideOnViewer>sideCount){
                    sideOnViewer--;
                }

                //new funtkion of add the contant
                setSide();

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

        boundsOfBeginProperty.addListener((observable, oldValue, newValue) -> {
            setSide();
            //System.out.println("BoundsOfBegin has changed. New value is: " + newValue);
        });

        boundsOfEndProperty.addListener((observable, oldValue, newValue) -> {
            setSide();
            //System.out.println("BoundsOfEnd has changed. New value is: " + newValue);
        });

        widthProperty().addListener(listenerSalePane);
        heightProperty().addListener(listenerSalePane);
        buttomPane.widthProperty().addListener(listenerButtomPane);
        buttomPane.heightProperty().addListener(listenerButtomPane);

        getChildren().addAll(saleListViewer,jumpToPane);
        saleListViewer.getChildren().addAll(grid,buttomPane);
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

    private void findPageBounds() {
        //clear all Bounds
        sideBoundsEnd.clear();
        sideBoundsBegin.clear();
        //get All numbers that we need
        int itemsPerPage=getMaxPanesCount();
        int saleListSize = Main.tempStorage.saleList.size();

        //System.out.println("items per Page: "+itemsPerPage+"\nAll Items: "+customerListSize);

        //set Page if only one page is available
        if (saleListSize <= itemsPerPage) {
            sideCount = 1;
            sideBoundsBegin.add(0);
            sideBoundsEnd.add(saleListSize-1);
        } else {
            //get Side's
            double a = (double) saleListSize / itemsPerPage;
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
                if (end > saleListSize - 1) {
                    end = saleListSize - 1;
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

    public int getColumnCount(double availableWidth) {
        availableWidth = availableWidth -20 -paneWidth;
        double a= availableWidth/(paneWidth);
        return (((int) a)+1);
    }
    //no failure
    public int getRowCount(double availableHeight) {
        availableHeight = availableHeight -paneHeight;
        double a= availableHeight/(paneHeight);
        return (((int) a)+1);
    }
    //no failure
    public int getMaxPanesCount() {
        int b=getColumnCount(saleListViewer.getWidth());
        int c=getRowCount(saleListViewer.getHeight());
        int a = b*c;
        //System.out.println("get Max: "+a+"   get S: "+b+"   get R: "+c);
        if(a<=2){
            return 2;
        }else{
            return a;
        }
    }

    public void setBoundsOfCustomerIndex(int begin, int end){
        this.boundsOfBegin = begin;
        this.boundsOfEnd = end;
        boundsOfBeginProperty.set(begin);
        boundsOfEndProperty.set(end);
    }

    public void setSide(){
        setBoundsOfCustomerIndex(sideBoundsBegin.get(sideOnViewer - 1), sideBoundsEnd.get(sideOnViewer - 1));
        int row = getRowCount(getWidth());
        int column = getColumnCount(getHeight());
        
        grid.getChildren().clear();
        int p=0;
        outerloop:
        for(int r=0;r<row;r++){
            for(int c=0;c<column;c++){
                if(boundsOfBegin+p<=boundsOfEnd) {
                    p++;
                    grid.add(new SaleSidePane(Main.tempStorage.saleList.get(boundsOfBegin + r * c)), r, c);
                }else{
                    break outerloop;
                }

            }
        }
    }

    /*
    private void setSide(char a) {
        switch (a) {
            //function to set next side
            case '+':
                if (sideOnViewer < sideCount) {
                    sideOnViewer++;
                    currentSide.setText(String.valueOf(sideOnViewer));
                    table.setBoundsOfSaleIndex(sideBoundsBegin.get(sideOnViewer - 1), sideBoundsEnd.get(sideOnViewer - 1));
                }
                break;
            //function to set last side
            case '-':
                if (sideOnViewer > 1) {
                    sideOnViewer--;
                    currentSide.setText(String.valueOf(sideOnViewer));
                    table.setBoundsOfSaleIndex(sideBoundsBegin.get(sideOnViewer - 1), sideBoundsEnd.get(sideOnViewer - 1));
                }
                break;
            //function to set first side
            case 'F':
                sideOnViewer = 1;
                currentSide.setText(String.valueOf(sideOnViewer));
                table.setBoundsOfSaleIndex(sideBoundsBegin.get(sideOnViewer - 1), sideBoundsEnd.get(sideOnViewer - 1));
                break;
            //function to set last side
            case 'L':
                sideOnViewer = sideCount;
                currentSide.setText(String.valueOf(sideOnViewer));
                table.setBoundsOfSaleIndex(sideBoundsBegin.get(sideOnViewer - 1), sideBoundsEnd.get(sideOnViewer - 1));
                break;
            //function to set sides +10 or first side
            case '1':
                if ((sideOnViewer + 10) < sideCount) {
                    sideOnViewer = sideOnViewer + 10;
                    currentSide.setText(String.valueOf(sideOnViewer));
                    table.setBoundsOfSaleIndex(sideBoundsBegin.get(sideOnViewer - 1), sideBoundsEnd.get(sideOnViewer - 1));
                } else {
                    setSide('L');
                }
                break;
            //function to set sides -10 or last side
            case '0':
                if ((sideOnViewer - 10) > 1) {
                    sideOnViewer = sideOnViewer - 10;
                    currentSide.setText(String.valueOf(sideOnViewer));
                    table.setBoundsOfSaleIndex(sideBoundsBegin.get(sideOnViewer - 1), sideBoundsEnd.get(sideOnViewer - 1));
                } else {
                    setSide('F');
                }
                break;

        }
    }

     */

    private boolean hasDecimal(double number) {
        return number % 1 != 0;
    }
}
