package com.example.myapplication;

import com.example.myapplication.expressions.Function;
import com.example.myapplication.graphics.Drawable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties
public final class Level {
    private List<Function> functions;
    private List<Drawable> requiredObstacles;
    private List<Drawable> forbiddenObstacles;
    private List<Drawable> environment;

    public Level() {}

    public List<Function> getFunctions() {
        return functions;
    }

    public List<Drawable> getRequiredObstacles() {
        return requiredObstacles;
    }

    public List<Drawable> getForbiddenObstacles() {
        return forbiddenObstacles;
    }

    public List<Drawable> getEnvironment() {
        return environment;
    }
}
