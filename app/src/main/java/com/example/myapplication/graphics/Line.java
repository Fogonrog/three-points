package com.example.myapplication.graphics;

public final class Line implements Drawable {
    private final Point a;
    private final Point b;

    public Line(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    public static Line from(Point a, Point b) {
        return new Line(a, b);
    }

    @Override
    public void drawOn(Canvas canvas) {
        canvas.getCanvas()
                .drawLine(a.x(), a.y(),
                        b.x(), b.y(), canvas.getPaint());
    }
}
