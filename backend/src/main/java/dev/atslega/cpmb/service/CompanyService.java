package dev.atslega.cpmb.service;

import dev.atslega.cpmb.dto.CompanyResponse;
import dev.atslega.cpmb.dto.OrderResponse;
import dev.atslega.cpmb.dto.ProductResponse;
import dev.atslega.cpmb.exception.ResourceAlreadyExistsException;
import dev.atslega.cpmb.model.Company;
import dev.atslega.cpmb.model.Order;
import dev.atslega.cpmb.repository.*;
import dev.atslega.cpmb.util.CustomerMapper;
import dev.atslega.cpmb.util.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private final CustomerMapper customerMapper;
    private final ProductMapper productMapper;


    private final PasswordEncoder passwordEncoder;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    public Company saveCompany(Company company) {
        if (companyRepository.existsByCompanyName(company.getCompanyName()))
            throw new ResourceAlreadyExistsException("Company", company.getCompanyName());
        return companyRepository.save(company);
    }

    public CompanyResponse getCompanyInformationById(Long id) {
        CompanyResponse companyResponse = new CompanyResponse();

        Company company = companyRepository.findById(id).orElse(null);
        if (company == null) return null;

        companyResponse.setCompanyName(company.getCompanyName());
        companyResponse.setCompanyAddress(company.getCompanyAddress());

        companyResponse.setCustomerCount(customerRepository.countByCompanyId(id));
        companyResponse.setOrderCount(orderRepository.countByCompanyId(id));
        companyResponse.setProductCount(productRepository.countByCompanyId(id));
        companyResponse.setUserCount(userRepository.countByCompanyId(id));

        companyResponse.setLatestCustomer(customerMapper.toResponse(customerRepository.findFirstByCompanyIdOrderByCreatedAtDesc(id)));
        companyResponse.setLatestOrder(mapOrdersToOrderResponse(orderRepository.findFirstByCompanyIdOrderByCreatedAtDesc(id)));
        companyResponse.setLatestProduct(productMapper.toResponse(productRepository.findFirstByCompanyIdOrderByCreatedAtDesc(id)));


        return companyResponse;
    }

    public OrderResponse mapOrdersToOrderResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setProducts(order.getProducts().stream().map(productMapper::toResponse).toArray(ProductResponse[]::new));
        orderResponse.setCustomer(order.getCustomer().getId());
        orderResponse.setId(order.getId());

        return orderResponse;
    }

}
