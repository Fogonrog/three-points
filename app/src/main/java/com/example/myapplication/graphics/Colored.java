package com.example.myapplication.graphics;

import com.example.myapplication.expressions.Function;

public final class Colored implements Drawable {
    private final Drawable child;
    private final int color;

    public Colored(int color, Drawable child) {
        this.color = color;
        this.child = child;
    }

    public void drawOn(Canvas canvas) {
        canvas.getPaint().setColor(color);
        canvas.draw(child);
    }

    public static Colored from(int color, Drawable child) {
        return new Colored(color, child);
    }
}
