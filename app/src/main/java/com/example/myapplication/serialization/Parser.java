package com.example.myapplication.serialization;

import static com.example.myapplication.serialization.Template.replace;

import com.example.myapplication.logic.model.LevelFromUser;
import com.example.myapplication.logic.model.Stage;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Parser {
    public static Stage parseStage(String text, float width, float height) { // text <- LevelEntity::stage
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

    public static LevelFromUser parseLevel(String text, float width, float height) {
        ObjectMapper objectMapper = new ObjectMapper();
        var stage = parseStage(text, width, height);
        LevelInfoJSON levelInfo;
        try {
            text = replace(text, width, height);
            levelInfo = objectMapper.readValue(text, LevelInfoJSON.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return LevelFromUser.from(levelInfo, stage);
    }
}
