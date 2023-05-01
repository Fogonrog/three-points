package com.example.myapplication.Graphics;

import android.graphics.Paint;

public class Canvas {
    android.graphics.Canvas origin;
    android.graphics.Paint paint;

    public Canvas(android.graphics.Canvas canvas, Paint paint) {
        this.origin = canvas;
        this.paint = paint;
    }

    public void draw(Drawable drawable) {
        drawable.drawOn(this);
    }
}
