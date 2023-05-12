package com.example.myapplication.graphics;

public final class Point {
    private final float x;
    private final float y;

    private Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float x() {
        return x;
    }

    public float y() {
        return y;
    }
    public static Point of(float x, float y) {
        return new Point(x, y);
    }
}