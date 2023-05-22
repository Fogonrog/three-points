package com.example.myapplication.graphics;

import android.graphics.Path;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;

public final class Line extends Figure implements Drawable {
    private final Point a;
    private final Point b;
    private final Path path;
    private final Geometry jts;

    private Line(Point a, Point b) {
        this.a = a;
        this.b = b;
        this.path = new Path();
        path.moveTo(a.x(), a.y());
        path.lineTo(b.x(), b.y());

        var geometryFactory = new GeometryFactory();
        var coordinateList = new Coordinate[]{new Coordinate(a.x(), a.y()), new Coordinate(b.x(), b.y())};
        jts = geometryFactory.createLineString(coordinateList);
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

    @Override
    Geometry jst() {
        return jts;
    }

    @Override
    public boolean intersects(Figure other) {
        return jts.intersects(other.jst());
    }
}
