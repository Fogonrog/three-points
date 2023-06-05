package com.example.myapplication.graphics;

import android.graphics.Path;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.operation.buffer.BufferOp;

@JsonTypeName("Circle")
public final class Circle implements Drawable {
    private static final int DEFINITION = 20;
    private final Point center;
    private final float radius;
    private final Path path;
    private final Geometry jts;

    @JsonCreator
    public Circle(@JsonProperty("center") Point center,
                  @JsonProperty("radius") float radius) {
        path = new Path();
        this.center = center;
        this.radius = radius;
        path.addCircle(center.x(), center.y(), radius, Path.Direction.CW);
        var geometryFactory = new GeometryFactory();
        var centerPoint = geometryFactory.createPoint(new Coordinate(
                center.x(), center.y()));
        this.jts = BufferOp.bufferOp(centerPoint, radius, DEFINITION);
    }

    public static Circle from(Point center, float radius) {
        return new Circle(center, radius);
    }

    public Path getPath() {
        return path;
    }

    @Override
    public void drawOn(Canva canvas) {
        canvas.origin.drawPath(path, canvas.paint);
    }

    @Override
    public Geometry jts() {
        return jts;
    }

    @Override
    public boolean intersects(Drawable other) {
        return jts.intersects(other.jts());
    }

    public Point getCenter() {
        return center;
    }

    public float getRadius() {
        return radius;
    }
}
