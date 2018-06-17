package com.company.CompaniesHouse.dao;

import com.company.CompaniesHouse.model.Company;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class CompanyDaoImpl implements CompanyDao{

    private ConcurrentHashMap<String,Company> companyStore=new ConcurrentHashMap<>();


    public Company createCompany(Company company){

        company.setId(UUID.randomUUID().toString());

        companyStore.putIfAbsent(company.getId(), company);
        return company;


    }

    public List<Company> listCompanies(){

        return companyStore.values().stream().collect(Collectors.toList());

    }

    public Optional<Company> getCompanyDetails(String name){

        return companyStore.values().stream().filter(company -> company.getName().equals(name)).findFirst();
    }



    @Override
    public Company updateCompany(Company company) {

        Company currentCompany=companyStore.values().stream().filter(c -> c.getName().equals(company.getName())).findFirst().get();

        company.setId(currentCompany.getId());
       companyStore.put(currentCompany.getId(),company);
       return company;

    }

    @Override
    public Company addBeneficiaryOwners(String name, Set<String> beneficiaryOwners) {



       Optional<Company> optional=getCompanyDetails(name);
     if(optional.isPresent()){

         Company company=optional.get();

            if (company.getBeneficialOwners() == null)
                company.setBeneficialOwners(beneficiaryOwners);
            else
                company.getBeneficialOwners().addAll(beneficiaryOwners);

         return company;

        }
        return  new Company();



    }


    @Override
    public void deleteAllCompanies() {

        companyStore.clear();

    }


}
