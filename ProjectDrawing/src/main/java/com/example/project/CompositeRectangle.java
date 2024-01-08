package com.example.project;


import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CompositeRectangle extends StackPane {
    private Rectangle colorButton;
    private Text text;

    public CompositeRectangle(String color)
    {
        colorButton = new Rectangle(62.5, 62.5, Color.valueOf(color));
        colorButton.setStroke(Color.LIGHTGRAY);
        colorButton.setStrokeWidth(5.0);

        text = new Text(color);

        this.getChildren().addAll(colorButton, text);
        this.setMinSize(25, 20);
        this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.setPrefSize(62.5, 62.5);
    }

    public Paint getFill()
    {
        return this.colorButton.getFill();
    }

    public void setStroke(Paint color)
    {
        this.colorButton.setStroke(color);
    }

    public void setStrokeWidth(Double width)
    {
        this.colorButton.setStrokeWidth(width);
    }
}
