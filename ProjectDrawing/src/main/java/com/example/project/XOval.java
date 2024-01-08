package com.example.project;


import javafx.scene.paint.Paint;

public class XOval extends XShape {
    private double left, top, height, width, x, y;
    private Paint color;
    private int z;

    public XOval(double xValue, double yValue, Paint color)
    {
        super(xValue, yValue, color);
        this.left = xValue;
        this.top = yValue;

        this.x = xValue;
        this.y = yValue;

        this.height = 0;
        this.width = 0;

        this.color = color;

        this.z = 0;
    }

    @Override
    public double getLeft()
    {
        return this.left;
    }

    @Override
    public double getTop()
    {
        return this.top;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    @Override
    public double getSize() {
        return (Math.PI * this.height * this.width) * 500;
    }

    public void setLeft(double newLeft)
    {
        this.left = newLeft;
    }

    @Override
    public void setTop(double newTop)
    {
        this.top = newTop;
    }

    @Override
    public void setHeight(double newHeight)
    {
        this.height = newHeight;
    }

    @Override
    public void setWidth(double newWidth)
    {
        this.width = newWidth;
    }

    @Override
    public void setColor(Paint color) {
        this.color = color;
    }

    @Override
    public Paint getColor() {
        return this.color;
    }

    @Override
    public String getShapeType() {
        return "XOval";
    }

    public double dist(double ovalX, double ovalY, double mouseX, double mouseY) {
        return Math.pow((mouseX-ovalX) / this.getWidth(), 2) + Math.pow((mouseY-ovalY) / this.getHeight(),
                2) - 1;
    }

    @Override
    public void move(double dX, double dY) {
        this.left += dX;
        this.top += dY;
    }

    @Override
    public boolean checkHit(double x, double y) {
        return x >= left && x <= left + this.width && y >= top && y <= top + this.height;
    }

    @Override
    public XOval update(double dX, double dY) {
        this.width = dX;
        this.height = dY;

        this.left = Math.min(dX, this.x);
        this.top = Math.min(dY, this.y);

        this.setWidth(Math.abs(dX - this.x));
        this.setHeight(Math.abs(dY - this.y));
        return this;
    }

    @Override
    public XShape selectedShape()
    {
        return this;
    }

    @Override
    public boolean contains(double x, double y) {
        return false;
    }

    @Override
    public void setZ(int ZScore) {
        this.z = ZScore;
    }

    @Override
    public int getZ() {
        return this.z;
    }
}
