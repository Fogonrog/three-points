package com.example.myapplication.Graphics;

public class Circle implements Drawable{
    private final Point center;
    private final float radius;

    public Circle(Point center, float radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public void drawOn(Canvas canvas) {
        canvas.origin.drawCircle(center.x, center.y, radius,canvas.paint);
    }
}
