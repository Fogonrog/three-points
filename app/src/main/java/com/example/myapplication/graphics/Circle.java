package com.example.myapplication.graphics;

import android.graphics.Path;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.operation.buffer.BufferOp;

public final class Circle implements Drawable {
    private final Path path;
    private final org.locationtech.jts.geom.Geometry jts;

    private Circle(Point center, float radius) {
        path = new Path();
        path.addCircle(center.x(), center.y(), radius, Path.Direction.CW);
        var geometryFactory = new GeometryFactory();
        var centerPoint = geometryFactory.createPoint(new Coordinate(center.x(), center.y()));
        this.jts = BufferOp.bufferOp(centerPoint, radius, 20);
    }

    public Path getPath() {
        return path;
    }
    public org.locationtech.jts.geom.Geometry getJts() {
        return jts;
    }


    @Override
    public void drawOn(Canva canvas) {
        canvas.origin.drawPath(path, canvas.paint);
    }
}
