package com.mgierasinski.springapi.gui;

import com.mgierasinski.springapi.services.FeatureService;
import com.mgierasinski.springapi.model.*;
import com.mgierasinski.springapi.model.helpers.Feature;
import com.mgierasinski.springapi.model.helpers.EnumInterface;
import com.mgierasinski.springapi.services.JSONService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Route("")
@Theme(value = Lumo.class, variant = Lumo.DARK)
@CssImport("style.css")
public class MainGui extends VerticalLayout {

    private List<Feature> features = new ArrayList<>();
    List<Select<EnumInterface>> dropDownsCreated = new ArrayList<>();
    private FormLayout formLayout = new FormLayout();
    private Div info = new Div();
    private FeatureService featureService;
    private JSONService jsonService;
    private boolean showResultsClicked=false;
    List<Image> images=new ArrayList<>();
    VerticalLayout imageLayout = new VerticalLayout();
    private final String IMAGE_PATH = "C:\\Users\\Mateusz\\Desktop\\test\\spring-api\\src\\main\\resources\\META-INF\\resources\\img";

    @Autowired
    public MainGui(FeatureService featureService, JSONService jsonService)
    {
        this.featureService=featureService;
        this.jsonService=jsonService;
        formLayout.setMaxWidth("50%");
        H2 pageTitle = new H2("Analysis of the methods of person identification based on facial feature description - input form");
        H4 author = new H4("Author: Mateusz Gierasiński");
        info.add(pageTitle);
        info.add(author);
        info.setClassName("info-nav");
        add(info);;
        createAndAddDropDownList("Sex", Sex.values());
        createAndAddDropDownList("Age", Young.values());
        createAndAddDropDownList("Attractive", Attractive.values());
        createAndAddDropDownList("Bags under eyes", Bags_Under_Eyes.values());
        createAndAddDropDownList("Lips size", Big_Lips.values());
        createAndAddDropDownList("Nose size", Big_Nose.values());
        createAndAddDropDownList("Bushy eyebrows", Bushy_Eyebrows.values());
        createAndAddDropDownList("Chubby", Chubby.values());
        createAndAddDropDownList("Double chin", Double_Chin.values());
        createAndAddDropDownList("Eyeglasses", Eyeglasses.values());
        createAndAddDropDownList("High cheekbones", High_Cheekbones.values());
        createAndAddDropDownList("Mustache", Mustache.values());
        createAndAddDropDownList("Narrow eyes", Narrow_Eyes.values());
        createAndAddDropDownList("Pointy nose", Pointy_Nose.values());
        add(formLayout);
        imageLayout.setClassName("image-layout");
        add(imageLayout);
        Button safeButton = new Button("Accept");
        safeButton.addClickListener(e ->{
            if(features.size()<dropDownsCreated.size())
            {
                for(Select<EnumInterface> dropDown: dropDownsCreated)
                {
                    if(dropDown.isEmpty())
                    {
                        dropDown.setInvalid(true);
                        dropDown.setErrorMessage("Field is required");
                    }
                }
            } else
            {
                boolean success= jsonService.exportAllToJSON(features);
                if(success) {
                    showResultsClicked=false;
                    Notification notification = new Notification(
                            "Success", 3000, Notification.Position.TOP_CENTER);
                    notification.open();
                    removeImages(images);

                }
            }

        });
add(safeButton);
        Button showResults = new Button("Show results");


        showResults.addClickListener(e -> {
            if(showResultsClicked==false)
            {
                final File folder = new File(IMAGE_PATH);
                for (final File fileEntry : folder.listFiles()) {
                    try {
                        Path path = Paths.get(IMAGE_PATH+ "\\" + fileEntry.getName());
                        byte[] imageBytes = Files.readAllBytes(path);
                        StreamResource resource = new StreamResource(fileEntry.getName(), () -> new ByteArrayInputStream(imageBytes));
                        Image image = new Image(resource, fileEntry.getName());
                        Div imageDiv = new Div();
                        imageDiv.add(image);
                        imageDiv.setClassName("img_div");
                        imageLayout.add(imageDiv);
                        images.add(image);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                showResultsClicked = true;
            }
        });

        add(showResults);
    }

    private void createAndAddDropDownList(String label, EnumInterface[] values)
    {
        Select<EnumInterface> dropDownList= new Select<>();
        dropDownList.setLabel(label);
        dropDownsCreated.add(dropDownList);
        dropDownList.setItems(values);
        String featureName = values.getClass().getSimpleName().replace("[]","");


        dropDownList.addValueChangeListener(e -> {
                dropDownList.setInvalid(false);
                String fieldValue = dropDownList.getValue().toString();
                String value=featureService.findEnumValueByFieldName(values, fieldValue);
                features = featureService.removePreviousFeatureValue(featureName, features);
                features.add( new Feature(featureName,value, fieldValue));
            });
        formLayout.add(dropDownList);
    }

    private void removeImages(List<Image> images) {
        for (Image image : images) {
            remove(image);
        }
    }
}
