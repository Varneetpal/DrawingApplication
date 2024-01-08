package com.example.project;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

public class DrawingController {
    InteractionModel iModel;
    DrawingModel model;
    private double opacity;
    double prevX, prevY;

    enum XShape {
        RECTANGLE, SQUARE, CIRCLE, OVAL, LINE;
    }

    XShape currentShape;

    private enum State {
        READY, PREPARE_CREATE, DRAG_SHAPE, RESIZE, DRAG_CANVAS
    }

    private State currentState;

    public DrawingController(){
        this.currentShape = XShape.RECTANGLE;
        this.currentState = State.READY;
    }

    public void setInteractionModel(InteractionModel newModel) {
        iModel = newModel;
    }

    public void setModel(DrawingModel newModel) {
        model = newModel;
    }

    public void readyToCreate(double normX, double normY)
    {
        iModel.unSelectShape();
        switch (currentShape) {

            case RECTANGLE -> {
                model.setTemp(new XRectangle(
                        normX,
                        normY,
                        iModel.selectedColor));
                currentState = State.PREPARE_CREATE;
            }

            case SQUARE -> {
                model.setTemp(new XSquare(
                        normX,
                        normY,
                        iModel.selectedColor));
                currentState = State.PREPARE_CREATE;
            }

            case CIRCLE -> {
                model.setTemp(new XCircle(
                        normX,
                        normY,
                        iModel.selectedColor));
                currentState = State.PREPARE_CREATE;
            }

            case OVAL -> {
                model.setTemp(new XOval(
                        normX,
                        normY,
                        iModel.selectedColor));
                currentState = State.PREPARE_CREATE;
            }

            case LINE -> {
                model.setTemp(new XLine(
                        normX,
                        normY,
                        iModel.selectedColor));
                currentState = State.PREPARE_CREATE;
            }
        }
    }

    public void handlePressedLeft(double normX, double normY, MouseEvent event) {
        prevX = normX;
        prevY = normY;

        switch (currentState) {

            case READY -> {
                // context: are we on a shape?
                boolean hit = this.model.checkHit(normX, normY);
                if (hit)
                {
                    this.iModel.selectShape(this.model.whichShape(normX, normY));
                    this.model.selectedShape(this.model.whichShape(normX, normY));
                    this.model.sortByZScore();
                    this.currentState = State.DRAG_SHAPE;
                }
                else if (this.iModel.selectedShape != null)
                {
                    //Are we on the resizing button?
                    boolean chooseResize = this.model.checkResize(this.iModel.selectedShape, normX, normY);
                    System.out.println(chooseResize);
                    if (chooseResize)
                        currentState = State.RESIZE;
                    else
                        readyToCreate(normX, normY);
                }
                else
                    readyToCreate(normX, normY);
            }
        }
    }

    public void handleWindowResizeWidth(double normX)
    {
        iModel.setCanvasWidth(normX);
    }

    public void handleWindowResizeHeight(double normY)
    {
        iModel.setCanvasHeight(normY);
    }

    public void handleMiniCanvasWithResize()
    {
        this.model.updateShape(model.selectedArea, iModel.getCanvasWidth(), iModel.getCanvasHeight());
    }

    public void handlePressedRight(double normX, double normY, MouseEvent event) {
        prevX = normX;
        prevY = normY;

        if (currentState == State.READY)
        {
            // context: are we on a shape?
            boolean hit = this.model.checkHit(normX, normY);
            if (!hit)
            {
                this.currentState = State.DRAG_CANVAS;
            }
        }
    }

    public void handleDraggedLeft(double normX, double normY, MouseEvent event) {
        double dX = normX - prevX;
        double dY = normY - prevY;

        prevX = normX;
        prevY = normY;

        switch (currentState) {
            case PREPARE_CREATE -> model.updateShape(model.getTemp(), normX, normY);

            case DRAG_SHAPE -> {
                this.model.sortByZScore();
                model.moveShape(iModel.selectedShape, dX, dY);
            }
            case RESIZE -> model.updateShape(iModel.getSelectedShape(), normX, normY);
        }
    }

    public void handleDraggedRight(double normX, double normY, MouseEvent event) {
        double dX = normX - prevX;
        double dY = normY - prevY;

        prevX = normX;
        prevY = normY;

        if (currentState == State.DRAG_CANVAS)
        {
            iModel.moveCanvas(dX, dY);
        }
    }

    public void handleReleasedLeft(double normX, double normY, MouseEvent event)
    {
        switch(currentState){

            case PREPARE_CREATE -> {
                model.addShape(model.getTemp().update(normX, normY));
                model.setTemp(null);
                currentState = State.READY;
            }
            case DRAG_SHAPE -> currentState = State.READY;

            case RESIZE ->{
                iModel.unSelectShape();
                currentState = State.READY;
            }
        }

    }

    public void handleReleasedRight(double normX, double normY, MouseEvent event)
    {
        if (currentState == State.DRAG_CANVAS)
        {
            currentState = State.READY;
        }
    }

    public void handleKeyboard(KeyEvent keyEvent)
    {
        if (currentState == State.READY)
        {
            if (keyEvent.getCode() == KeyCode.DELETE) {
                model.deleteShape(iModel.getSelectedShape());
                iModel.unSelectShape();
            }
        }
    }

    public void handleMoved(double normX, double normY, MouseEvent mouseEvent) {
        prevX = normX;
        prevY = normY;
    }

    public void setColor(Paint newColour) {
        iModel.setColour(newColour);
        opacity = 1.0;
    }

    public void handleRectangle() {
        this.currentShape = XShape.RECTANGLE;
        iModel.handleRectangle();
    }

    public void handleSquare() {
        this.currentShape = XShape.SQUARE;
        iModel.handleSquare();
    }

    public void handleCircle() {
        this.currentShape = XShape.CIRCLE;
        iModel.handleCircle();
    }

    public void handleOval() {
        this.currentShape = XShape.OVAL;
        iModel.handleOval();
    }

    public void handleLine() {
        this.currentShape = XShape.LINE;
        iModel.handleLine();
    }
}
