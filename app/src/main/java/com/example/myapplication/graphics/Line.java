package com.example.myapplication.graphics;

import android.graphics.Path;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;

public final class Line implements Drawable {
    private final Point a;
    private final Point b;
    private final Path path;
    private final LineString jts;

    private Line(Point a, Point b) {
        this.a = a;
        this.b = b;
        this.path = new Path();
        path.moveTo(a.x(), a.y());
        path.lineTo(b.x(), b.y());

        var geometryFactory = new GeometryFactory();
        var coordinateList = new Coordinate[]{ new Coordinate(a.x(), a.y()),new Coordinate(b.x(), b.y())};
        jts = geometryFactory.createLineString(coordinateList);
    }

    public Path getPath() {
        return path;
    }

    public LineString getJts() {
        return jts;
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
