package com.example.project;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DrawingApp extends Application {
    @Override
    public void start(Stage stage){
        VBox root = new VBox();

        //set up MVC
        MainUIView mainUI = new MainUIView();

        DrawingModel model = new DrawingModel();
        InteractionModel iModel = new InteractionModel();

        DrawingController controller = new DrawingController();
        MiniDrawingController mini_controller = new MiniDrawingController();

        //Wire MVC up
        mainUI.setIModel(iModel);
        mainUI.setModel(model);
        mainUI.setController(controller);
        mainUI.getMiniDrawingView().setController(mini_controller);

        controller.setModel(model);
        controller.setInteractionModel(iModel);

        mini_controller.setModel(model);
        mini_controller.setInteractionModel(iModel);

        model.addSubscriber(mainUI.getMainDrawingView());
        model.addSubscriber(mainUI.getMiniDrawingView());

        iModel.addSubscriber(mainUI.getColorBar());
        iModel.addSubscriber(mainUI.getToolBar());
        iModel.addSubscriber(mainUI.getMainDrawingView());
        iModel.addSubscriber(mainUI.getMiniDrawingView());

        root.getChildren().add(mainUI);

        //Display
        Scene scene = new Scene(root);

        //Handle shape deletion
        scene.setOnKeyPressed(controller::handleKeyboard);

        //Moving around on the canvas
        scene.setOnMouseMoved(e -> controller.handleMoved(
                (e.getX() + iModel.getCanvasLeft() * 20) / iModel.worldWidth,
                (e.getY() + iModel.getCanvasTop() * 20) / iModel.worldHeight, e));

        //Handle window resizing
        scene.widthProperty().addListener((observable, oldVal, newVal) -> controller.handleWindowResizeWidth((Double) newVal));
        scene.heightProperty().addListener((observable, oldVal, newVal) -> controller.handleWindowResizeHeight((Double) newVal));

        stage.setTitle("Assignment 3");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
