package com.example.myapplication.Graphics;

public class Colored implements Drawable {
    private final Drawable child;
    private final int color;

    public Colored(int color, Drawable child) {
        this.color = color;
        this.child = child;
    }

    public void drawOn(Canvas canvas) {
        canvas.paint.setColor(color);
        canvas.draw(child);
    }
}
