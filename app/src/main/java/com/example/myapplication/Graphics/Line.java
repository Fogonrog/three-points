package com.example.myapplication.Graphics;

public class Line implements Drawable {
    private final Point a;
    private final Point b;

    public Line(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void drawOn(Canvas canvas) {
        canvas.origin.drawLine(a.x, a.y, b.x, b.y, canvas.paint);
    }
}
