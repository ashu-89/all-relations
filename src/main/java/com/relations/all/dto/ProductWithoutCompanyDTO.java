package com.relations.all.dto;

import java.util.UUID;

public class ProductWithoutCompanyDTO {
    private UUID id;
    private String name;

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
}
