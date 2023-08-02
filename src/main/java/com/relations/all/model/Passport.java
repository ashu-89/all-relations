package com.relations.all.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.relations.all.serializer.CustomPassportSerializer;
import com.relations.all.serializer.CustomProductSetSerializer;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@JsonSerialize(using = CustomPassportSerializer.class)

public class Passport {

    //@GeneratedValue(strategy = GenerationType.AUTO) cannot be used with String !!
    //https://docs.jboss.org/hibernate/orm/4.1/manual/en-US/html/ch05.html#mapping-declaration-id
    //Above creates table in MySQL db, but the column isn't marked G (generated value) and needs to be manually entered!

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;
    private String passportId;
    private String name;
    private LocalDate dateIssued;
    private LocalDate dateOfExpiry;

    @OneToOne(mappedBy = "passport")
    //@JsonBackReference
    //@JsonSerialize(using = CustomPassportSerializer.class)
    private Employee employee;

    //Getters and setters

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(LocalDate dateIssued) {
        this.dateIssued = dateIssued;
    }

    public LocalDate getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(LocalDate dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }




}
