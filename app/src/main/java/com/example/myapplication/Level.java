package com.example.myapplication;

import com.example.myapplication.expressions.Function;
import com.example.myapplication.graphics.Drawable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties
public final class Level {
    private final int level;
    private final List<Function> functions;
    private final List<Drawable> requiredObstacles;
    private final List<Drawable> forbiddenObstacles;
    private final List<Drawable> environment;
    @JsonCreator
    public Level(@JsonProperty("level") int level,
                 @JsonProperty("functions") List<Function> functions,
                 @JsonProperty("requiredObstacles") List<Drawable> requiredObstacles,
                 @JsonProperty("forbiddenObstacles") List<Drawable> forbiddenObstacles,
                 @JsonProperty("environment") List<Drawable> environment) {
        this.level = level;
        this.functions = functions;
        this.requiredObstacles = requiredObstacles;
        this.forbiddenObstacles = forbiddenObstacles;
        this.environment = environment;
    }

    public int getLevel() {
        return level;
    }
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
