package com.example.myapplication;

import com.example.myapplication.logic.expressions.Function;
import com.example.myapplication.logic.graphics.Drawable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class StageJSON {
    private final int level;
    private final List<Function> functions;
    private final Set<Drawable> requiredObstacles;
    private final Set<Drawable> forbiddenObstacles;
    private final List<Drawable> background;
    @JsonCreator
    public StageJSON(@JsonProperty("level") int level,
                     @JsonProperty("functions") List<Function> functions,
                     @JsonProperty("requiredObstacles") Set<Drawable> requiredObstacles,
                     @JsonProperty("forbiddenObstacles") Set<Drawable> forbiddenObstacles,
                     @JsonProperty("environment") List<Drawable> background) {
        this.level = level;
        this.functions = functions;
        this.requiredObstacles = requiredObstacles;
        this.forbiddenObstacles = forbiddenObstacles;
        this.background = background;
    }

    public int getLevel() {
        return level;
    }
    public List<Function> getFunctions() {
        return functions;
    }

    public Set<Drawable> getRequiredObstacles() {
        return requiredObstacles;
    }

    public Set<Drawable> getForbiddenObstacles() {
        return forbiddenObstacles;
    }

    public List<Drawable> getBackground() {
        return background;
    }
}
