package com.example.myapplication.graphics;

public final class Circle implements Drawable {
    private final Point center;
    private final float radius;

    public Circle(Point center, float radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public void drawOn(Canvas canvas) {
        canvas.getCanvas()
                .drawCircle(center.x(), center.y(),
                            radius, canvas.getPaint());
    }
}
