package com.example.project;


import javafx.scene.paint.Paint;

public class XLine extends XShape{
    private double left, top, right, bottom;
    private double ratioA, ratioB, ratioC;
    private double tolerance;
    private double length;
    private Paint color;
    private int z;

    public XLine(double xValue, double yValue, Paint color) {

        super(xValue, yValue, color);

        this.left = xValue;
        this.top = yValue;

        this.right = xValue;
        this.bottom = yValue;

        this.color = color;

        this.tolerance = 10;
        this.length = dist(left, top, right, bottom);

        ratioA = 0;
        ratioB = 0;
        ratioC = 0;

        this.z = 0;
    }

    public double getRatioA()
    {
        return this.ratioA;
    }

    public double getRatioB()
    {
        return this.ratioB;
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
        return "XLine";
    }

    @Override
    public double getLeft() {
        return this.left;
    }

    @Override
    public double getTop() {
        return this.top;
    }

    @Override
    public double getWidth() {
        return this.right;
    }

    @Override
    public double getHeight() {
        return this.bottom;
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
        this.right = newHeight;
    }

    @Override
    public void setWidth(double newWidth)
    {
        this.bottom = newWidth;
    }

    @Override
    public double getSize() {
        return dist(this.left, this.top, this.right, this.bottom) * 500;
    }


//    public boolean contains(double x, double y, double x2, double y2)
//    {
//        return Math.abs(dist(x,y,x2,y2)) < tolerance &&
//                dist(x,y, lineX, lineY) < length &&
//                dist(x,y, mouse_x, mouse_y) < length;
//    }

    public double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public double distanceFromLine(double x, double y)
    {
        return ratioA * x + ratioB * y + ratioC;
    }

    @Override
    public void move(double dX, double dY) {
        this.left += dX;
        this.top += dY;
        this.right += dX;
        this.bottom += dY;
    }

    @Override
    public boolean checkHit(double x, double y) {
        return (Math.abs(distanceFromLine(x, y)) * 500) < 1;
    }

    @Override
    public XLine update(double dX, double dY) {
        this.right = dX;
        this.bottom = dY;
        this.length = dist(this.left, this.top, this.right, this.bottom);

        ratioA = (this.top-this.bottom) / length;
        ratioB = (this.right - this.left) / length;
        ratioC = -1 * ((this.top - this.bottom) * this.left + (this.right - this.left) * this.top) / length;

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
