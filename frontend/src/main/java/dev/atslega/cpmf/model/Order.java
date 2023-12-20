package dev.atslega.cpmf.model;

import java.util.Arrays;

public class Order {

    private Long id;
    private Customer customer;
    private Product[] products;

    public Order() {
    }

    public Order(Long id, Customer customer, Product[] products) {
        this.id = id;
        this.customer = customer;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", products=" + Arrays.toString(products) +
                '}';
    }
}
