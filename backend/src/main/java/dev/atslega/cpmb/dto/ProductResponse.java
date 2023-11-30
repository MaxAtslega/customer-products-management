package dev.atslega.cpmb.dto;

import lombok.Data;

@Data
public class ProductResponse {

    private Long id;

    private String productName;

    private String category;

    private long price;

    private int stockQuantity;

    private String manufacturer;
}
