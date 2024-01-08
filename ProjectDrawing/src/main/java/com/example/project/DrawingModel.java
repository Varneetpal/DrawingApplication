package com.example.project;


import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DrawingModel {
    private ArrayList<XShape> shapes;
    private ArrayList<DrawingModelSubscriber> subs;
    private XShape tempShape;
    public XRectangle selectedArea;
    private int nextZ;

    public DrawingModel() {
        shapes = new ArrayList<>();
        subs = new ArrayList<>();
        tempShape = null;
        selectedArea = new XRectangle(0, 0,  Color.rgb(211, 211, 211, 0.35));
        selectedArea.update(0.25, 0.25);
    }

    public void addSubscriber(DrawingModelSubscriber aSub) {
        subs.add(aSub);
    }

    public void notifySubscribers() {
        subs.forEach(sub -> sub.modelChanged());
    }

    public void deleteShape(XShape shape) {
        nextZ--;
        shapes.remove(shape);
        notifySubscribers();
    }

    public List<XShape> getShapes() {
        return shapes;
    }

    public void addShape(XShape shape) {
        nextZ++;
        shapes.add(shape);
        notifySubscribers();
    }

    public XShape whichShape(double x, double y) {
        XShape found;
        for (XShape shape : shapes) {
            if (shape.checkHit(x,y)) {
                nextZ++;
                shape.setZ(nextZ);
                found = shape;
                return found;
            }
        }
        return null;
    }

    public boolean checkHit(double x, double y) {
        return shapes.stream().anyMatch(s -> s.checkHit(x, y));
    }

    public void setAreaSelected(XRectangle area) {
        this.selectedArea = area;
    }

    public boolean checkAreaSelected(double x, double y) {
        return this.selectedArea.checkHit(x, y);
    }

    public XRectangle getSelectedArea(double x, double y) {
        return this.selectedArea;
    }

    public boolean checkResize(XShape shape, double x, double y)
    {
        if (!shape.getShapeType().equals("XLine"))
        {
            double boxLeft = shape.getLeft() * 500 - 2;
            double boxTop = shape.getTop() * 500 - 2;
            double boxWidth = shape.getWidth() * 500 + 4;
            double boxHeight = shape.getHeight() * 500 + 4;

            return (x * 500) >= (boxLeft + boxWidth - 5) && (x * 500) <= (boxLeft + boxWidth - 5) + 10 && (y * 500) >= (boxTop + boxHeight - 5)
                    && (y * 500) <= (boxTop + boxHeight - 5) + 10;
        }
        else
        {
            double boxWidth = shape.getWidth() * 500;
            double boxHeight = shape.getHeight() * 500;

            return (x * 500) >= (boxWidth - 5) && (x * 500) <= (boxWidth - 5) + 10 && (y * 500) >= (boxHeight - 5)
                    && (y * 500) <= (boxHeight - 5) + 10;
        }
    }

    public void moveShape(XShape shape, double dX, double dY) {
        shape.move(dX, dY);
        notifySubscribers();
    }


    public void selectedShape(XShape shape)
    {
        shape.selectedShape();
        notifySubscribers();
    }

    public void selectedArea(XRectangle area)
    {
        area.selectedShape();
        notifySubscribers();
    }

    public void updateShape(XShape shape, double dX, double dY)
    {
        shape.update(dX, dY);
        notifySubscribers();
    }

    public void setTemp(XShape temp)
    {
        this.tempShape = temp;
        notifySubscribers();
    }

    public XShape getTemp()
    {
        return this.tempShape;
    }

    public void sortByZScore()
    {
        shapes.sort(Comparator.comparingInt(XShape::getZ));
        notifySubscribers();
    }
}
