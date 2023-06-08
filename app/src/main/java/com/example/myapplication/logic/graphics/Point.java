package com.example.myapplication.logic.graphics;

import static com.example.myapplication.logic.expressions.Functions.expr;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Point")
public final class Point {
    private String strX;
    private String strY;

    private final float x;
    private final float y;

    @JsonCreator
    public Point(@JsonProperty("strX") String strX,
                 @JsonProperty("strY") String strY) {
        this.x = expr(strX);
        this.y = expr(strY);
    }

    public Point(float x, float y) {
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
