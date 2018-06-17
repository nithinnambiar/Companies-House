package com.company.CompaniesHouse.dao;

import com.company.CompaniesHouse.model.Company;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CompanyDao {

    public Company createCompany(Company company);

    public List<Company> listCompanies();

    public Optional<Company> getCompanyDetails(String name);

    public Company updateCompany(Company company);

    public Company addBeneficiaryOwners(String name, Set<String> beneficiaryOwners);

    public void deleteAllCompanies();

}
