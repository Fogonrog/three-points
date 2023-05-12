package com.example.myapplication.graphics;

import android.graphics.Path;

public final class Circle implements Drawable {
    private final Point center;
    private final float radius;
    private final Path path;

    private Circle(Point center, float radius) {
        this.center = center;
        this.radius = radius;
        this.path = new Path();
    }

    public Path getPath() {
        return path;
    }

    @Override
    public void drawOn(Canva canvas) {
        path.addCircle(center.x(), center.y(), radius, Path.Direction.CW);
        canvas.origin.drawPath(path, canvas.paint);
    }
}
