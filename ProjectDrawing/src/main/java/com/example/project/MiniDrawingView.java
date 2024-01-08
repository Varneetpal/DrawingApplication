package com.example.project;



import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class MiniDrawingView extends MainDrawingView implements DrawingModelSubscriber, InteractionModelSubscriber{
    Canvas miniCanvas;
    GraphicsContext miniGC;
    DrawingModel draw_model;
    InteractionModel iModel;
    double width = 2000;
    double height = 2000;
    XRectangle selectedArea;
    StackPane container;

    public MiniDrawingView() {
        super(500, 500);
        container = new StackPane();

        miniCanvas = new Canvas(width, height);
        miniGC = miniCanvas.getGraphicsContext2D();
        miniCanvas.setStyle("-fx-background-color: darkgray");

        selectedArea = new XRectangle(0, 0, Color.rgb(211, 211, 211, 0.35));

        container.getChildren().addAll(miniCanvas);
        container.toFront();

        this.getChildren().add(container);
        this.toFront();
        this.setMinSize(20, 20);
        this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.setPrefSize(width, height);
        this.setScaleX(0.05);
        this.setScaleY(0.05);
        this.setStyle("-fx-background-color: darkgray");
    }

    public void setModel(DrawingModel newModel)
    {
        super.model = newModel;
        this.draw_model = newModel;
        drawSelectedArea();
    }

    public void setInteractionModel(InteractionModel newIModel)
    {
        super.iModel = newIModel;
        this.iModel = newIModel;
    }

    public void setController(MiniDrawingController controller)
    {
        this.miniCanvas.setOnMousePressed(e -> {
            //left click
            if (e.getButton() == MouseButton.PRIMARY)
            {
                controller.handlePressedLeft(e.getX() / width,e.getY() / height,e);
            }
            //right click
            else if (e.getButton() == MouseButton.SECONDARY) {
                controller.handlePressedRight(e.getX() / width,e.getY() / height,e);
            }
        });
        this.miniCanvas.setOnMouseDragged(e -> {
            //left drag
            if (e.getButton() == MouseButton.PRIMARY) {
                controller.handleDraggedLeft(e.getX() / width,e.getY() / height,e);
            }
            //right drag
            else if (e.getButton() == MouseButton.SECONDARY) {
                controller.handleDraggedRight(e.getX() / width,e.getY() / height,e);
            }
        });

        this.miniCanvas.setOnMouseMoved(e -> {
            if (e.getButton() == MouseButton.PRIMARY)
                controller.handleMoved(e.getX() / width,e.getY() / height,e);
        });

        this.miniCanvas.setOnMouseReleased(e -> {
            if (e.getButton() == MouseButton.PRIMARY)
                controller.handleReleasedLeft(e.getX() / width,e.getY() / height,e);
            else
                controller.handleReleasedRight(e.getX() / width,e.getY() / height,e);
        });
    }

    public void drawBounding(double first, double second, double third, double fourth,
                             GraphicsContext graphics_context, XShape shape)
    {
        super.drawBounding(first, second, third, fourth, graphics_context, shape);
    }

    public void drawShapes(XShape shape, double boxLeft, double boxTop, double boxWidth, double boxHeight,
                           GraphicsContext graphics_context)
    {
        super.drawShapes(shape, boxLeft, boxTop, boxWidth, boxHeight, graphics_context);
    }

    public void draw(GraphicsContext graphics_context) {
        super.draw(graphics_context, 0, 0);
    }

    private void drawSelectedArea() {
        double box_Left = draw_model.selectedArea.getLeft() * width;
        double box_Top = draw_model.selectedArea.getTop() * height;
        double box_Width = draw_model.selectedArea.getWidth() * width;
        double box_Height = draw_model.selectedArea.getHeight() * height;

        miniGC.setStroke(Color.YELLOW);
        miniGC.setLineWidth(16);
        miniGC.setFill(draw_model.selectedArea.getColor());
        miniGC.fillRect(
                box_Left, box_Top, box_Width, box_Height
        );
        miniGC.strokeRect(
                box_Left, box_Top, box_Width, box_Height
        );
    }

    @Override
    public void modelChanged() {
        draw(this.miniGC);
        //drawSelectedArea();
    }

    @Override
    public void iModelChanged()
    {
        draw(this.miniGC);
        draw_model.selectedArea.move(-iModel.getCanvasLeft()/20, -iModel.getCanvasTop()/20); //needs attention
        drawSelectedArea();
    }

}
