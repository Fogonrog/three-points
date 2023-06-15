package com.example.myapplication.logic.graphics;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import org.locationtech.jts.geom.Geometry;


@JsonTypeName("Scaled")
public final class Scaled implements Drawable {
    private final Drawable child;
    private final int width;

    @JsonCreator
    public Scaled(@JsonProperty("width") int width,
                  @JsonProperty("child") Drawable child) {
        this.width = width;
        this.child = child;
    }

    public static Scaled from(int width, Drawable child) {
        return new Scaled(width, child);
    }

    @Override
    public Geometry jts() {
        return child.jts();
    }

    public void drawOn(Canva canvas) {
        canvas.paint.setStrokeWidth(width);
        canvas.draw(child);
    }

    @Override
    public boolean intersects(Drawable other) {
        return child.intersects(other);
    }

    public Drawable getChild() {
        return child;
    }

    public int getWidth() {
        return width;
    }
}
