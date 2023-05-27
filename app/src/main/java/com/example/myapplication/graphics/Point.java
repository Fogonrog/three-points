package com.example.myapplication.graphics;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Point")
public final class Point {
    private final float x;
    private final float y;

    @JsonCreator
    public Point(@JsonProperty("x") float x, @JsonProperty("y") float y) {
        this.x = x;
        this.y = y;
    }

    public float x() {
        return x;
    }

    public float y() {
        return y;
    }

    @JsonProperty("class")
    public String getType() {
        return "Point";
    }

    public static Point of(float x, float y) {
        return new Point(x, y);
    }
}
