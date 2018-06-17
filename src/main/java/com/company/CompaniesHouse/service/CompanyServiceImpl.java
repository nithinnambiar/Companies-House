package com.company.CompaniesHouse.service;

import com.company.CompaniesHouse.dao.CompanyDao;
import com.company.CompaniesHouse.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyDao companyDao;
    @Override
    public Company createCompany(Company company) {
        return companyDao.createCompany(company);
    }

    @Override
    public List<Company> listCompanies() {
        return companyDao.listCompanies();
    }

    @Override
    public Company getCompanyDetails(String name) {


        Optional<Company> optional= companyDao.getCompanyDetails(name);

        if(optional.isPresent())
            return optional.get();
        else
            return new Company();
    }

    @Override
    public Company updateCompany(Company company) {
        return companyDao.updateCompany(company);
    }

    @Override
    public Company addBeneficiaryOwners(String name, Set<String> beneficiaryOwners) {
        return companyDao.addBeneficiaryOwners(name, beneficiaryOwners);
    }
}
