package com.example.project;


import javafx.scene.paint.Paint;

public class XSquare extends XShape{
    private double left, top, height, width, x, y;
    private double size;
    private Paint color;
    private int z;

    public XSquare(double xValue, double yValue, Paint color)
    {
        super(xValue, yValue, color);

        this.left = xValue;
        this.top = yValue;

        this.x = xValue;
        this.y= yValue;

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
    public double getSize() {
        return (this.size = Math.pow(this.height, 2)) * 500;
    }

    @Override
    public double getWidth() {
        return this.height;
    }

    @Override
    public double getHeight() {
        return this.height;
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
    public boolean checkHit(double x, double y) {
        return x >= left && x <= left + height && y >= top && y <= top + height;
    }

    @Override
    public XSquare update(double dX, double dY) {
        this.height = dX;
        this.width = dX;


        if (dX < this.x)
        {
            this.left = Math.max(dX, this.x - size);
        }
        else
        {
            this.left = this.x;
        }
        this.width = Math.min(Math.abs(dX - this.x), Math.abs(dY - this.y));


        if  (dY < this.y)
        {
            this.top = Math.max(dY, this.y - Math.min(Math.abs(dX - this.x), Math.abs(dY - this.y)));
        }
        else
        {
            this.top = this.y;
        }
        this.height = Math.min(Math.abs(dX - this.x), Math.abs(dY - this.y));

        return this;
    }

    @Override
    public void move(double dX, double dY) {
        this.left += dX;
        this.top += dY;
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
        return "XSquare";
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
