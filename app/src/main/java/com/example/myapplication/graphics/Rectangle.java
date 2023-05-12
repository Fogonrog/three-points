package com.example.myapplication.graphics;

import android.graphics.Path;

public final class Rectangle implements Drawable {
    private final Point a;
    private final Point b;
    private final Path path;

    private Rectangle(Point a, Point b) {
        this.a = a;
        this.b = b;
        this.path = new Path();
    }

    public Path getPath() {
        return path;
    }

    public static Rectangle from(Point a, Point b) {
        return new Rectangle(a, b);
    }

    @Override
    public void drawOn(Canva canvas) {
        path.addRect(a.x(), a.y(), b.x(), b.y(), Path.Direction.CW);
        canvas.origin.drawPath(path, canvas.paint);
    }
}
