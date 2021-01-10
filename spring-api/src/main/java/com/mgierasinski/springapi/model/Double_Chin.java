package com.mgierasinski.springapi.model;

import com.mgierasinski.springapi.model.helpers.EnumInterface;

public enum Double_Chin implements EnumInterface {
    YES("Yes", 1.0),
    NO("No", -1.0);


    private String displayName;
    private Double value;

    Double_Chin(String displayName, Double value) {
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
