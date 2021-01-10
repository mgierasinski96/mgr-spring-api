package com.mgierasinski.springapi.services;


import com.mgierasinski.springapi.model.helpers.JSONFeature;
import com.mgierasinski.springapi.model.helpers.EnumInterface;
import com.mgierasinski.springapi.model.helpers.Feature;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeatureService {
    public String findEnumValueByFieldName(EnumInterface[] values, String searchedValue)
    {
        for(EnumInterface enumInterface :values)
        {
            if(enumInterface.getDisplayName().equals(searchedValue))
            {
                return enumInterface.getValue()+"";
            }
        }
        return null;
    }

    public List<Feature> removePreviousFeatureValue(String featureName, List<Feature> features) {
        List<Feature> newFeatureList = new ArrayList<>();
        for (Feature feature : features) {
            if (!feature.getFeatureName().equals(featureName)) {
                newFeatureList.add(feature);
            }
        }
        return newFeatureList;
    }
}
