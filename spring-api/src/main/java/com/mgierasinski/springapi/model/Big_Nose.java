package com.mgierasinski.springapi.model;

import com.mgierasinski.springapi.model.helpers.EnumInterface;

public enum Big_Nose implements EnumInterface {
    SMALL("Small", -1.0),
    NORMAL("Normal", 0.0),
    BIG("Big", -1.0);


    private String displayName;
    private Double value;
    Big_Nose(String displayName, Double value) {
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
