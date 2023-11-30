package dev.atslega.cpmb.service;

import dev.atslega.cpmb.exception.ResourceAlreadyExistsException;
import dev.atslega.cpmb.model.Company;
import dev.atslega.cpmb.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
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
}
