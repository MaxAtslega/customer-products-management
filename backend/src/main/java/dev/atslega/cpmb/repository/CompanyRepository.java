package dev.atslega.cpmb.repository;

import dev.atslega.cpmb.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByCompanyName(String companyName);
}
