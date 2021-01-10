package com.mgierasinski.springapi.model;

import com.mgierasinski.springapi.model.helpers.EnumInterface;

public enum High_Cheekbones implements EnumInterface {
    YES("Yes", 1.0),
    NO("No", -1.0);


    private String displayName;
    private Double value;

    High_Cheekbones(String displayName, Double value) {
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
