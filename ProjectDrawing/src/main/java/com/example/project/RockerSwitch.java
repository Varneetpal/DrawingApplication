package com.example.project;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.text.Font;


public class RockerSwitch extends StackPane{
    InteractionModel iModel;

    public enum RockerState {FIRST, SECOND, THIRD, FOURTH, FIFTH}
    public RockerState state;

    //toggle buttons for rectangle, square, circle, oval, line
    private final ToggleButton[] toggles = {new ToggleButton(), new ToggleButton(), new ToggleButton(),
            new ToggleButton(), new ToggleButton()};

    private final SimpleBooleanProperty[] booleanProperties = {new SimpleBooleanProperty(), new SimpleBooleanProperty(),
            new SimpleBooleanProperty(), new SimpleBooleanProperty(), new SimpleBooleanProperty()};

    private final Shape[] images = {new Rectangle(25, 20), new Rectangle(23, 23),
            new Circle(12.5), new Ellipse(12.5, 10), new Line(5, 5, 20, 20)};

    public RockerSwitch() {
        VBox frame = new VBox();

        state = RockerState.FIRST;

        String[] switchNames = {"Rect", "Square", "Circle", "Oval", "Line"};

        for (int i = 0; i < toggles.length; i++)
        {
            booleanProperties[i].set(false);
            toggles[i] = new ToggleButton(switchNames[i], images[i]);

            toggles[i].setContentDisplay(ContentDisplay.TOP);
            toggles[i].setFont(Font.font(12));

            toggles[i].setMinSize(30, 50);
            toggles[i].setPrefSize(60, 100);
            toggles[i].setMaxSize(100, Double.MAX_VALUE);

            toggles[i].setSelected(false);

            frame.getChildren().addAll(toggles[i]);
        }
        booleanProperties[0].set(true); //first button is being pressed by default
        toggles[0].setSelected(true); //first button is being selected by default
        this.images[0].setFill(Color.AQUA);

        toggles[0].setOnAction(this::chooseRect);
        toggles[1].setOnAction(this::chooseSquare);
        toggles[2].setOnAction(this::chooseCircle);
        toggles[3].setOnAction(this::chooseOval);
        toggles[4].setOnAction(this::chooseLine);

        frame.setMinSize(60, 60);
        frame.setPrefSize(60, 500);
        frame.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.getChildren().add(frame);
    }

    public void chooseRect(ActionEvent actionEvent) {
        state = RockerState.FIRST;

        for (ToggleButton toggle : toggles)
        {
            toggle.setSelected(false);
        }
        toggles[0].setSelected(true);

        for (SimpleBooleanProperty booleanProperty : booleanProperties) {
            booleanProperty.set(false);
        }
        booleanProperties[0].set(true);
    }

    public void chooseSquare(ActionEvent actionEvent) {
        state = RockerState.SECOND;

        for (ToggleButton toggle : toggles)
        {
            toggle.setSelected(false);
        }
        toggles[1].setSelected(true);


        for (SimpleBooleanProperty booleanProperty : booleanProperties) {
            booleanProperty.set(false);
        }
        booleanProperties[1].set(true);
    }

    private void chooseCircle(ActionEvent actionEvent) {
        state = RockerState.THIRD;

        for (ToggleButton toggle : toggles)
        {
            toggle.setSelected(false);
        }
        toggles[2].setSelected(true);


        for (SimpleBooleanProperty booleanProperty : booleanProperties) {
            booleanProperty.set(false);
        }
        booleanProperties[2].set(true);
    }

    private void chooseOval(ActionEvent actionEvent) {
        state = RockerState.FOURTH;

        for (ToggleButton toggle : toggles)
        {
            toggle.setSelected(false);
        }
        toggles[3].setSelected(true);


        for (SimpleBooleanProperty booleanProperty : booleanProperties) {
            booleanProperty.set(false);
        }
        booleanProperties[3].set(true);
    }

    private void chooseLine(ActionEvent actionEvent) {
        state = RockerState.FIFTH;

        for (ToggleButton toggle : toggles)
        {
            toggle.setSelected(false);
        }
        toggles[4].setSelected(true);


        for (SimpleBooleanProperty booleanProperty : booleanProperties) {
            booleanProperty.set(false);
        }
        booleanProperties[4].set(true);
    }

    public RockerState getState() {
        return state;
    }

    public void setModel(InteractionModel newModel)
    {
        iModel = newModel;
    }

    public void setRectangleColor(Paint color)
    {
        this.images[0].setFill(color);
    }

    public void setSquareColor(Paint color)
    {
        this.images[1].setFill(color);
    }

    public void setCircleColor(Paint color)
    {
        this.images[2].setFill(color);
    }

    public void setOvalColor(Paint color)
    {
        this.images[3].setFill(color);
    }

    public void setLineColor(Paint color)
    {
        this.images[4].setStroke(color);
    }

    public SimpleBooleanProperty[] getBooleanProperties()
    {
        return this.booleanProperties;
    }

}

