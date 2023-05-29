package com.example.myapplication.graphics;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import org.locationtech.jts.geom.Geometry;

@JsonTypeName("Colored")
public final class Colored implements Drawable {
    private final Drawable child;
    private final int color;

    @JsonCreator
    public Colored(@JsonProperty("color") int color,
                   @JsonProperty("child") Drawable child) {
        this.color = color;
        this.child = child;
    }

    public static Colored from(int color, Drawable child) {
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
