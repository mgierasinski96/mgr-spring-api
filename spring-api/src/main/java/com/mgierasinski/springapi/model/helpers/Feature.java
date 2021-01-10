package com.mgierasinski.springapi.model.helpers;

public class Feature {
    private String featureName;
    private String value;
    private String displayedName;
    public Feature(String featureName,String value, String displayedName)
    {
        this.featureName=featureName;
        this.value=value;
        this.displayedName=displayedName;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "featureName='" + featureName + '\'' +
                ", value='" + value + '\'' +
                ", displayedName='" + displayedName + '\'' +
                '}';
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDisplayedName() {
        return displayedName;
    }

    public void setDisplayedName(String displayedName) {
        this.displayedName = displayedName;
    }
}
