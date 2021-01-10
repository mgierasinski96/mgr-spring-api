package com.mgierasinski.springapi.model;

import com.mgierasinski.springapi.model.helpers.EnumInterface;

public enum Sex implements EnumInterface {
    MALE("Male", 1.0),
    FEMALE("Female", -1.0);


    private String displayName;
    private Double value;
    Sex(String displayName, Double value) {
        this.value= value;
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Double getValue() {
        return value;
    }
    @Override
    public String toString() {
        return getDisplayName();
    }
}
