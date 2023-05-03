package com.example.myapplication.Graphics;

public class Rectangle implements Drawable {
    private final Point a;
    private final Point b;

    public Rectangle(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void drawOn(Canvas canvas) {
        canvas.origin.drawRect(a.x, a.y, b.x, b.y, canvas.paint);
    }
}
