package com.mgierasinski.springapi.model;

import com.mgierasinski.springapi.model.helpers.EnumInterface;

public enum Attractive implements EnumInterface {


    YES("Yes", 1.0d),
    NO("No", -1.0d);


    private String displayName;
    private Double value;

    Attractive(String displayName, Double value) {
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
