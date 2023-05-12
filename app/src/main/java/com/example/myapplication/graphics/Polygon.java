package com.example.myapplication.graphics;

import android.graphics.Path;

import java.util.Collection;

public final class Polygon implements Drawable {
    private final Collection<Point> points;
    private final Path path;

    private Polygon(Collection<Point> points) {
        this.points = points;
        this.path = new Path();
    }

    public Path getPath() {
        return path;
    }

    public static Polygon from(Collection<Point> points) {
        return new Polygon(points);
    }

    @Override
    public void drawOn(Canva canvas) {
        var startPointAdded = false;
        for (Point point : points) {
            if (!startPointAdded) {
                path.moveTo(point.x(), point.y());
                startPointAdded = true;
            }
            path.lineTo(point.x(), point.y());
        }
        path.close();
        canvas.origin.drawPath(path, canvas.paint);
    }
}
