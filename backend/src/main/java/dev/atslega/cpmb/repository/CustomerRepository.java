package dev.atslega.cpmb.repository;

import dev.atslega.cpmb.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Page<Customer> findByCompanyId(Long companyId, Pageable pageable);
}
