package com.example.myapplication.graphics;

import android.graphics.Path;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.operation.buffer.BufferOp;

public final class Circle extends Figure implements Drawable {
    private static final int DEFINITION = 20;
    private final Path path;
    private final Geometry jts;

    private Circle(Point center, float radius) {
        path = new Path();
        path.addCircle(center.x(), center.y(), radius, Path.Direction.CW);
        var geometryFactory = new GeometryFactory();
        var centerPoint = geometryFactory.createPoint(new Coordinate(center.x(), center.y()));
        this.jts = BufferOp.bufferOp(centerPoint, radius, DEFINITION);
    }

    public Path getPath() {
        return path;
    }

    @Override
    public void drawOn(Canva canvas) {
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
