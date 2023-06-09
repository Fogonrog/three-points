package com.example.myapplication.serialization;

import static com.example.myapplication.serialization.Template.replace;

import com.example.myapplication.logic.model.LevelFromUser;
import com.example.myapplication.logic.model.Stage;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public final class Parser {
    private final float height;
    private final float width;

    public Parser(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public Stage parseStage(String text) {
        ObjectMapper objectMapper = new ObjectMapper();
        StageJSON stageJSON;
        try {
            text = replace(text, width, height);
            stageJSON = objectMapper.readValue(text, StageJSON.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Stage.fromJSON(stageJSON);
    }

    public LevelInfoJSON parseInfo(String text) {
        ObjectMapper objectMapper = new ObjectMapper();
        LevelInfoJSON infoJSON;
        try {
            text = replace(text, width, height);
            infoJSON = objectMapper.readValue(text, LevelInfoJSON.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return infoJSON;
    }

    public LevelFromUser parseLevel(String text) {
        ObjectMapper objectMapper = new ObjectMapper();
        var info = parseInfo(text);
        return LevelFromUser.from(info, text);
    }
}
