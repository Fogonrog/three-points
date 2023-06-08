package com.example.myapplication.logic.graphics;

import android.graphics.Path;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

@JsonTypeName("Line")
public final class Line implements Drawable {
    private final Point a;
    private final Point b;
    private final Path path;
    private final Geometry jts;

    @JsonCreator
    private Line(@JsonProperty("a") Point a, @JsonProperty("b") Point b) {
        this.a = a;
        this.b = b;
        this.path = new Path();
        path.moveTo(a.x(), a.y());
        path.lineTo(b.x(), b.y());

        var geometryFactory = new GeometryFactory();
        var coordinateList = new Coordinate[]{
                new Coordinate(a.x(), a.y()),
                new Coordinate(b.x(), b.y())
        };
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
    public Geometry jts() {
        return jts;
    }

    @Override
    public boolean intersects(Drawable other) {
        return jts.intersects(other.jts());
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }
}
