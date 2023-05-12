package com.example.myapplication.graphics;

public final class Colored implements Drawable {
    private final Drawable child;
    private final int color;
    private final int width;

    public Colored(int color, int width, Drawable child) {
        this.width = width;
        this.color = color;
        this.child = child;
    }

    public void drawOn(Canva canvas) {
        canvas.paint.setColor(color);
        canvas.paint.setStrokeWidth(width);
        canvas.draw(child);
    }

    public static Colored from(int color, int width, Drawable child) {
        return new Colored(color, width, child);
    }
}
