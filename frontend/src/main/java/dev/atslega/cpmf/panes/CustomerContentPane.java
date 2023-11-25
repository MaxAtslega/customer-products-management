package dev.atslega.cpmf.panes;

import dev.atslega.cpmf.AppStyles;
import dev.atslega.cpmf.model.Customer;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class CustomerContentPane extends Pane {

    private boolean isFlipped = false,customerIsCompany = false;

    private final Customer customer;

    public CustomerContentPane(Customer customer) {
        setMaxSize(AppStyles.BOX_SIZE_W,AppStyles.BOX_SIZE_H);
        setMinSize(AppStyles.BOX_SIZE_W,AppStyles.BOX_SIZE_H);
        this.customer = customer;
        char firstChar = getFirstChar(customer.getFirstName());

        Label nameTextLabel = creatLabelWithOutCopyAction("Name:", 110);
        Label nameOfCustomer = creatLabelWithOutCopyAction(customer.getLastName()+ " " + customer.getFirstName(), 130);
        Label iDOfCustomer = creatLabelWithOutCopyAction("ID: " + customer.getID(), 150);
        Label mailTextLabel = creatLabelWithOutCopyAction("Mail:",170);
        Label mailOfCustomer = creatLabelWithOutCopyAction(customer.getMail(),190);

        // customer Icon
        Circle circle = new Circle(100, 60, 40, getRandomColor());
        circle.setStrokeWidth(3);
        getChildren().add(circle);

        Label firstCharLabel = new Label(String.valueOf(firstChar));
        firstCharLabel.setLayoutX(86-(firstCharLabel.getWidth()/2));
        firstCharLabel.setLayoutY(34-(firstCharLabel.getHeight()/2));
        firstCharLabel.setStyle("-fx-font-size: 36; -fx-text-fill: black; -fx-alignment: center;");

        Label backName = creatLabel("Name: " + customer.getLastName() + " " + customer.getFirstName(), 10);
        backName.setStyle("-fx-font-size: 14; -fx-text-fill: white;");
        backName.setVisible(false);
        Label backID = creatLabel("ID: " + customer.getID(), 30);
        backID.setStyle("-fx-font-size: 14; -fx-text-fill: white;");
        backID.setVisible(false);
        Label backMail = creatLabel("Mail: "+customer.getMail(), 50);
        backMail.setStyle("-fx-font-size: 14; -fx-text-fill: white;");
        backMail.setVisible(false);
        Label backPhoneNumber = creatLabel("Phonenumber: \n"+customer.getPhoneNumber(), 70);
        backPhoneNumber.setStyle("-fx-font-size: 14; -fx-text-fill: white;");
        backPhoneNumber.setVisible(false);
        Label backAdress = creatLabel("Address: "+customer.getAddress(), 110);
        backAdress.setStyle("-fx-font-size: 14; -fx-text-fill: white;");
        backAdress.setVisible(false);
        Label backCompanyName = new Label();
        backCompanyName.setLayoutX(10);
        backCompanyName.setLayoutY(170);
        backCompanyName.setMaxWidth(160);
        backCompanyName.setMinWidth(160);
        if(customer.getCompanyName().compareTo("")==0){
            backCompanyName.setText("Company: \nPrivate Customer");
            customerIsCompany = true;
        }else{
            backCompanyName.setText("Company: \n"+customer.getCompanyName());
            customerIsCompany = false;
        }
        backCompanyName.setStyle("-fx-font-size: 14; -fx-text-fill: white;");
        backCompanyName.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                ClipboardContent content = new ClipboardContent();
                if(!customerIsCompany) {
                    content.putString(customer.getCompanyName());
                }else{
                    content.putString("Private Customer");
                }
                Clipboard.getSystemClipboard().setContent(content);
            }
        });
        backCompanyName.setVisible(false);

        // Setzen des Styles für die Pane
        setStyle("-fx-background-radius: 15; -fx-background-color: white;");

        setOnMouseEntered(event -> {
            if (!isFlipped) {
                // set the visible of front false
                nameTextLabel.setVisible(false);
                nameOfCustomer.setVisible(false);
                iDOfCustomer.setVisible(false);
                mailTextLabel.setVisible(false);
                mailOfCustomer.setVisible(false);
                circle.setVisible(false);
                firstCharLabel.setVisible(false);
                // set the visible of back true
                backName.setVisible(true);
                backID.setVisible(true);
                backMail.setVisible(true);
                backPhoneNumber.setVisible(true);
                backAdress.setVisible(true);
                backCompanyName.setVisible(true);
                //set back style
                setStyle("-fx-background-radius: 15; -fx-background-color: black;");
                isFlipped = true;
            }
        });

        setOnMouseExited(event -> {
            if (isFlipped) {
                // set the visible of front true
                nameTextLabel.setVisible(true);
                nameOfCustomer.setVisible(true);
                iDOfCustomer.setVisible(true);
                mailTextLabel.setVisible(true);
                mailOfCustomer.setVisible(true);
                circle.setVisible(true);
                firstCharLabel.setVisible(true);
                //set the visible of back false
                backName.setVisible(false);
                backID.setVisible(false);
                backMail.setVisible(false);
                backPhoneNumber.setVisible(false);
                backAdress.setVisible(false);
                backCompanyName.setVisible(false);
                //set front style
                setStyle("-fx-background-radius: 15; -fx-background-color: white;");
                isFlipped = false;
            }
        });

        // add front
        getChildren().add(firstCharLabel);
        getChildren().addAll(nameTextLabel,nameOfCustomer, iDOfCustomer,mailTextLabel,mailOfCustomer);
        //add back
        getChildren().addAll(backName,backID,backMail, backPhoneNumber,backAdress,backCompanyName);
    }

    private Label creatLabelWithOutCopyAction(String text, int y){
        Label label = new Label();
        label.setText(text);
        label.setLayoutX(10);
        label.setLayoutY(y);
        label.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-alignment: center;");
        label.setMaxWidth(180);
        label.setMinWidth(180);

        return label;
    }

    private Label creatLabel (String text, int y){
        Label label = new Label();
        label.setText(text);
        label.setLayoutX(10);
        label.setLayoutY(y);
        label.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-alignment: center;");
        label.setMaxWidth(180);
        label.setMinWidth(180);

        // copy to clipboard by two clicks
        label.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                ClipboardContent content = new ClipboardContent();
                content.putString(getTextFormLabel(label.getText()));
                Clipboard.getSystemClipboard().setContent(content);
            }
        });

        return label;
    }

    private char getFirstChar(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input string is null or empty.");
        }
        return input.charAt(0);
    }
    private Color getRandomColor() {
        Random random = new Random();
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    private String getTextFormLabel(String text){
        char[] charArray = text.toCharArray();
        int spaceIndex = -1;
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == ' ') {
                spaceIndex = i;
                break;
            }
        }
        String a=charArrayToStringFromIndex(charArray,spaceIndex);
        return a;
    }

    private String charArrayToStringFromIndex(char[] charArray, int startIndex) {
        StringBuilder stringBuilder = new StringBuilder();
        int e=0;
        for (int i = startIndex; i < charArray.length; i++) {
            if(charArray[i]=='\n'){
                stringBuilder.append(' ');
            }else if(charArray[i] ==' '&& e==0){
                e++;
            }else if(charArray[i] ==' '&& e!=0){
                e++;
                stringBuilder.append(charArray[i]);
            }else{
                stringBuilder.append(charArray[i]);
            }
        }
        return stringBuilder.toString();
    }

    public Customer getCustomer(){
        return customer;
    }
}
