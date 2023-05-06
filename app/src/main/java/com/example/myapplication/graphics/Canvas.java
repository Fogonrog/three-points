package com.example.myapplication.graphics;

import android.graphics.Paint;

public final class Canvas {
    private final android.graphics.Canvas origin;
    private final android.graphics.Paint paint;

    public Canvas(android.graphics.Canvas canvas, Paint paint) {
        this.origin = canvas;
        this.paint = paint;
    }

    public android.graphics.Canvas getCanvas() {
        return origin;
    }
    public android.graphics.Paint getPaint() {
        return paint;
    }
    public static Canvas from(android.graphics.Canvas canvas, android.graphics.Paint paint) {
        return new Canvas(canvas, paint);
    }

    public void draw(Drawable drawable) {
        drawable.drawOn(this);
    }
    public static Container fromAndroidComponents(Container container) {
        return container;
    }
}
