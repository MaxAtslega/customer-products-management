package dev.atslega.cpmb.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequest {

    @NotNull(message = "{required.field}")
    private Long customer;

    @NotNull(message = "{required.field}")
    private Long[] products;
}
