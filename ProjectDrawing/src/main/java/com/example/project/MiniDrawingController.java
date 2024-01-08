package com.example.project;



import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

public class MiniDrawingController extends DrawingController{
    InteractionModel iModel;
    DrawingModel model;
    double prevX, prevY;

    private enum XShape {
        RECTANGLE, SQUARE, CIRCLE, OVAL, LINE;
    }

    private XShape currentShape;

    private enum State {
        READY, AREA_DRAGGING
    }

    private State currentState;

    public MiniDrawingController(){
        super();
        this.currentShape = XShape.RECTANGLE;
        this.currentState = State.READY;
    }

    public void setInteractionModel(InteractionModel newModel) {
        super.iModel = newModel;
        this.iModel = newModel;
    }

    public void setModel(DrawingModel newModel) {
        super.model = newModel;
        this.model = newModel;
    }

    public void readyToCreate(double normX, double normY)
    {
        switch (currentShape) {

            case RECTANGLE -> super.currentShape = DrawingController.XShape.RECTANGLE;

            case SQUARE -> super.currentShape = DrawingController.XShape.SQUARE;

            case CIRCLE -> super.currentShape = DrawingController.XShape.CIRCLE;

            case OVAL -> super.currentShape = DrawingController.XShape.OVAL;

            case LINE -> super.currentShape = DrawingController.XShape.LINE;
        }

        super.readyToCreate(normX, normY);
    }

    public void handlePressedLeft(double normX, double normY, MouseEvent event) {
        if (this.iModel.currentShape.equals(InteractionModel.Shapes.RECTANGLE))
        {
            handleRectangle();
        }
        else if (this.iModel.currentShape.equals(InteractionModel.Shapes.SQUARE))
        {
            handleSquare();
        }
        else if (this.iModel.currentShape.equals(InteractionModel.Shapes.CIRCLE))
        {
            handleCircle();
        }
        else if (this.iModel.currentShape.equals(InteractionModel.Shapes.OVAL))
        {
            handleOval();
        }
        else if (this.iModel.currentShape.equals(InteractionModel.Shapes.LINE))
        {
            handleLine();
        }
        else
        {
            System.out.println("Something went wrong?");
        }
        super.handlePressedLeft(normX, normY, event);
    }

    public void handlePressedRight(double normX, double normY, MouseEvent event) {
        prevX = normX;
        prevY = normY;

        switch (currentState) {

            case READY -> {
                // context: are we on a selection area?
                boolean hit = this.model.checkAreaSelected(normX, normY);
                System.out.println(hit);
                if (hit)
                {
                    this.model.selectedArea(this.model.getSelectedArea(normX, normY));
                    this.currentState = State.AREA_DRAGGING;
                }
            }
        }

    }

    public void handleDraggedLeft(double normX, double normY, MouseEvent event) {
        super.handleDraggedLeft(normX, normY, event);
    }

    public void handleDraggedRight(double normX, double normY, MouseEvent event) {
        double dX = normX - prevX;
        double dY = normY - prevY;

        prevX = normX;
        prevY = normY;

        switch (currentState) {
            case AREA_DRAGGING ->
                    {
                        this.model.moveShape(model.selectedArea, dX, dY);
                        this.iModel.moveCanvas(-dX * iModel.worldWidth, -dY * iModel.worldHeight);
                    }
        }
    }

    public void handleReleasedLeft(double normX, double normY, MouseEvent event)
    {
        super.handleReleasedLeft(normX, normY, event);
        this.currentState = State.READY;
    }

    public void handleReleasedRight(double normX, double normY, MouseEvent event)
    {
        currentState = State.READY;
    }

    public void handleKeyboard(KeyEvent keyEvent)
    {
        super.handleKeyboard(keyEvent);
    }


    public void handleMoved(double normX, double normY, MouseEvent mouseEvent) {
        prevX = normX;
        prevY = normY;
    }

    public void setColor(Paint newColour) {
        super.setColor(newColour);
    }

    public void handleRectangle() {
        this.currentShape = XShape.RECTANGLE;
        super.handleRectangle();
    }

    public void handleSquare() {
        this.currentShape = XShape.SQUARE;
        super.handleSquare();
    }

    public void handleCircle() {
        this.currentShape = XShape.CIRCLE;
        super.handleCircle();
    }

    public void handleOval() {
        this.currentShape = XShape.OVAL;
        super.handleOval();
    }

    public void handleLine() {
        this.currentShape = XShape.LINE;
        super.handleLine();
    }
}
