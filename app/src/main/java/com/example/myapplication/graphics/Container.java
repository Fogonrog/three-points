package com.example.myapplication.graphics;

import java.util.Collection;

public final class Container implements Drawable {
    private final Collection<Drawable> children;

    private Container(Collection<Drawable> children) {
        this.children = children;
    }

    public static Container from(Collection<Drawable> children) {
        return new Container(children);
    }
    public void drawOn(Canva canvas) {
        for (Drawable child : children) {
            child.drawOn(canvas);
        }
    }
}
