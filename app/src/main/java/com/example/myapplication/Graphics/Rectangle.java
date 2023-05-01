package com.example.myapplication.Graphics;

public class Rectangle implements Drawable {
    private final Point a;
    private final Point b;
    private final Integer color;

    public Rectangle(Point a, Point b, Integer color) {
        this.a = a;
        this.b = b;
        this.color = color;
    }

    @Override
    public void drawOn(Canvas canvas) {
        canvas.paint.setColor(color);
        canvas.origin.drawRect(a.x, a.y, b.x, b.y, canvas.paint);
    }
}
