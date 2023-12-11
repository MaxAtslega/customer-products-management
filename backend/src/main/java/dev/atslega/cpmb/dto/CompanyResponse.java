package dev.atslega.cpmb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {
    private String companyName;
    private String companyAddress;

    private ProductResponse latestProduct;
    private CustomerResponse latestCustomer;
    private OrderResponse latestOrder;

    private long customerCount;
    private long orderCount;
    private long productCount;
    private long userCount;
}
