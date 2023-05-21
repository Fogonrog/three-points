package com.example.myapplication.graphics;

import android.graphics.Path;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import java.util.List;

public final class Polygon implements Drawable {
    private final Path path;
    private final org.locationtech.jts.geom.Polygon jts;

    private Polygon(List<Point> points) {
        path = new Path();
        var startPointAdded = false;
        var geometryFactory = new GeometryFactory();
        var coordinateList = new Coordinate[points.size()];
        var iterator = 0;
        for (Point point : points) {
            if (!startPointAdded) {
                path.moveTo(point.x(), point.y());
                coordinateList[iterator] = new Coordinate(point.x(), point.y());
                startPointAdded = true;
                iterator++;
                continue;
            }
            path.lineTo(point.x(), point.y());
            coordinateList[iterator] = new Coordinate(point.x(), point.y());
            iterator++;
        }
        this.jts = geometryFactory.createPolygon(coordinateList);
    }

    public Path getPath() {
        return path;
    }

    public org.locationtech.jts.geom.Polygon getJts() {
        return jts;
    }

    public static Polygon from(List<Point> points) {
        return new Polygon(points);
    }

    @Override
    public void drawOn(Canva canvas) {
        canvas.origin.drawPath(path, canvas.paint);
    }
}
