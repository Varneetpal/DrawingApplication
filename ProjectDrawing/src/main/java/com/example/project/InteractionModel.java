package com.example.project;


import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class InteractionModel {
    Paint selectedColor = Color.AQUA;

    XShape selectedShape;

    protected enum Shapes {
        RECTANGLE, SQUARE, CIRCLE, OVAL, LINE;
    }

    public Shapes currentShape;

    ArrayList<InteractionModelSubscriber> subscribers;
    double worldWidth = 2000;
    double worldHeight = 2000;

    private double canvasLeft, canvasTop;
    private double canvasWidth, canvasHeight;

    public InteractionModel(){
        subscribers = new ArrayList<>();

        this.currentShape = Shapes.RECTANGLE;

        this.selectedShape = null;

        this.canvasLeft = 0;
        this.canvasTop = 0;

        this.canvasWidth = 500;
        this.canvasHeight = 500;
    }

    public void moveCanvas(double dX, double dY)
    {
        this.canvasLeft += dX;
        this.canvasTop += dY;
        notifySubscribers();
    }

    public void resizeCanvas(double dX, double dY)
    {
        this.canvasWidth = dX;
        this.canvasHeight = dY;
        notifySubscribers();
    }

    public void setCanvasLeft(double left)
    {
        this.canvasLeft += left;
        notifySubscribers();
    }

    public double getCanvasLeft()
    {
        return this.canvasLeft;
    }

    public void setCanvasTop(double top)
    {
        this.canvasTop += top;
        notifySubscribers();
    }

    public double getCanvasTop()
    {
        return this.canvasTop;
    }

    public void setCanvasWidth(double width)
    {
        this.canvasWidth = width;
        notifySubscribers();
    }

    public double getCanvasWidth()
    {
        return this.canvasWidth;
    }

    public void setCanvasHeight(double height)
    {
        this.canvasHeight = height;
        notifySubscribers();
    }

    public void setLeft(double left)
    {
        this.canvasLeft = left;
        notifySubscribers();
    }

    public void setTop(double top)
    {
        this.canvasTop = top;
        notifySubscribers();
    }
    public double getCanvasHeight()
    {
        return this.canvasHeight;
    }

    public void addSubscriber(InteractionModelSubscriber aSub) {
        subscribers.add(aSub);
    }

    public void notifySubscribers() {
        subscribers.forEach(InteractionModelSubscriber::iModelChanged);
    }

    public void setColour(Paint newColour) {
        this.selectedColor = newColour;
        notifySubscribers();
    }

    public void selectShape(XShape shape)
    {
        this.selectedShape = shape;
        notifySubscribers();
    }

    public void unSelectShape()
    {
        this.selectedShape = null;
        notifySubscribers();
    }

    public XShape getSelectedShape()
    {
        return this.selectedShape;
    }

    public void handleRectangle()
    {
        this.currentShape = Shapes.RECTANGLE;
        notifySubscribers();
    }

    public void handleSquare()
    {
        this.currentShape = Shapes.SQUARE;
        notifySubscribers();
    }

    public void handleCircle()
    {
        this.currentShape = Shapes.CIRCLE;
        notifySubscribers();
    }

    public void handleOval()
    {
        this.currentShape = Shapes.OVAL;
        notifySubscribers();
    }

    public void handleLine()
    {
        this.currentShape = Shapes.LINE;
        notifySubscribers();
    }

}
