package com.relations.all.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.relations.all.views.Views;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.boot.jackson.JsonMixin;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "product")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
//@JsonMixin(ProductMixin.class)
public class Product {

    @Id
    @GeneratedValue
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @JsonView(Views.Public.class)
    UUID id;

    @JsonView(Views.Public.class)
    String name;

    @ManyToMany ( mappedBy = "products")
    @JsonView(Views.Public.class)
    Set<Company> companies = new HashSet<>();

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

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    //
    // Custom serializer as static inner class
   // public static class CustomProductsSerializer extends JsonSerializer<Product> {
//        public static class CustomProductsSerializer extends JsonSerializer<Product> {
//
//
//            public CustomProductsSerializer() {
//            // Add a default constructor (no-argument) to satisfy Jackson's requirements
//        }
//
//        @Override
//        public void serialize(Product product, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//            jsonGenerator.writeStartObject();
//            jsonGenerator.writeStringField("id", product.getId().toString());
//            jsonGenerator.writeEndObject();
//        }
//    }
    //
}