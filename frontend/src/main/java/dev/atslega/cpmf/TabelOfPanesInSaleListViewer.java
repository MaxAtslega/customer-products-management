package dev.atslega.cpmf;

import dev.atslega.cpmf.panes.CustomerSidePane;
import dev.atslega.cpmf.panes.SaleSidePane;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class TabelOfPanesInSaleListViewer extends Pane {
    private final ArrayList<SaleSidePane> paneList = new ArrayList<>();

    private int boundsOfBegin =0;
    private int boundsOfEnd =9;

    private IntegerProperty boundsOfBeginProperty = new SimpleIntegerProperty(boundsOfBegin);
    private IntegerProperty boundsOfEndProperty = new SimpleIntegerProperty(boundsOfEnd);

    private final int paneWidth = 200;
    private final int paneHeight = 220;
    private final int spacing = 10;

    public TabelOfPanesInSaleListViewer(){
        setMinSize(410,220);
        setStyle("-fx-background-color: transparent");

        for(int i=0; i<2;i++){
            SaleSidePane pane = new SaleSidePane(Main.tempStorage.saleList.get(i));
            addPane(pane);
        }

        boundsOfBeginProperty.addListener((observable, oldValue, newValue) -> {
            getAllSaleInBounds();
            //System.out.println("BoundsOfBegin has changed. New value is: " + newValue);
        });

        boundsOfEndProperty.addListener((observable, oldValue, newValue) -> {
            getAllSaleInBounds();
            //System.out.println("BoundsOfEnd has changed. New value is: " + newValue);
        });
    }


    public void addPane( SaleSidePane pane) {
        paneList.add(pane);
        getChildren().add(pane);
    }

    //no failure
    public int getColumnCount(double availableWidth) {
        availableWidth = availableWidth -paneWidth;
        double a= availableWidth/(paneWidth+spacing);
        return (((int) a)+1);
    }
    //no failure
    public int getRowCount(double availableHeight) {
        availableHeight = availableHeight -paneHeight;
        double a= availableHeight/(paneHeight+spacing);
        return (((int) a)+1);
    }
    //no failure
    public int getMaxPanesCount() {
        int b=getColumnCount(getWidth());
        int c=getRowCount(getHeight());
        int a = b*c;
        //System.out.println("get Max: "+a+"   get S: "+b+"   get R: "+c);
        if(a<=2){
            return 2;
        }else{
            return a;
        }
    }

    public void setPanesPosition() {
        int columnCount = getColumnCount(getWidth());
        int rowCount = getRowCount(getHeight());
        //p is the amount of Panes what the function did set at the mainPane
        int p=0;

        outerloop:
        //for every row
        for(int r =0; r<rowCount;r++){
            //in row set for every column a Pane from Pane List
            for(int c= 0;c<columnCount;c++){
                //System.out.println("Row: "+r+"  Y: "+getRowLayoutY(r)+"\nColumn: "+c+"  X: "+getColumnLayoutX(c));
                //if all Panes was set on main Pane break function loop
                if(p == paneList.size()){
                    break outerloop;
                    //set secound Pane to Last Pane in MainPane
                } else if(p!=0){
                    paneList.get(p).setLayoutY(getRowLayoutY(r));
                    paneList.get(p).setLayoutX(getColumnLayoutX(c));
                    p++;
                    //set First Pane in main Pane
                } else if (p==0) {
                    paneList.get(p).setLayoutY(0);
                    paneList.get(p).setLayoutX(0);
                    p++;
                }
            }
        }
    }
    //no failure
    private int getColumnLayoutX(int columnCount){
        int layoutX=0;
        layoutX = columnCount *(paneWidth+spacing);
        return layoutX;
    }
    //no failure
    private int getRowLayoutY(int rowCount){
        int layoutY=0;
        layoutY = rowCount *(paneHeight+spacing);
        return layoutY;
    }

    public void setBoundsOfSaleIndex(int begin, int end){
        this.boundsOfBegin = begin;
        this.boundsOfEnd = end;
        boundsOfBeginProperty.set(begin);
        boundsOfEndProperty.set(end);
    }
    //no failure
    public void getAllSaleInBounds(){
        //clear added Panes From main Pane
        paneList.clear();
        getChildren().clear();
        //get All customers convert to Panes and add them to mainPane
        for(int i=boundsOfBegin;i<=boundsOfEnd;i++){
            if(i<Main.tempStorage.saleList.size()){
                addPane(new SaleSidePane(Main.tempStorage.saleList.get(i)));
            }
        }
        //set All the Layout X and Y of Panes
        setPanesPosition();
    }

}
