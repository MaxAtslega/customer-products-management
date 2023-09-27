package dev.atslega.cpmb.repository;

import dev.atslega.cpmb.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}