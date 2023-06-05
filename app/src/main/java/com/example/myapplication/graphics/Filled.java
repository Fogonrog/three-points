package com.example.myapplication.graphics;

import android.graphics.Paint;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import org.locationtech.jts.geom.Geometry;

@JsonTypeName("Filled")
public final class Filled implements Drawable {
    private final Drawable child;
    private final boolean filled;

    @JsonCreator
    public Filled(@JsonProperty("filled") boolean filled,
                   @JsonProperty("child") Drawable child) {
        this.filled = filled;
        this.child = child;
    }

    public static Filled from(boolean filled, Drawable child) {
        return new Filled(filled, child);
    }

    @Override
    public Geometry jts() {
        return child.jts();
    }
    @Override
    public boolean intersects(Drawable other) {
        return child.intersects(other);
    }

    public void drawOn(Canva canvas) {
        if (filled) {
            canvas.paint.setStyle(Paint.Style.FILL);
        } else {
            canvas.paint.setStyle(Paint.Style.STROKE);
        }
        canvas.draw(child);
    }

    public Drawable getChild() {
        return child;
    }

    public boolean getFilled() {
        return filled;
    }
}
