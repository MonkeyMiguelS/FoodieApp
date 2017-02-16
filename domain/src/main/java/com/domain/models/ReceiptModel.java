package com.domain.models;

import java.util.List;

public class ReceiptModel {

    private final int id;
    private final String name;
    private final List<String> ingredients;
    private final String typeCousine;
    private final int timePreparation;
    private final String description;

    public ReceiptModel(int id, String name, List<String> ingredients, String typeCousine, int timePreparation, String description) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.typeCousine = typeCousine;
        this.timePreparation = timePreparation;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public String getTypeCousine() {
        return typeCousine;
    }

    public int getTimePreparation() {
        return timePreparation;
    }

    public String getDescription() {
        return description;
    }
}
