package com.example.myapplication.graphics;

import android.graphics.Path;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;

import java.util.List;

public final class BrokenLine implements Drawable {
    private final Path path;
    private final org.locationtech.jts.geom.LineString jts;

    private BrokenLine(List<Point> points) {
        this.path = new Path();
        var geometryFactory = new GeometryFactory();
        var coordinates = new Coordinate[points.size()];
        path.moveTo(points.get(0).x(), points.get(0).y());
        for (int i = 1; i < points.size(); i++) {
            path.lineTo(points.get(i).x(), points.get(i).y());
            coordinates[i]= new Coordinate(points.get(i).x(), points.get(i).y());
        }
        path.close();
        this.jts = geometryFactory.createLineString(coordinates);
    }

    public Path getPath() {
        return path;
    }
    public org.locationtech.jts.geom.LineString getJts() {
        return jts;
    }


    public static BrokenLine of(List<Point> points) {
        return new BrokenLine(points);
    }

    @Override
    public void drawOn(Canva canvas) {
        canvas.origin.drawPath(path, canvas.paint);
    }
}

