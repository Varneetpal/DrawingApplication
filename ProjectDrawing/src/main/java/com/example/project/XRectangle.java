package com.example.project;


import javafx.scene.paint.Paint;

public class XRectangle extends XShape{
    private double left, top, x, y;
    private double height, width;
    private Paint color;
    private int z;

    public XRectangle(double xValue, double yValue, Paint color)
    {
        super(xValue, yValue, color);
        this.left = xValue;
        this.top = yValue;
        this.x = xValue;
        this.y= yValue;

        this.width = 0;
        this.height = 0;

        this.color = color;
    }

    @Override
    public double getLeft()
    {
        return this.left;
    }

    @Override
    public void setLeft(double newLeft)
    {
        this.left = newLeft;
    }

    @Override
    public double getTop()
    {
        return this.top;
    }

    @Override
    public void setTop(double newTop)
    {
        this.top = newTop;
    }

    @Override
    public double getSize() {
        return (this.width * this.height) * 500;
    }

    @Override
    public double getHeight()
    {
        return this.height;
    }

    @Override
    public void setHeight(double newHeight)
    {
        this.height = newHeight;
    }

    @Override
    public double getWidth()
    {
        return this.width;
    }

    @Override
    public void setWidth(double newWidth)
    {
        this.width = newWidth;
    }

    @Override
    public void setColor(Paint color)
    {
        this.color = color;
    }

    @Override
    public Paint getColor() {
        return this.color;
    }

    @Override
    public String getShapeType() {
        return "XRectangle";
    }

    @Override
    public boolean checkHit(double x, double y) {
        return x >= left && x <= left + this.width && y >= top && y <= top + this.height;
    }

    @Override
    public void move(double dX, double dY) {
        this.left += dX;
        this.top += dY;
    }

    @Override
    public XRectangle update(double dX, double dY) {
        this.width = dX;
        this.height = dY;

//        if  (this.width < this.x) {
//            this.setLeft(this.width);
//        }
//        else if  (this.width >= this.x) {
//            this.setLeft(this.x);
//        }
//
//        if  (this.height >= this.y) {
//            this.setTop(this.y);
//        }
//        else {
//            this.setTop(this.height);
//        }

        this.left = Math.min(this.width, this.x);
        this.top = Math.min(this.height, this.y);
        this.setWidth(Math.abs(this.width - this.x));
        this.setHeight(Math.abs(this.height - this.y));

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
