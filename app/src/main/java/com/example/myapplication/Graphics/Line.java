package com.example.myapplication.Graphics;

public class Line implements Drawable {
    private final Point a;
    private final Point b;
    private final Integer color;

    public Line(Point a, Point b, Integer color) {
        this.a = a;
        this.b = b;
        this.color = color;
    }

    @Override
    public void drawOn(Canvas canvas) {
        canvas.paint.setColor(color);
        canvas.origin.drawLine(a.x, a.y, b.x, b.y, canvas.paint);
    }
}
