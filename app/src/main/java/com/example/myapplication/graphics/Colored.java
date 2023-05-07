package com.example.myapplication.graphics;

public final class Colored implements Drawable {
    private final Drawable child;
    private final int color;

    public Colored(int color, Drawable child) {
        this.color = color;
        this.child = child;
    }

    public void drawOn(Canva canvas) {
        canvas.paint.setColor(color);
        canvas.draw(child);
    }

    public static Colored from(int color, Drawable child) {
        return new Colored(color, child);
    }
}
