package com.example.myapplication.graphics;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import org.locationtech.jts.geom.Geometry;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Colored.class, name = "Colored"),
        @JsonSubTypes.Type(value = Scaled.class, name = "Scaled"),
        @JsonSubTypes.Type(value = Circle.class, name = "Circle"),
        @JsonSubTypes.Type(value = Point.class, name = "Point"),
        @JsonSubTypes.Type(value = BrokenLine.class, name = "BrokenLine"),
        @JsonSubTypes.Type(value = Container.class, name = "Container"),
        @JsonSubTypes.Type(value = Line.class, name = "Line"),
        @JsonSubTypes.Type(value = Polygon.class, name = "Polygon"),
        @JsonSubTypes.Type(value = Filled.class, name = "Filled")
})
public interface Drawable {
    Geometry jts();
    void drawOn(Canva canvas);
    boolean intersects(Drawable other);
}
