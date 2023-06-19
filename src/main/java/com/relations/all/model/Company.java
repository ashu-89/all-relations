package com.relations.all.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
public class Company {

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    private String name;

    @JsonManagedReference //Annotations work here as well as when applied to getter itself
    @OneToMany ( cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "company") //- only required on this side for bi-directional one-to-many relation.
    //@JoinColumn(name="company_id")  //w/o join column, hibernate will create a join table
                                      //for uni-directional one-to-many relations !
    private List<Employee> employees;

    //Getters and setters

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


    //@JsonManagedReference
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
