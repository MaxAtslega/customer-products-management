package dev.atslega.cpmb.repository;

import dev.atslega.cpmb.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByCompanyId(Long companyId, Pageable pageable);

    Optional<Order> findFirstByCompanyIdOrderByCreatedAtDesc(Long companyId);

    long countByCompanyId(Long id);
}