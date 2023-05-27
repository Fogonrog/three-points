package com.example.myapplication.graphics;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;


@JsonTypeName("Scaled")
public final class Scaled implements Drawable {
    private final Drawable child;
    private final int width;

    @JsonCreator
    public Scaled(@JsonProperty("width") int width, @JsonProperty("child") Drawable child) {
        this.width = width;
        this.child = child;
    }

    public static Scaled from(int width, Drawable child) {
        return new Scaled(width, child);
    }

    public void drawOn(Canva canvas) {
        canvas.paint.setStrokeWidth(width);
        canvas.draw(child);
    }

    public Drawable getChild() {
        return child;
    }

    public int getWidth() {
        return width;
    }
}
