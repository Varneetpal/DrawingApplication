package com.example.project;


import javafx.scene.paint.Paint;

public class XCircle extends XShape{
    private double left, top, x, y;
    private double height, width;
    private Paint color;
    private int z;

    public XCircle(double xValue, double yValue, Paint color)
    {
        super(xValue, yValue, color);
        this.left = xValue;
        this.top = yValue;

        this.x = xValue;
        this.y = yValue;

        this.height = 0;

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
    public double getSize() {
        return (Math.PI * Math.pow(this.height, 2)) * 500;
    }

    @Override
    public double getWidth()
    {
        return this.height;
    }

    @Override
    public double getHeight()
    {
        return this.height;
    }

    @Override
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
        return "XCircle";
    }


    public double dist(double circleX, double circleY, double mouseX, double mouseY) {
        return Math.sqrt(Math.pow(mouseX - circleX,2) + Math.pow(mouseY - circleY,2));
    }

    @Override
    public boolean checkHit(double x, double y) {
        return x >= left && x <= left + height && y >= top && y <= top + height;
    }

    @Override
    public XCircle update(double dX, double dY) {
        this.height = dX;
        this.width = dX;

        double size = Math.min( Math.abs(dX - this.x), Math.abs(dY - this.y));

        if (dX < this.x)
        {
            this.left = Math.max(dX, this.x - size);
        }
        else {
            this.left = this.x;
        }
        this.width = size;


        if  (dY < this.y)
        {
            this.top = Math.max(dY, this.y - size);
        }
        else
        {
            this.top = this.y;
        }
        this.height = size;

        return this;
    }

    @Override
    public void move(double dX, double dY) {
        this.left += dX;
        this.top += dY;
    }

    @Override
    public XShape selectedShape()
    {
        return this;
    }

    @Override
    public boolean contains(double x, double y) {
        double cx = this.left + (Math.PI * Math.pow(this.height, 2))/2;
        double cy = this.top + (Math.PI * Math.pow(this.height, 2))/2;
        return Math.hypot(x-cx,y-cy) <= (Math.PI * Math.pow(this.height, 2))/2;
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
