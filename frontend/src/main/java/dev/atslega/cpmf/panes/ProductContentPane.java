package dev.atslega.cpmf.panes;

import dev.atslega.cpmf.model.Product;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Pane;

public class ProductContentPane extends Pane {

    private final Product product;

    boolean isFlipped=false, infoIconClicked=false;

    public ProductContentPane(Product product){
        setPrefSize(200,220);
        setStyle("-fx-background-radius: 5; -fx-background-color: #18191C;");
        setCursor(Cursor.HAND);
        this.product = product;

        //Label in front (name, Id , inventory,price)
        Label nameF = creatLabelWithOutCopyAction("Name:",10);
        Label nameFront = creatLabelWithOutCopyAction(product.getName(), 30);
        Label iDF = creatLabelWithOutCopyAction("ID:",50);
        Label iDFront = creatLabelWithOutCopyAction(product.getID(), 70);
        Label inventoryF = creatLabelWithOutCopyAction("Inventory:",90);
        Label inventoryFront = creatLabelWithOutCopyAction(String.valueOf(product.getInventory()),110);
        Label priceF = creatLabelWithOutCopyAction("Price:",130);
        Label priceFront = creatLabelWithOutCopyAction(String.valueOf(product.getPrice()),150);
        //Label in back (all but without description)
        Label nameB = creatLabel("Name: "+product.getName(),10);
        nameB.setVisible(false);
        Label iDB = creatLabel("ID: "+product.getID(),30);
        iDB.setVisible(false);
        Label manufacturerB = creatLabel("Manufacturer: \n"+product.getManufacturer(),50);
        manufacturerB.setVisible(false);
        Label inventoryB = creatLabel("Inventory: "+product.getInventory(),90);
        inventoryB.setVisible(false);
        Label priceB = creatLabel("Price: "+product.getPrice()+"€",110);
        priceB.setVisible(false);
        //Label for description
        Label descriptionI = creatLabel("Description: "+product.getDescription(),10);
        descriptionI.setWrapText(true);
        descriptionI.setVisible(false);

        Image infoIcon = new Image(getClass().getResourceAsStream("Images/infoIcon.png"));
        ImageView infoIconView = new ImageView(infoIcon);
        infoIconView.setLayoutY(190);
        infoIconView.setLayoutX(170);
        infoIconView.setOnMouseClicked(event -> {
            if(infoIconClicked){
                descriptionI.setVisible(false);

                nameB.setVisible(true);
                iDB.setVisible(true);
                manufacturerB.setVisible(true);
                inventoryB.setVisible(true);
                priceB.setVisible(true);
                infoIconClicked= false;
                System.out.println("Contant del from Pane");
            }else{
                nameB.setVisible(false);
                iDB.setVisible(false);
                manufacturerB.setVisible(false);
                inventoryB.setVisible(false);
                priceB.setVisible(false);

                descriptionI.setVisible(true);
                infoIconClicked = true;
                System.out.println("Contant set to Pane");
            }
        });
        getChildren().add(infoIconView);

        setOnMouseEntered(event -> {
            if(!isFlipped){
                nameF.setVisible(false);
                nameFront.setVisible(false);
                iDF.setVisible(false);
                iDFront.setVisible(false);
                inventoryF.setVisible(false);
                inventoryFront.setVisible(false);
                priceF.setVisible(false);
                priceFront.setVisible(false);
                //set back true
                nameB.setVisible(true);
                iDB.setVisible(true);
                manufacturerB.setVisible(true);
                inventoryB.setVisible(true);
                priceB.setVisible(true);
                isFlipped=true;
                setStyle("-fx-background-radius: 5; -fx-background-color: #202225;");
            }
        });

        setOnMouseExited(event -> {
            if(isFlipped){
                nameF.setVisible(true);
                nameFront.setVisible(true);
                iDF.setVisible(true);
                iDFront.setVisible(true);
                inventoryF.setVisible(true);
                inventoryFront.setVisible(true);
                priceF.setVisible(true);
                priceFront.setVisible(true);
                // del back
                nameB.setVisible(false);
                iDB.setVisible(false);
                manufacturerB.setVisible(false);
                inventoryB.setVisible(false);
                priceB.setVisible(false);

                descriptionI.setVisible(false);

                isFlipped=false;
                setStyle("-fx-background-radius: 5; -fx-background-color: #18191C;");
            }
        });
        //add front
        getChildren().addAll(nameF,nameFront,iDF,iDFront,inventoryF,inventoryFront,priceF,priceFront);
        //add back
        getChildren().addAll(nameB,iDB,manufacturerB,inventoryB,priceB);
        //add info
        getChildren().add(descriptionI);

    }

    private Label creatLabelWithOutCopyAction(String text, int y){
        Label label = new Label();
        label.setText(text);
        label.setLayoutX(10);
        label.setLayoutY(y);
        label.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: #ffffff; -fx-alignment: center;");
        label.setMaxWidth(180);
        label.setMinWidth(180);

        return label;
    }

    private Label creatLabel (String text, int y){
        Label label = new Label();
        label.setText(text);
        label.setLayoutX(10);
        label.setLayoutY(y);
        label.setStyle("-fx-font-size: 14; -fx-text-fill: #ffffff;");
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

    public Product getProduct(){
        return product;
    }

}
