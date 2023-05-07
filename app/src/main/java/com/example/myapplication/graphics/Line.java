package com.example.myapplication.graphics;

public final class Line implements Drawable {
    private final Point a;
    private final Point b;

    public Line(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    public static Line of(Point a, Point b) {
        return new Line(a, b);
    }

    @Override
    public void drawOn(Canva canvas) {
        canvas.origin
                .drawLine(a.x(), a.y(),
                        b.x(), b.y(), canvas.paint);
    }
}
