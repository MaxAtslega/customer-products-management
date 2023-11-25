package dev.atslega.cpmf.panes;

import dev.atslega.cpmf.AppStyles;
import dev.atslega.cpmf.model.Product;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Pane;

public class SaleSideFour extends Pane {

    boolean infoIconClicked = false;

    Label name,iD,manufacturer,inventory,price,descriptionI;
    ImageView infoIconView;

    public SaleSideFour(Product product){
        setStyle("-fx-background-radius: 15; -fx-background-color: #FF6A6A;");
        setPrefSize(AppStyles.BOX_SIZE_W,AppStyles.BOX_SIZE_H);

        name = creatLabel("Name: "+product.getName(),10);
        iD = creatLabel("ID: "+product.getID(),30);
        manufacturer = creatLabel("Manufacturer: \n"+product.getManufacturer(),50);
        inventory = creatLabel("Inventory: "+product.getInventory(),90);
        price = creatLabel("Price: "+product.getPrice()+"€",110);
        //Label for description
        descriptionI = creatLabel("Description: "+product.getDescription(),10);
        descriptionI.setWrapText(true);
        descriptionI.setVisible(false);

        Image infoIcon = new Image(getClass().getResourceAsStream("Images/infoIcon.png"));
        infoIconView = new ImageView(infoIcon);
        infoIconView.setLayoutY(190);
        infoIconView.setLayoutX(170);
        infoIconView.setOnMouseClicked(event -> {
            if(infoIconClicked){
                descriptionI.setVisible(false);

                name.setVisible(true);
                iD.setVisible(true);
                manufacturer.setVisible(true);
                inventory.setVisible(true);
                price.setVisible(true);
                infoIconClicked= false;
                System.out.println("Contant del from Pane");
            }else{
                name.setVisible(false);
                iD.setVisible(false);
                manufacturer.setVisible(false);
                inventory.setVisible(false);
                price.setVisible(false);

                descriptionI.setVisible(true);
                infoIconClicked = true;
                System.out.println("Contant set to Pane");
            }
        });
        getChildren().add(infoIconView);
        getChildren().addAll(name,iD,manufacturer,inventory,price);
        //add info
        getChildren().add(descriptionI);

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
