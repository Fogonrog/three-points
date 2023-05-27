package com.example.myapplication.graphics;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.Collection;

@JsonTypeName("Container")
public final class Container implements Drawable {
    private final Collection<Drawable> children;
    @JsonCreator
    private Container(@JsonProperty("children") Collection<Drawable> children) {
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

    public Collection<Drawable> getChildren() {
        return children;
    }
}
