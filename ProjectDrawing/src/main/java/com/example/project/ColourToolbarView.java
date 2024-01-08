package com.example.project;


import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ColourToolbarView extends StackPane implements InteractionModelSubscriber {
    ArrayList<CompositeRectangle> colors;
    InteractionModel iModel;

    public ColourToolbarView()
    {
        VBox colorBar = new VBox();

        colors = new ArrayList<>();

        //Initialize the color bar with buttons
        String[] colorNames = {"Aqua", "Violet", "Green", "Gold", "Orange", "Coral", "Fuchsia", "Peru"};
        for (String name : colorNames)
        {
            CompositeRectangle colorButton = new CompositeRectangle(name);
            colorBar.getChildren().add(colorButton);
            colors.add(colorButton);
        }
        colorBar.setMinSize(25, 160);
        colorBar.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        colorBar.setPrefSize(62.5, 62.5 * colorNames.length);

        this.getChildren().add(colorBar);
    }

    public void setInteractionModel(InteractionModel newModel)
    {
        iModel = newModel;
    }

    public void setController(DrawingController controller){
        //set up color-selector event handling
        colors.forEach(c -> c.setOnMouseClicked(e -> controller.setColor(c.getFill())));
    }

    @Override
    public void iModelChanged() {
        // set border highlight for selected colour
        colors.forEach(cr -> {
            if (cr.getFill().equals(iModel.selectedColor)) {
                cr.setStroke(Color.BLACK);
                cr.setStrokeWidth(3.0);
            } else {
                cr.setStroke(Color.LIGHTGRAY);
            }
        });
    }
}
