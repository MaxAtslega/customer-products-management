package dev.atslega.cpmf.model;

public class Product {
    private String name;
    private String iD;
    private String manufacturer;
    private String description;
    private int inventory;
    private double price;

    public Product(String name, String iD, String manufacturer, String description, int inventory, double price) {
        this.name = name;
        this.iD = iD;
        this.manufacturer = manufacturer;
        this.description = description;
        this.inventory = inventory;
        this.price = price;
    }

    // Getter-Methoden
    public String getName() {
        return name;
    }

    // Setter-Methoden
    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

