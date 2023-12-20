package dev.atslega.cpmb.repository;

import dev.atslega.cpmb.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Page<Customer> findByCompanyId(Long companyId, Pageable pageable);

    Optional<Customer> findFirstByCompanyIdOrderByCreatedAtDesc(Long companyId);

    long countByCompanyId(Long id);
}
