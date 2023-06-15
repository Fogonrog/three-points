package com.example.myapplication.logic.graphics;

import android.graphics.Color;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import org.locationtech.jts.geom.Geometry;

@JsonTypeName("Colored")
public final class Colored implements Drawable {
    private final Drawable child;
    private final int color;

    @JsonCreator
    public Colored(@JsonProperty("color") String color,
                   @JsonProperty("child") Drawable child) {
        this.color = Color.parseColor(color);
        this.child = child;
    }

    public static Colored from(String color, Drawable child) {
        return new Colored(color, child);
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
        canvas.paint.setColor(color);
        canvas.draw(child);
    }

    public Drawable getChild() {
        return child;
    }

    public int getColor() {
        return color;
    }
}
