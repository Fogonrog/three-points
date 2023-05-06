package com.example.myapplication.graphics;

public final class Rectangle implements Drawable {
    private final Point a;
    private final Point b;

    public Rectangle(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void drawOn(Canvas canvas) {
        canvas.getCanvas()
                .drawRect(a.x(), a.y(),
                          b.x(), b.y(), canvas.getPaint());
    }
}
