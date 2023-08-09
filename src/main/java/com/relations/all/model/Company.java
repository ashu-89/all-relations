package com.relations.all.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.relations.all.serializer.CustomEmployeeSerializer;
import com.relations.all.serializer.CustomProductSetSerializer;
import com.relations.all.views.Views;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
public class Company {

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @JsonView(Views.Public.class)
    private UUID id;

    @JsonView(Views.Public.class)
    private String name;

    @ManyToMany
    @JsonSerialize(using = CustomProductSetSerializer.class) // Use the custom serializer for the 'products' field
    Set<Product> products = new HashSet<>();

    //@JsonManagedReference //Annotations work here as well as when applied to getter itself
    @OneToMany ( cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "company") //- only required on this side for bi-directional one-to-many relation.
    //@JoinColumn(name="company_id")  //w/o join column, hibernate will create a join table
                                      //for uni-directional one-to-many relations !
    //@JsonView(Views.Internal.class)
    //@JsonIgnore
    @JsonSerialize(using = CustomEmployeeSerializer.class)
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

    //

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
