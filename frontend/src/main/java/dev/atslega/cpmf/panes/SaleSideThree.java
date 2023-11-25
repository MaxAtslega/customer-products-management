package dev.atslega.cpmf.panes;

import dev.atslega.cpmf.AppStyles;
import dev.atslega.cpmf.model.Customer;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Pane;

public class SaleSideThree extends Pane {

    Label name,iD,mail,phoneNumber,address,companyName;
    boolean customerIsCompany;

    public SaleSideThree(Customer customer){
        setStyle("-fx-background-radius: 15; -fx-background-color: #FF6A6A;");
        setPrefSize(AppStyles.BOX_SIZE_W,AppStyles.BOX_SIZE_H);

        name = creatLabel("Name: " + customer.getLastName() + " " + customer.getFirstName(), 10);
        iD = creatLabel("ID: " + customer.getID(), 30);
        mail = creatLabel("Mail: "+customer.getMail(), 50);
        phoneNumber = creatLabel("Phonenumber: \n"+customer.getPhoneNumber(), 70);
        address = creatLabel("Address: "+customer.getAddress(), 110);
        companyName = new Label();
        companyName.setLayoutX(10);
        companyName.setLayoutY(170);
        companyName.setMaxWidth(160);
        companyName.setMinWidth(160);
        if(customer.getCompanyName().compareTo("")==0){
            companyName.setText("Company: \nPrivate Customer");
            customerIsCompany = true;
        }else{
            companyName.setText("Company: \n"+customer.getCompanyName());
            customerIsCompany = false;
        }
        companyName.setOnMouseClicked(event -> {
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

        getChildren().addAll(name,iD,mail,phoneNumber,address,companyName);
    }


    private Label creatLabel (String text, int y){
        Label label = new Label();
        label.setText(text);
        label.setLayoutX(10);
        label.setLayoutY(y);
        label.setStyle("-fx-font-size: 14; -fx-text-fill: black;");
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
}
