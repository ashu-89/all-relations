package com.relations.all.dto;

import com.relations.all.model.Company;

import java.io.Serializable;
import java.util.Set;

public class ProductDTO implements Serializable {

    private String name;
    private Set<Company> companies;

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    //

}
