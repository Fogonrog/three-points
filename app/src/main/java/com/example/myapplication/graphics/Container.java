package com.example.myapplication.graphics;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import org.locationtech.jts.geom.Geometry;

import java.util.Collection;
import java.util.List;

@JsonTypeName("Container")
public final class Container implements Drawable {
    private final List<Drawable> children;

    @JsonCreator
    private Container(@JsonProperty("children") List<Drawable> children) {
        this.children = children;
    }

    public static Container from(List<Drawable> children) {
        return new Container(children);
    }

    @Override
    public Geometry jts() {
        var result = children.get(0);
        for (Drawable child : children) {
            result.jts().union(child.jts());
        }
        return result.jts();
    }

    @Override
    public boolean intersects(Drawable other) {
        var result = true;
        for (Drawable child : children) {
            result = result && child.intersects(other);
        }
        return result;
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
