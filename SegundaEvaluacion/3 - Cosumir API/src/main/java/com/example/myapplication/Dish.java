package com.example.myapplication;

import java.io.Serializable;

public class Dish implements Serializable {
    private int id;
    private String name;
    private String category;
    private String cuisine;
    private String description;

    public Dish(int id, String name, String category, String cuisine, String description) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.cuisine = cuisine;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
