package dev.atslega.cpmb.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "price", precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

    protected Product() {}

    public Product(String productName, String category, BigDecimal price, Integer stockQuantity, String manufacturer, String imageUrl, List<Order> orders) {
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.manufacturer = manufacturer;
        this.imageUrl = imageUrl;
        this.orders = orders;
    }
}
