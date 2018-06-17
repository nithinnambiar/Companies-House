package com.company.CompaniesHouse.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.ComponentScan;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@ComponentScan
public class Company {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    @NotNull
    @Size(min=2, message="Name should have atleast 2 characters")
    private String name;
    @JsonProperty("address")
    private String address;
    @JsonProperty("city")
    private String city;
    @JsonProperty("country")
    private String country;
    @JsonProperty("beneficial_owners")
    private Set<String> beneficialOwners;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<String> getBeneficialOwners() {
        return beneficialOwners;
    }

    public void setBeneficialOwners(Set<String> beneficialOwners) {
        this.beneficialOwners = beneficialOwners;
    }
}
