package com.example.myapplication.graphics;

import android.graphics.Path;

import com.example.myapplication.expressions.Function;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateSequence;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.impl.CoordinateArraySequence;

import java.util.ArrayList;
import java.util.List;

public final class FunctionGraph implements Drawable {
    private static final float MAX_VALUE_X = 350F;
    private static final float STEP_X = 0.1F;
    private final Function function;
    private final Path path;
    private final org.locationtech.jts.geom.LineString jts;


    public FunctionGraph(Function function, float widthMlt, float heightMlt) {
        this.function = function;
        this.path = new Path();
        var geometryFactory = new GeometryFactory();
        List<Coordinate> pointList = new ArrayList<>();
        path.moveTo(-MAX_VALUE_X, (float) (function.evaluate(-MAX_VALUE_X)));
        for (float i = -MAX_VALUE_X + STEP_X; i < MAX_VALUE_X; i += STEP_X) {
            path.lineTo(i * widthMlt, function.evaluate(i) * heightMlt);
            pointList.add(new Coordinate(i * widthMlt, function.evaluate(i) * heightMlt));
        }
        this.jts = geometryFactory.createLineString(new CoordinateArraySequence(pointList.toArray(new Coordinate[0])));
    }

    public Path getPath() {
        return path;
    }
    public org.locationtech.jts.geom.LineString getJts() {
        return jts;
    }


    public static FunctionGraph from(Function function, float widthMlt, float heightMlt) {
        return new FunctionGraph(function, widthMlt, heightMlt);
    }
    @Override
    public void drawOn(Canva canvas) {
        canvas.origin.drawPath(path, canvas.paint);
    }
}
