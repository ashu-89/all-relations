package com.relations.all.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
public class Employee {

    //@GeneratedValue(strategy = GenerationType.AUTO) cannot be used with String !!
    //https://docs.jboss.org/hibernate/orm/4.1/manual/en-US/html/ch05.html#mapping-declaration-id
    //Above creates table in MySQL db, but the column isn't marked G (generated value) and needs to be manually entered!

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    private String name;

    private Integer age;
    private String sex;
    private String city;

    @JsonBackReference //Annotation works here as well as if applied only on getter
    @ManyToOne(fetch=FetchType.EAGER)
// @JoinColumn //(name = "company_id") @JoinColumn is OPTIONAL on this side of the association.
    //This annotation is used only if we want custom name of the fk field (default - attributeNameOfThisTable_primaryKeyNameOfOtherTable)
    private Company company;

    //Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
