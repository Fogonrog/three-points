package com.example.myapplication.Graphics;

public class Circle implements Drawable{
    private final Point center;
    private final Float radius;
    private final Integer color;

    public Circle(Point center, Float radius, Integer color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void drawOn(Canvas canvas) {
        canvas.paint.setColor(color);
        canvas.origin.drawCircle(center.x, center.y, radius,canvas.paint);
    }
}
