package com.example.myapplication.Graphics;

import java.util.Collection;

public class Container implements Drawable {
    private final Collection<Drawable> children;

    public Container(Collection<Drawable> children) {
        this.children = children;
    }

    public void drawOn(Canvas canvas) {
        for (Drawable child : children) {
            child.drawOn(canvas);
        }
    }
}
