package com.example.myapplication.graphics;

import org.locationtech.jts.geom.Geometry;

public abstract class Figure {
    private Geometry jst;
    abstract Geometry jst();

    public abstract boolean intersects(Figure other);
}
