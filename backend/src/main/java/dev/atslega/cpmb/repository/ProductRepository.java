package dev.atslega.cpmb.repository;

import dev.atslega.cpmb.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCompanyId(Long companyId, Pageable pageable);

    Optional<Product> findFirstByCompanyIdOrderByCreatedAtDesc(Long companyId);

    long countByCompanyId(Long id);
}
