package com.example.myapplication.graphics;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

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
