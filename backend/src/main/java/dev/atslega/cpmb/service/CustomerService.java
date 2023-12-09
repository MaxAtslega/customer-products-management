package dev.atslega.cpmb.service;

import dev.atslega.cpmb.exception.ResourceNotFoundException;
import dev.atslega.cpmb.model.Customer;
import dev.atslega.cpmb.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers(Long companyId, Integer size, Integer pageNumber) {
        Pageable page = Pageable.ofSize(size).withPage(pageNumber);
        return customerRepository.findAll(page).stream().filter(c -> Objects.equals(c.getCompany().getId(), companyId)).toList();
    }

    public Customer getCustomerById(Long id, Long companyId) {
        abortIfCustomerDoesNotExist(id, companyId);
        return customerRepository.findById(id).filter(c -> Objects.equals(c.getCompany().getId(), companyId)).orElse(null);
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id, Long companyId) {
        abortIfCustomerDoesNotExist(id, companyId);
        customerRepository.deleteById(id);
    }

    public Customer updateCustomer(Customer customer, Long companyId) {
        abortIfCustomerDoesNotExist(customer.getId(), companyId);
        return customerRepository.save(customer);
    }

    private void abortIfCustomerDoesNotExist(Long id, Long companyId) {
        customerRepository.findById(id).filter(c -> Objects.equals(c.getCompany().getId(), companyId)).orElseThrow(() -> new ResourceNotFoundException(Customer.class.getSimpleName(), id));
    }
}