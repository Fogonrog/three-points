package com.example.myapplication.graphics;

import android.graphics.Path;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public final class Polygon extends Figure implements Drawable {
    private final Path path;
    private final Geometry jts;

    private Polygon(List<Point> points) {
            path = new Path();
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

    public Path getPath() {
        return path;
    }

    public static Polygon from(List<Point> points) {
        return new Polygon(points);
    }

    @Override
    Geometry jst() {
        return jts;
    }

    @Override
    public boolean intersects(Figure other) {
        return jts.intersects(other.jst());
    }

    @Override
    public void drawOn(Canva canvas) {
        canvas.origin.drawPath(path, canvas.paint);
    }
}
