package dev.atslega.cpmb.controller;

import dev.atslega.cpmb.dto.CompanyResponse;
import dev.atslega.cpmb.service.CompanyService;
import dev.atslega.cpmb.util.EmailAuthenticationToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
@Tag(name = "Company", description = "Endpoints for getting information about the company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/")
    @RolesAllowed({"ADMIN", "USER"})
    @Operation(summary = "Company Information",
            description = "Fetch information about the company.")
    public ResponseEntity<CompanyResponse> getCompany() {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(companyService.getCompanyInformationById(authentication.getCompany()));
    }
}
