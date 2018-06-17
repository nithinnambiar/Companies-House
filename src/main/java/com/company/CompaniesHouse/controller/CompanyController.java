package com.company.CompaniesHouse.controller;


import com.company.CompaniesHouse.model.Company;
import com.company.CompaniesHouse.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    @RequestMapping(path="/create", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createCompany(@Valid @RequestBody Company company){


        Company currentCompany=companyService.getCompanyDetails(company.getName());

        if(currentCompany.getId()!=null)
            return new ResponseEntity("Company " + company.getName() + " already exists ", HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(companyService.createCompany(company));
    }

    @RequestMapping(path="/list", method= RequestMethod.GET)
    public ResponseEntity<?> listCompanies(){

        return ResponseEntity.ok(companyService.listCompanies());

    }

    @RequestMapping(path="/details/{name}", method= RequestMethod.GET)
    public ResponseEntity<?> getCompanyDetails(@PathVariable("name") String name){

        Company company=companyService.getCompanyDetails(name);
        if(company.getId()==null)
            return new ResponseEntity("Company " + name + " not found ", HttpStatus.BAD_REQUEST);


        return ResponseEntity.ok(companyService.getCompanyDetails(name));

    }

    @RequestMapping(path="/update", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> updateCompany(@Valid @RequestBody Company company){

        Company currentCompany=companyService.getCompanyDetails(company.getName());

        if(currentCompany.getId()==null)
            return new ResponseEntity("Error, Company " + company.getName() + " doesn't exist ", HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(companyService.updateCompany(company));

    }

    @RequestMapping(path="/addowners", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addBeneficialOwners(@Valid @RequestBody Company company){

        Company currentCompany=companyService.getCompanyDetails(company.getName());

        if(currentCompany.getId()==null)
            return new ResponseEntity("Error, Company " + company.getName() + " doesn't exist ", HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(companyService.addBeneficiaryOwners(company.getName(),company.getBeneficialOwners()));

    }




}
