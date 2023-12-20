package dev.atslega.cpmb.controller;

import dev.atslega.cpmb.dto.ErrorResponse;
import dev.atslega.cpmb.dto.ProductRequest;
import dev.atslega.cpmb.dto.ProductResponse;
import dev.atslega.cpmb.model.Product;
import dev.atslega.cpmb.service.CompanyService;
import dev.atslega.cpmb.service.ProductService;
import dev.atslega.cpmb.util.EmailAuthenticationToken;
import dev.atslega.cpmb.util.ProductMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Product Management", description = "Endpoints for managing products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/")
    @RolesAllowed({"ADMIN", "USER"})
    @Operation(summary = "Retrieve All Products",
            description = "Fetch a list of all products, with options for pagination.")
    public ResponseEntity<List<ProductResponse>> getAllProducts(@RequestParam(value = "size", required = false, defaultValue = "3") Integer size,
                                                                @RequestParam(value = "page", required = false, defaultValue = "0") Integer pageNumber) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        List<Product> products = productService.getAllProducts(authentication.getCompany(), size, pageNumber);
        var resp = products.stream().map(productMapper::toResponse).toList();

        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ADMIN", "USER"})
    @Operation(summary = "Retrieve Product by ID",
            description = "Find and return a specific product using its unique ID.")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Product product = productService.getProductById(id, authentication.getCompany());
        var resp = productMapper.toResponse(product);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"ADMIN", "USER"})
    @Operation(summary = "Delete Product by ID",
            description = "Remove a product from the system using its unique ID. This operation is irreversible.")
    @ApiResponse(responseCode = "200", description = "Product successfully deleted")
    @ApiResponse(responseCode = "404", description = "Product not found",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    public ResponseEntity<Object> deleteProductById(@PathVariable Long id) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        productService.deleteProduct(id, authentication.getCompany());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @RolesAllowed({"ADMIN", "USER"})
    @Operation(summary = "Update Product",
            description = "Update the details of an existing product.")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") Long id, @RequestBody @Valid ProductRequest request) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        var product = productMapper.toModel(request);
        product.setId(id);
        product.setCompany(companyService.getCompanyById(authentication.getCompany()).orElse(null));
        product = productService.updateProduct(product, authentication.getCompany());
        var resp = productMapper.toResponse(product);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/")
    @RolesAllowed({"ADMIN", "USER"})
    @Operation(summary = "Create New Product",
            description = "Add a new product to the system.")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest request) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        var product = productMapper.toModel(request);
        product.setCompany(companyService.getCompanyById(authentication.getCompany()).orElse(null));

        product = productService.saveProduct(product);
        var resp = productMapper.toResponse(product);

        return ResponseEntity.created(URI.create(product.getId().toString())).body(resp);
    }
}