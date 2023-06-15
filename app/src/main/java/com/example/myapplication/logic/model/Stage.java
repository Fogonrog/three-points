package com.example.myapplication.logic.model;

import com.example.myapplication.repository.entity.LevelEntity;
import com.example.myapplication.serialization.Parser;
import com.example.myapplication.serialization.StageJSON;
import com.example.myapplication.logic.expression.Function;
import com.example.myapplication.logic.graphics.Drawable;

import java.util.List;
import java.util.Set;

public final class Stage {
    private final Set<Drawable> obstacles; // intersects(other)
    private final Set<Drawable> checkpoints;
    private final List<Drawable> background;
    private final List<Function> functions;

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

    public static Stage fromEntity(LevelEntity entity, Parser parser) {
        var string = entity.getStage();
        return parser.parseStage(string);
    }

    public Set<Drawable> getObstacles() {
        return obstacles;
    }

    public Set<Drawable> getCheckpoints() {
        return checkpoints;
    }

    public List<Drawable> getBackground() {
        return background;
    }

    public List<Function> getFunctions() {
        return functions;
    }
}
