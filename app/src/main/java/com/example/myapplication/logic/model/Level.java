package com.example.myapplication.logic.model;

public final class Level {
    private final LevelInfo info;
    private final Stage stage;

    public Level(LevelInfo info, Stage stage) {
        this.info = info;
        this.stage = stage;
    }

    public LevelInfo getInfo() {
        return info;
    }

    public Stage getStage() {
        return stage;
    }
}
