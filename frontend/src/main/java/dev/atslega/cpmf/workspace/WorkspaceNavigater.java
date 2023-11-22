package dev.atslega.cpmf.workspace;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class WorkspaceNavigater extends Pane {

    private Button lastClickedButton = null;
    private Button bt1,bt2,bt3,bt4,bt5,bt6;

    public WorkspaceNavigater (){
        int prefWight = 200;
        setStyle("-fx-background-color: linear-gradient(to bottom right, blue, white);");
        setPrefSize(prefWight,300);
        //Images for icons
        Image homeIcon = new Image(getClass().getResourceAsStream("Images/homeButtonIcon.png"));
        Image customersIcon = new Image(getClass().getResourceAsStream("Images/customersButtonIcon.png"));
        Image productsIcon = new Image(getClass().getResourceAsStream("Images/productsButtonIcon.png"));
        Image soldlistIcon = new Image(getClass().getResourceAsStream("Images/soldlistButtonIcon.png"));
        Image shortsIcon = new Image(getClass().getResourceAsStream("Images/shortButtonIcon.png"));
        Image calendarIcon = new Image(getClass().getResourceAsStream("Images/calendarButtonIcon.png"));
        // var of width of the border of buttons
        int leftAndRight = 4;
        int outSide = 4;
        int between = 2;
        //Buttons
        bt1 = creatButton("Home",prefWight,40,0,0,homeIcon,-50,0, outSide, leftAndRight, between, leftAndRight);
        bt2 = creatButton("Customers",prefWight,40,0,44.5,customersIcon, -38,1,between, leftAndRight, between, leftAndRight);
        bt3 = creatButton("Products",prefWight,40,0,87.3,productsIcon, -42,0,between, leftAndRight, between, leftAndRight);
        bt4 = creatButton("Sale List",prefWight,40,0,130,soldlistIcon,-46,0,between,leftAndRight,between,leftAndRight);
        bt5 = creatButton("Shorts",prefWight,40,0,173,shortsIcon,-49,1 ,between, leftAndRight, between, leftAndRight);
        bt6 = creatButton("Calendar",prefWight,40,0,216,calendarIcon, -42,1,between, leftAndRight, outSide, leftAndRight);
        //set first text on HomeButton
        bt1.setText(getButtonText(bt1));
        adjustIconPosition(bt1,-50,0);
        lastClickedButton = bt1;

        getChildren().addAll(bt1, bt2, bt3, bt4, bt5, bt6);
    }

    private Button creatButton (String text, int width, int height, int x, double y, Image icon,int imageX,int imageY, int top, int right, int bottom, int left){
        Button bt = new Button();
        bt.setPrefWidth(width);
        bt.setPrefHeight(height);
        bt.setLayoutX(x);
        bt.setLayoutY(y);
        bt.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-background-color: transparent; -fx-border-color: rgba(0, 0, 0, 0.3); -fx-border-width: " + top + " " + right + " " + bottom + " " + left);
        bt.setOnMouseEntered(event -> {
            if(!bt.equals(lastClickedButton)) {
                bt.setText(text);
                adjustIconPosition(bt, imageX, imageY);
            }
        });

        bt.setOnMouseExited(event -> {
            if(!bt.equals(lastClickedButton)) {
                bt.setText("");
                adjustIconPosition(bt, 0, 0);
            }

        });

        bt.setOnMouseClicked(event -> handleButtonClick(bt));

        ImageView imageView = new ImageView(icon);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        bt.setGraphic(imageView);

        imageView.setTranslateX(imageX);
        imageView.setTranslateY(imageY);
        adjustIconPosition(bt, 0, 0);

        return bt;
    }

    private void adjustIconPosition(Button button, double imageX, double imageY) {
        ImageView imageView = (ImageView) button.getGraphic();
        imageView.setTranslateX(imageX);
        imageView.setTranslateY(imageY);
    }

    private void handleButtonClick(Button clickedButton) {
        if (lastClickedButton != null && !lastClickedButton.equals(clickedButton)) {
            lastClickedButton.setText(getButtonText(lastClickedButton));
            lastClickedButton.setText("");
            adjustIconPosition(lastClickedButton, 0, 0);
            if(clickedButton.equals(bt1)){
                WorkspacePattern.setCenter(1);
            }else if(clickedButton.equals(bt2)){
                WorkspacePattern.setCenter(2);
            }else if(clickedButton.equals(bt3)){
                WorkspacePattern.setCenter(3);
            }else if(clickedButton.equals(bt4)){
                WorkspacePattern.setCenter(4);
            } else if (clickedButton.equals(bt5)) {
                WorkspacePattern.setCenter(5);
            }else if(clickedButton.equals(bt6)){
                WorkspacePattern.setCenter(6);
            }
        }
        lastClickedButton = clickedButton;
        clickedButton.setText(getButtonText(clickedButton));
    }

    private String getButtonText(Button button) {
        if (button.equals(bt1)) {
            return "Home";
        } else if (button.equals(bt2)) {
            return "Customers";
        } else if (button.equals(bt3)) {
            return "Products";
        } else if (button.equals(bt4)) {
            return "Soldlist";
        } else if (button.equals(bt5)) {
            return "Shorts";
        }else if(button.equals(bt6)){
            return "Calendar";
        } else {
            return "";
        }
    }
}

