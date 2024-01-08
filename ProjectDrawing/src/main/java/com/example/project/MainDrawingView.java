package com.example.project;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class MainDrawingView extends StackPane implements DrawingModelSubscriber, InteractionModelSubscriber{
    Canvas myCanvas;
    GraphicsContext gc;
    DrawingModel model;
    InteractionModel iModel;
    double boxLeft, boxTop, boxWidth, boxHeight;
    double width, height;

    public MainDrawingView(double w, double h) {
        width = w;
        height = h;
        myCanvas = new Canvas(width, height);
        gc = myCanvas.getGraphicsContext2D();

        this.getChildren().addAll(myCanvas);
        this.setStyle("-fx-background-color: lightgray");
        this.setMinSize(100, 100);
        this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.setPrefSize(500, 500);
    }

    public void setModel(DrawingModel newModel)
    {
        this.model = newModel;
    }

    public void setInteractionModel(InteractionModel iModel) {
        this.iModel = iModel;
    }

    public void setController(DrawingController controller) {
        //set up canvas event handling (only on left mouse click)
        myCanvas.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.PRIMARY)
                controller.handlePressedLeft((e.getX() + iModel.getCanvasLeft() * 20) / iModel.worldWidth,
                        (e.getY() + iModel.getCanvasTop() * 20) / iModel.worldHeight,
                        e);
            else
                controller.handlePressedRight((e.getX() + iModel.getCanvasLeft() * 20) / iModel.worldWidth,
                        (e.getY() + iModel.getCanvasTop() * 20) / iModel.worldHeight,
                        e);
        });
        myCanvas.setOnMouseDragged(e -> {
            if (e.getButton() == MouseButton.PRIMARY)
                controller.handleDraggedLeft((e.getX() + iModel.getCanvasLeft() * 20) / iModel.worldWidth,
                        (e.getY() + iModel.getCanvasTop() * 20) / iModel.worldHeight,
                        e);
            else
                controller.handleDraggedRight((e.getX() + iModel.getCanvasLeft() * 20) / iModel.worldWidth,
                        (e.getY() + iModel.getCanvasTop() * 20) / iModel.worldHeight,
                        e);
        });
        myCanvas.setOnMouseMoved(e -> {
            if (e.getButton() == MouseButton.PRIMARY)
                controller.handleMoved((e.getX() + iModel.getCanvasLeft() * 20) / iModel.worldWidth,
                        (e.getY() + iModel.getCanvasTop() * 20) / iModel.worldHeight,
                        e);
        });
        myCanvas.setOnMouseReleased(e -> {
            if (e.getButton() == MouseButton.PRIMARY)
                controller.handleReleasedLeft((e.getX() + iModel.getCanvasLeft() * 20) / iModel.worldWidth,
                        (e.getY() + iModel.getCanvasTop() * 20) / iModel.worldHeight,
                        e);
            else
                controller.handleReleasedRight((e.getX() + iModel.getCanvasLeft() * 20) / iModel.worldWidth,
                        (e.getY() + iModel.getCanvasTop() * 20) / iModel.worldHeight,
                        e);
        });
    }

    public void drawBounding(double first, double second, double third, double fourth,
                             GraphicsContext graphics_context, XShape shape)
    {
        //Bounding box
        graphics_context.setStroke(Color.RED);
        graphics_context.setLineWidth(1.5);
        graphics_context.setLineDashes(5, 5);
        graphics_context.strokeRect(first, second, third, fourth);

        //Controller circle at bottom right after certain size
        if (shape.getSize() > 1.5) {
            graphics_context.setLineDashes(0, 0);
            graphics_context.setFill(Color.GOLD);
            graphics_context.setLineWidth(2.0);
            graphics_context.setStroke(Color.BLACK);
            graphics_context.fillOval(first + third - 5, second + fourth - 5, 10, 10);
            graphics_context.strokeOval(first + third - 5, second + fourth - 5, 10, 10);
        }
    }

    public void drawShapes(XShape shape, double boxLeft, double boxTop, double boxWidth, double boxHeight,
                           GraphicsContext graphics_context)
    {
        graphics_context.setFill(shape.getColor());
        if (shape.getShapeType().equals("XRectangle") || shape.getShapeType().equals("XSquare"))
        {
            graphics_context.fillRect(boxLeft, boxTop, boxWidth, boxHeight);
            graphics_context.strokeRect(boxLeft, boxTop, boxWidth, boxHeight);
        }
        else if (shape.getShapeType().equals("XCircle") || shape.getShapeType().equals("XOval"))
        {
            graphics_context.fillOval(boxLeft, boxTop, boxWidth, boxHeight);
            graphics_context.strokeOval(boxLeft, boxTop, boxWidth, boxHeight);
        }
        else if (shape.getShapeType().equals("XLine"))
        {
            graphics_context.setStroke(shape.getColor());
            graphics_context.setLineWidth(1.5);
            graphics_context.strokeLine(boxLeft, boxTop, boxWidth, boxHeight);
        }
        else
        {
            System.out.println("Something went wrong!");
        }

        if (iModel.getSelectedShape()!= null && shape == iModel.getSelectedShape() ||
                model.getTemp() != null && shape == model.getTemp())
        {
            if (!shape.getShapeType().equals("XLine"))
            {
                //Drawing bounding box
                drawBounding(boxLeft - 2, boxTop - 2,boxWidth + 4, boxHeight + 4,
                        graphics_context, shape);
            }
            else
            {
                //Bounding for lines
                graphics_context.setStroke(Color.RED);
                graphics_context.setLineWidth(1.5);
                graphics_context.setLineDashes(5, 5);
                graphics_context.strokeLine(boxLeft, boxTop, boxWidth, boxHeight);

                graphics_context.setLineDashes(0, 0);
                graphics_context.setFill(Color.GOLD);
                graphics_context.setLineWidth(2.0);
                graphics_context.setStroke(Color.BLACK);
                graphics_context.fillOval(boxWidth - 5, boxHeight - 5, 10, 10);
                graphics_context.strokeOval(boxWidth - 5, boxHeight - 5, 10, 10);
            }
        }
    }

    public void draw(GraphicsContext graphics_context, double lf, double tp) {
        graphics_context.clearRect(lf, tp, myCanvas.getHeight(), myCanvas.getWidth());

        graphics_context.setLineDashes(0, 0);
        graphics_context.setStroke(Color.BLACK);
        graphics_context.setLineWidth(1.0);

        //Drawing temp shapes
        if (model.getTemp() != null)
        {
            boxLeft = model.getTemp().getLeft() * iModel.worldWidth - iModel.getCanvasLeft() * 20 + (lf * iModel.worldWidth);
            boxTop = model.getTemp().getTop() * iModel.worldHeight - iModel.getCanvasTop() * 20+ (tp * iModel.worldHeight);
            boxWidth = model.getTemp().getWidth() * iModel.worldWidth - iModel.getCanvasLeft() * 20;
            boxHeight = model.getTemp().getHeight() * iModel.worldHeight - iModel.getCanvasTop() * 20;

            drawShapes(model.getTemp(), boxLeft, boxTop, boxWidth, boxHeight, graphics_context);
        }

        //Drawing final shapes
        model.getShapes().forEach(item ->
        {
            boxLeft = (item.getLeft() * iModel.worldWidth - iModel.getCanvasLeft() * 20)
                    + (lf * iModel.worldWidth);
            boxTop = (item.getTop() * iModel.worldHeight - iModel.getCanvasTop() * 20)
                    + (tp * iModel.worldHeight);
            boxWidth = item.getWidth() * iModel.worldWidth - iModel.getCanvasLeft() * 20;
            boxHeight = item.getHeight() * iModel.worldHeight - iModel.getCanvasTop() * 20;

            switch (item) {

                case XRectangle rect -> drawShapes(rect, boxLeft, boxTop, boxWidth, boxHeight, graphics_context);

                case XSquare square -> drawShapes(square, boxLeft, boxTop, boxWidth, boxHeight, graphics_context);

                case XCircle circle -> drawShapes(circle, boxLeft, boxTop, boxWidth, boxHeight, graphics_context);

                case XOval oval -> drawShapes(oval, boxLeft, boxTop, boxWidth, boxHeight, graphics_context);

                case XLine line -> drawShapes(line, boxLeft, boxTop, boxWidth + lf * iModel.worldHeight,
                        boxHeight + tp * iModel.worldHeight, graphics_context);

                case XShape shape -> System.out.println("Something went wrong again!");
            }
        });

    }

    public void draw1(Canvas canvas, GraphicsContext graphics_context, double left, double top,
                      double width, double height) {
        graphics_context.clearRect(left, top, width, height);

        graphics_context.setLineDashes(0, 0);
        graphics_context.setStroke(Color.BLACK);
        graphics_context.setLineWidth(1.0);

        //Drawing temp shapes
        if (model.getTemp() != null)
        {
            boxLeft = model.getTemp().getLeft() * width;
            boxTop = model.getTemp().getTop() * height;
            boxWidth = model.getTemp().getWidth() * width;
            boxHeight = model.getTemp().getHeight() * height;

            drawShapes(model.getTemp(), boxLeft, boxTop, boxWidth, boxHeight, graphics_context);
        }

        //Drawing final shapes
        model.getShapes().forEach(item ->
        {
            boxLeft = item.getLeft() * width;
            boxTop = item.getTop() * height;
            boxWidth = item.getWidth() * width;
            boxHeight = item.getHeight() * height;

            switch (item) {

                case XRectangle rect -> drawShapes(rect, boxLeft, boxTop, boxWidth, boxHeight, graphics_context);

                case XSquare square -> drawShapes(square, boxLeft, boxTop, boxWidth, boxHeight, graphics_context);

                case XCircle circle -> drawShapes(circle, boxLeft, boxTop, boxWidth, boxHeight, graphics_context);

                case XOval oval -> drawShapes(oval, boxLeft, boxTop, boxWidth, boxHeight, graphics_context);

                case XLine line -> drawShapes(line, boxLeft, boxTop, boxWidth, boxHeight, graphics_context);

                case XShape shape -> System.out.println("Something went wrong again!");
            }
        });

    }

    @Override
    public void modelChanged() {
        draw(this.gc,
                iModel.getCanvasLeft(),
                iModel.getCanvasTop());
    }

    @Override
    public void iModelChanged() {
        draw(this.gc,
                iModel.getCanvasLeft(),
                iModel.getCanvasTop());
    }
}
