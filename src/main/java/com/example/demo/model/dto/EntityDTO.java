package com.example.demo.model.dto;

public abstract class EntityDTO {

    private String name;
    private String description;

    public EntityDTO(String entityName) {
        this.name = entityName;
    }

    public EntityDTO(String entityName, String description) {
        this.name = entityName;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
