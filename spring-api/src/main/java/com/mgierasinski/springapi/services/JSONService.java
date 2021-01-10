package com.mgierasinski.springapi.services;

import com.mgierasinski.springapi.model.helpers.Feature;
import com.mgierasinski.springapi.model.helpers.JSONFeature;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;

@Service
public class JSONService {
private final String FOLDER_PATH = "C:\\Users\\Mateusz\\Desktop\\jsons";
    private void safeToJson(String pathWithFilename, String text) throws Exception
    {
        File file = new File(pathWithFilename);
        PrintWriter out = new PrintWriter(file);
        out.print(text);
        out.close();
    }

    private String getJSONFrom(Feature feature)
    {
        JSONFeature jsonFeature = new JSONFeature(feature);
        return jsonFeature.toJSON();
    }

    public boolean exportAllToJSON(List<Feature> features)
    {   cleanDirectory();
        String path = FOLDER_PATH;

        try
        {
            for(Feature feature : features)
            {
                String jsonString = getJSONFrom(feature);
                String pathWithFilename = path + File.separator + feature.getFeatureName().replaceAll("[:\\\\/*?|<>\\\"]", "_") + ".json";
                safeToJson(pathWithFilename, jsonString);

            }
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void cleanDirectory() {
        try {
            FileUtils.cleanDirectory(new File(FOLDER_PATH));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
