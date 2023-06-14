package com.example.myapplication.graphics;

import android.graphics.Canvas;
import android.graphics.Paint;

public final class Canva {
    //CHECKSTYLE:OFF
    final Canvas origin;
    final Paint paint;
    //CHECKSTYLE:ON

    private Canva(Canvas canvas, Paint paint) {
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
