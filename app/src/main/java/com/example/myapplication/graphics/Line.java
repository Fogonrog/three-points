package com.example.myapplication.graphics;

import android.graphics.Path;

public final class Line implements Drawable {
    private final Point a;
    private final Point b;
    private final Path path;

    private Line(Point a, Point b) {
        this.a = a;
        this.b = b;
        this.path = new Path();
    }

    public Path getPath() {
        return path;
    }

    public static Line of(Point a, Point b) {
        return new Line(a, b);
    }

    @Override
    public void drawOn(Canva canvas) {
        path.moveTo(a.x(), a.y());
        path.lineTo(b.x(), b.y());
        canvas.origin.drawPath(path, canvas.paint);
    }
}
