package com.mgierasinski.springapi.model.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONFeature {

    private String featureName;
    private String value;
    private String displayedName;

    public JSONFeature()
    {
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

    public JSONFeature(Feature feature) {
        setDisplayedName(feature.getDisplayedName());
        setFeatureName(feature.getFeatureName());
        setValue(feature.getValue());
    }


    public String toJSON()
    {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(this);
    }


}
