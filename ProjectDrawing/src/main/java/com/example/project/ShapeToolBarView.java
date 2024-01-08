package com.example.project;



import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ShapeToolBarView extends StackPane implements InteractionModelSubscriber{
    InteractionModel iModel;
    private final RockerSwitch aSwitch;

    public ShapeToolBarView()
    {
        VBox shapeBar = new VBox();

        aSwitch = new RockerSwitch();
        shapeBar.getChildren().add(aSwitch);
        shapeBar.setMaxSize(aSwitch.getMaxWidth(), aSwitch.getMaxHeight());
        shapeBar.setMinSize(aSwitch.getMinWidth(), aSwitch.getMinHeight());
        shapeBar.setPrefSize(aSwitch.getPrefWidth(), aSwitch.getPrefHeight());

        this.getChildren().add(shapeBar);
    }

    public void setModel(InteractionModel newModel)
    {
        iModel = newModel;
        aSwitch.setModel(newModel);
    }

    public void setController(DrawingController controller){
        aSwitch.getBooleanProperties()[0].addListener((observable, oldVal, newVal) -> {
            iModelChanged();
            controller.handleRectangle();
        });

        aSwitch.getBooleanProperties()[1].addListener((observable, oldVal, newVal) -> {
            iModelChanged();
            controller.handleSquare();
        });

        aSwitch.getBooleanProperties()[2].addListener((observable, oldVal, newVal) -> {
            iModelChanged();
            controller.handleCircle();
        });

        aSwitch.getBooleanProperties()[3].addListener((observable, oldVal, newVal) -> {
            iModelChanged();
            controller.handleOval();
        });

        aSwitch.getBooleanProperties()[4].addListener((observable, oldVal, newVal) -> {
            iModelChanged();
            controller.handleLine();
        });

    }

    @Override
    public void iModelChanged() {
        if (aSwitch.getState() == RockerSwitch.RockerState.FIRST)
        {
            aSwitch.setRectangleColor(iModel.selectedColor);
            aSwitch.setSquareColor(Color.BLACK);
            aSwitch.setCircleColor(Color.BLACK);
            aSwitch.setOvalColor(Color.BLACK);
            aSwitch.setLineColor(Color.BLACK);
        }
        else if (aSwitch.getState() == RockerSwitch.RockerState.SECOND)
        {
            aSwitch.setRectangleColor(Color.BLACK);
            aSwitch.setSquareColor(iModel.selectedColor);
            aSwitch.setCircleColor(Color.BLACK);
            aSwitch.setOvalColor(Color.BLACK);
            aSwitch.setLineColor(Color.BLACK);
        }
        else if (aSwitch.getState() == RockerSwitch.RockerState.THIRD)
        {
            aSwitch.setRectangleColor(Color.BLACK);
            aSwitch.setSquareColor(Color.BLACK);
            aSwitch.setCircleColor(iModel.selectedColor);
            aSwitch.setOvalColor(Color.BLACK);
            aSwitch.setLineColor(Color.BLACK);
        }
        else if (aSwitch.getState() == RockerSwitch.RockerState.FOURTH)
        {
            aSwitch.setRectangleColor(Color.BLACK);
            aSwitch.setSquareColor(Color.BLACK);
            aSwitch.setCircleColor(Color.BLACK);
            aSwitch.setOvalColor(iModel.selectedColor);
            aSwitch.setLineColor(Color.BLACK);
        }
        else
        {
            aSwitch.setRectangleColor(Color.BLACK);
            aSwitch.setSquareColor(Color.BLACK);
            aSwitch.setCircleColor(Color.BLACK);
            aSwitch.setOvalColor(Color.BLACK);
            aSwitch.setLineColor(iModel.selectedColor);
        }
    }

}
