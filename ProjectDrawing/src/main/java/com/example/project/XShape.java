package com.example.project;


import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

abstract class XShape extends Shape {
    private Paint color;
    private double left, top;
    private int z;

    public XShape(double x, double y, Paint color) {
        this.left = x;
        this.top = y;
        this.color = color;
        this.z = 0;
    }

    public abstract void setColor(Paint color);

    public abstract Paint getColor();

    public abstract String getShapeType();

    public abstract double getLeft();

    public abstract double getTop();

    public abstract double getWidth();

    public abstract double getHeight();

    public abstract void setLeft(double newLeft);

    public abstract void setTop(double newTop);

    public abstract void setWidth(double newWidth);

    public abstract void setHeight(double newHeight);

    public abstract double getSize();

    public abstract void move(double dX, double dY);

    public abstract boolean checkHit(double x, double y);

    public abstract XShape update(double dX, double dY);

    public abstract XShape selectedShape();

    public abstract boolean contains(double x, double y);

    public abstract void setZ(int Z);

    public abstract int getZ();
}
