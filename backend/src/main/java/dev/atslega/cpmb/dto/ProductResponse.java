package dev.atslega.cpmb.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ProductResponse {

    private String productName;

    private String category;

    private long price;

    private int stockQuantity;

    private String manufacturer;
}
