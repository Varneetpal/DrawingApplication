package com.example.project;



import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.*;

public class MainUIView extends GridPane{
    private ColourToolbarView color_bar;
    private ShapeToolBarView tool_bar;
    public MainDrawingView main_draw;
    public MiniDrawingView mini_draw;

    public MainUIView(){
        //Create color toolbar
        this.color_bar = new ColourToolbarView();

        //Create shape toolbar
        this.tool_bar = new ShapeToolBarView();

        StackPane drawingPanes = new StackPane();

        //Create main drawing canvas
        this.main_draw = new MainDrawingView(500, 500);

        //Create mini drawing canvas
        this.mini_draw = new MiniDrawingView();
        Group smallDraw = new Group(mini_draw);

        //Layout
        drawingPanes.getChildren().addAll(main_draw, smallDraw);
        StackPane.setAlignment(smallDraw, Pos.TOP_LEFT);
        AnchorPane.setTopAnchor(drawingPanes, 0.0);
        AnchorPane.setLeftAnchor(drawingPanes, 60.0);
        AnchorPane.setRightAnchor(drawingPanes, 62.5);
        AnchorPane.setBottomAnchor(drawingPanes, 0.0);

        GridPane.setConstraints(tool_bar, 0, 0);
        GridPane.setConstraints(drawingPanes, 1, 0);
        GridPane.setConstraints(color_bar, 2, 0);

        this.getChildren().addAll(tool_bar, drawingPanes, color_bar);
        ColumnConstraints drawingPaneColumnRestraints = new ColumnConstraints(main_draw.getMinWidth(), main_draw.getPrefWidth(), Double.MAX_VALUE);
        drawingPaneColumnRestraints.setHgrow(Priority.ALWAYS);
        this.getColumnConstraints().addAll(new ColumnConstraints(tool_bar.getMaxWidth()), drawingPaneColumnRestraints);
        this.setStyle("-fx-background-color: lightgray");
        this.setMinSize(100, 100);
        this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.setPrefSize(623, 500);
    }

    public void setModel(DrawingModel newModel)
    {
        this.main_draw.setModel(newModel);
        this.mini_draw.setModel(newModel);
    }

    public void setIModel(InteractionModel newModel)
    {
        this.color_bar.setInteractionModel(newModel);
        this.tool_bar.setModel(newModel);
        this.main_draw.setInteractionModel(newModel);
        this.mini_draw.setInteractionModel(newModel);
    }

    public void setController(DrawingController controller){
        this.tool_bar.setController(controller);
        this.color_bar.setController(controller);
        this.main_draw.setController(controller);
    }

    public ColourToolbarView getColorBar()
    {
        return this.color_bar;
    }

    public ShapeToolBarView getToolBar()
    {
        return this.tool_bar;
    }

    public MainDrawingView getMainDrawingView()
    {
        return this.main_draw;
    }

    public MiniDrawingView getMiniDrawingView()
    {
        return this.mini_draw;
    }
}
