package com.company.CompaniesHouse.service;

import com.company.CompaniesHouse.model.Company;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface CompanyService {

    public Company createCompany(Company company);

    public List<Company> listCompanies();

    public Company getCompanyDetails(String name);

    public Company updateCompany(Company company);

    public Company addBeneficiaryOwners(String name, Set<String> beneficiaryOwners);
}
