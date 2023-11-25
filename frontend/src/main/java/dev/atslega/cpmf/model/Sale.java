package dev.atslega.cpmf.model;

public class Sale {
    private Product product;
    private Customer customer;
    private String iD;
    private double taxPerHundred;
    private int discount;
    private double pricePerPiece;
    private int numberOfPieces;
    private double totalPrice;

    public Sale(Product product, Customer customer, String iD, Double taxPerHundred, int discount, Double pricePerPiece, int numberOfPieces, Double totalPrice) {
        this.product = product;
        this.customer = customer;
        this.iD = iD;
        this.taxPerHundred = taxPerHundred;
        this.discount = discount;
        this.pricePerPiece = pricePerPiece;
        this.numberOfPieces = numberOfPieces;
        this.totalPrice = totalPrice;
    }

    public Sale(Product product, Customer customer, String iD, Double taxPerHundred, int discount, Double pricePerPiece, int numberOfPieces) {
        this.product = product;
        this.customer = customer;
        this.iD = iD;
        this.taxPerHundred = taxPerHundred;
        this.discount = discount;
        this.pricePerPiece = pricePerPiece;
        this.numberOfPieces = numberOfPieces;
    }

    public Sale(Product product, Customer customer, String iD, Double taxPerHundred, Double pricePerPiece, int numberOfPieces) {
        this.product = product;
        this.customer = customer;
        this.iD = iD;
        this.taxPerHundred = taxPerHundred;
        this.pricePerPiece = pricePerPiece;
        this.numberOfPieces = numberOfPieces;
    }

    public Sale(Product product, Customer customer, String iD, Double pricePerPiece, int numberOfPieces, Double totalPrice) {
        this.product = product;
        this.customer = customer;
        this.iD = iD;
        this.pricePerPiece = pricePerPiece;
        this.numberOfPieces = numberOfPieces;
        this.totalPrice = totalPrice;
    }

    public Sale(Product product, Customer customer, String iD, int discount, Double pricePerPiece, int numberOfPieces, Double totalPrice) {
        this.product = product;
        this.customer = customer;
        this.iD = iD;
        this.discount = discount;
        this.pricePerPiece = pricePerPiece;
        this.numberOfPieces = numberOfPieces;
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getNumberOfPieces() {
        return numberOfPieces;
    }

    public void setNumberOfPieces(int numberOfPieces) {
        this.numberOfPieces = numberOfPieces;
    }

    public double getPricePerPiece() {
        return pricePerPiece;
    }

    public void setPricePerPiece(double pricePerPiece) {
        this.pricePerPiece = pricePerPiece;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getTaxPerHundred() {
        return taxPerHundred;
    }

    public void setTaxPerHundred(double taxPerHundred) {
        this.taxPerHundred = taxPerHundred;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
