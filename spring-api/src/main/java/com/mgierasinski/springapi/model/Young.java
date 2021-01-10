package com.mgierasinski.springapi.model;

import com.mgierasinski.springapi.model.helpers.EnumInterface;

public enum Young implements EnumInterface {

    VERY_YOUNG("Very young (0-18)", 2.0d),
    YOUNG("Young (18-30)", 1.0d),
    MIDDLE_AGED("Middle aged (30-50)", 0.0d),
    OLD("Old (50-65)", -1.0d),
    VERY_OLD("Very old (65+)", -2.0d);


    private String displayName;
    private Double value;

    Young(String displayName, Double value) {
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
