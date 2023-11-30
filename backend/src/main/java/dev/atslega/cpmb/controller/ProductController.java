package dev.atslega.cpmb.controller;

import dev.atslega.cpmb.dto.*;
import dev.atslega.cpmb.exception.ResourceNotFoundException;
import dev.atslega.cpmb.model.Customer;
import dev.atslega.cpmb.model.Product;
import dev.atslega.cpmb.model.User;
import dev.atslega.cpmb.service.CompanyService;
import dev.atslega.cpmb.service.ProductService;
import dev.atslega.cpmb.util.EmailAuthenticationToken;
import dev.atslega.cpmb.util.ProductMapper;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/")
    @RolesAllowed( {"ADMIN","USER"})
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        List<Product> products = productService.getAllProducts(authentication.getCompany());
        var resp = products.stream().map(productMapper::toResponse).toList();

        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    @RolesAllowed( {"ADMIN","USER"})
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Product product = productService.getProductById(id, authentication.getCompany()).orElse(null);
        var resp = productMapper.toResponse(product);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed( {"ADMIN","USER"})
    public ResponseEntity deleteProductById(@PathVariable Long id) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        productService.deleteProduct(id, authentication.getCompany());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @RolesAllowed({"ADMIN", "USER"})
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
    @RolesAllowed( {"ADMIN","USER"})
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest request) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        var product = productMapper.toModel(request);
        product.setCompany(companyService.getCompanyById(authentication.getCompany()).orElse(null));

        product = productService.saveProduct(product);
        var resp = productMapper.toResponse(product);

        return ResponseEntity.created(URI.create(product.getId().toString())).body(resp);
    }
}