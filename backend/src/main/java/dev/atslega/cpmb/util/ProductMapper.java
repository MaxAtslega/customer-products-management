package dev.atslega.cpmb.util;

import dev.atslega.cpmb.dto.ProductRequest;
import dev.atslega.cpmb.dto.ProductResponse;
import dev.atslega.cpmb.model.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final ModelMapper mapper;

    public Product toModel(ProductRequest request) {
        return mapper.map(request, Product.class);
    }

    public ProductRequest toRequest(Product product) {
        return mapper.map(product, ProductRequest.class);
    }

    public ProductResponse toResponse(Product product) {
        return mapper.map(product, ProductResponse.class);
    }
}