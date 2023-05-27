package com.example.myapplication.graphics;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Colored.class, name = "Colored"),
        @JsonSubTypes.Type(value = Scaled.class, name = "Scaled"),
        @JsonSubTypes.Type(value = Circle.class, name = "Circle"),
        @JsonSubTypes.Type(value = Point.class, name = "Point"),
        @JsonSubTypes.Type(value = BrokenLine.class, name = "BrokenLine"),
        @JsonSubTypes.Type(value = Container.class, name = "Container"),
        @JsonSubTypes.Type(value = Line.class, name = "Line"),
        @JsonSubTypes.Type(value = Polygon.class, name = "Polygon")
})
public interface Drawable {
    void drawOn(Canva canvas);
}
