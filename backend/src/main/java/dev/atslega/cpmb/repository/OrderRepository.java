package dev.atslega.cpmb.repository;

import dev.atslega.cpmb.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}