package com.example.myapplication.graphics;

import android.graphics.Path;
import java.util.List;

public final class BrokenLine implements Drawable {
    private final List<Point> points;
    private final Path path;

    private BrokenLine(List<Point> points) {
        this.points = points;
        this.path = new Path();
    }

    public Path getPath() {
        return path;
    }

    public static BrokenLine of(List<Point> points) {
        return new BrokenLine(points);
    }

    @Override
    public void drawOn(Canva canvas) {
        path.moveTo(points.get(0).x(), points.get(0).y());
        for (int i = 1; i < points.size(); i++) {
            path.lineTo(points.get(i).x(), points.get(i).y());
        }
        canvas.origin.drawPath(path, canvas.paint);
    }
}

