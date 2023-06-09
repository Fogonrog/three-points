package com.example.myapplication.logic.graphics;

import android.graphics.Path;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

import java.util.Arrays;
import java.util.List;

@JsonTypeName("Polygon")
public final class Polygon implements Drawable {
    private final Path path;
    private final Geometry jts;
    private final List<Point> points;

    @JsonCreator
    private Polygon(@JsonProperty("points") List<Point> points) {
        path = new Path();
        this.points = points;
        GeometryFactory geometryFactory = new GeometryFactory();

        Coordinate[] coordinateList = points.stream()
                .map(p -> new Coordinate(p.x(), p.y()))
                .toArray(Coordinate[]::new);

        if (coordinateList.length > 0) {
            path.moveTo((float) coordinateList[0].x, (float) coordinateList[0].y);
            Arrays.stream(coordinateList).skip(1)
                    .forEach(c -> path.lineTo((float) c.x, (float) c.y));
        }

        this.jts = geometryFactory.createPolygon(coordinateList);

    }

    public static Polygon from(List<Point> points) {
        return new Polygon(points);
    }

    public Path getPath() {
        return path;
    }

    @Override
    public Geometry jts() {
        return jts;
    }

    @Override
    public boolean intersects(Drawable other) {
        return jts.intersects(other.jts());
    }

    @Override
    public void drawOn(Canva canvas) {
        canvas.origin.drawPath(path, canvas.paint);
    }

    public List<Point> getPoints() {
        return points;
    }
}
