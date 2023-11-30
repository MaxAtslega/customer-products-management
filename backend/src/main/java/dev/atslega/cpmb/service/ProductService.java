package dev.atslega.cpmb.service;

import dev.atslega.cpmb.exception.ResourceNotFoundException;
import dev.atslega.cpmb.model.Product;
import dev.atslega.cpmb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(Long companyId) {
        return productRepository.findAll().stream().filter(c -> Objects.equals(c.getCompany().getId(), companyId)).toList();
    }

    public Product getProductById(Long id, Long companyId) {
        abortIfProductDoesNotExist(id, companyId);
        return productRepository.findById(id).filter(c -> Objects.equals(c.getCompany().getId(), companyId)).orElse(null);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id, Long companyId) {
        abortIfProductDoesNotExist(id, companyId);
        productRepository.deleteById(id);
    }

    public Product updateProduct(Product product, Long companyId) {
        abortIfProductDoesNotExist(product.getId(), companyId);
        return productRepository.save(product);
    }

    private void abortIfProductDoesNotExist(Long id, Long companyId) {
        productRepository.findById(id).filter(c -> Objects.equals(c.getCompany().getId(), companyId)).orElseThrow(() -> new ResourceNotFoundException(Product.class.getSimpleName(), id));
    }
}
