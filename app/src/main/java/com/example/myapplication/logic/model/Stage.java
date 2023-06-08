package com.example.myapplication.logic.model;

import com.example.myapplication.StageJSON;
import com.example.myapplication.logic.expressions.Function;
import com.example.myapplication.logic.graphics.Drawable;

import java.util.List;
import java.util.Set;

public class Stage {
    public Set<Drawable> obstacles; // intersects(other)
    public Set<Drawable> checkpoints;
    public List<Drawable> background;
    public List<Function> functions;

    public Stage(Set<Drawable> obstacles, Set<Drawable> checkpoints, List<Drawable> background, List<Function> functions) {
        this.obstacles = obstacles;
        this.checkpoints = checkpoints;
        this.background = background;
        this.functions = functions;
    }

    public static Stage fromJSON(StageJSON stageJSON) {
        return new Stage(stageJSON.getForbiddenObstacles(),
                stageJSON.getRequiredObstacles(),
                stageJSON.getBackground(),
                stageJSON.getFunctions());
    }
}
