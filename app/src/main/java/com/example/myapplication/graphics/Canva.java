package com.example.myapplication.graphics;

import android.graphics.Canvas;
import android.graphics.Paint;

public final class Canva {
    //CHECKSTYLE:OFF
    final android.graphics.Canvas origin;
    final android.graphics.Paint paint;
    //CHECKSTYLE:ON

    public Canva(android.graphics.Canvas canvas, Paint paint) {
        this.origin = canvas;
        this.paint = paint;
    }

    public void draw(Drawable drawable) {
        drawable.drawOn(this);
    }

    public static Canva from(Canvas canvas, Paint paint) {
        return new Canva(canvas, paint);
    }
}
