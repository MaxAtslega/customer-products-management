package dev.atslega.cpmb.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderResponse {

    private Long id;

    private Long customer;

    private ProductResponse[] products;
}
