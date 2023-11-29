package dev.atslega.cpmb.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRequest {

    @NotEmpty(message = "{required.field}")
    private String productName;

    @NotEmpty(message = "{required.field}")
    private String category;

    @NotNull(message = "{required.field}")
    private long price;

    @NotNull(message = "{required.field}")
    private Integer stockQuantity;

    @NotEmpty(message = "{required.field}")
    private String manufacturer;
}
