package dev.atslega.cpmb.repository;

import dev.atslega.cpmb.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
