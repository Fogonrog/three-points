package com.example.myapplication.graphics;

import android.graphics.Path;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

import java.util.List;

@JsonTypeName("BrokenLine")
public final class BrokenLine extends Figure implements Drawable {
    private final Path path;
    private final Geometry jts;
    private final List<Point> points;

    @JsonCreator
    public BrokenLine(@JsonProperty("points") List<Point> points) {
        this.path = new Path();
        this.points = points;
        var geometryFactory = new GeometryFactory();
        var coordinates = new Coordinate[points.size()];
        path.moveTo(points.get(0).x(), points.get(0).y());
        for (int i = 1; i < points.size(); i++) {
            path.lineTo(points.get(i).x(), points.get(i).y());
            coordinates[i] = new Coordinate(points.get(i).x(), points.get(i).y());
        }
        path.close();
        this.jts = geometryFactory.createLineString(coordinates);
    }

    public static BrokenLine of(List<Point> points) {
        return new BrokenLine(points);
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

    public List<Point> getPoints() {
        return points;
    }
}

