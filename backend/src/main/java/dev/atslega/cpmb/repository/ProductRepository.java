package dev.atslega.cpmb.repository;

import dev.atslega.cpmb.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
