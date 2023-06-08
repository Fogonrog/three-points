package com.example.myapplication.logic.serialization;

import com.example.myapplication.LevelInfoJSON;
import com.example.myapplication.StageJSON;
import com.example.myapplication.logic.model.LevelFromUser;
import com.example.myapplication.logic.model.Stage;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Parser {
    public static Stage parseStage(String text, float width, float height){ // text <- LevelEntity::stage
        ObjectMapper objectMapper = new ObjectMapper();
        StageJSON stageJSON;
        try {
            text = text
                    .replace("{{WIDTH_PLACEHOLDER}}", String.valueOf(width))
                    .replace("{{HEIGHT_PLACEHOLDER}}", String.valueOf(height))
                    .replace("{{WIDTH_HALF}}", String.valueOf(width / 2))
                    .replace("{{HEIGHT_HALF}}", String.valueOf(height / 2))
                    .replace("{{INDENT}}", String.valueOf(20));
            stageJSON = objectMapper.readValue(text, StageJSON.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Stage.fromJSON(stageJSON);
    }
    public static LevelFromUser parseLevel(String text, float width, float height){
        ObjectMapper objectMapper = new ObjectMapper();
        var stage = parseStage(text, width, height);
        LevelInfoJSON levelInfo;
        try {
            text = text
                    .replace("{{WIDTH_PLACEHOLDER}}", String.valueOf(width))
                    .replace("{{HEIGHT_PLACEHOLDER}}", String.valueOf(height))
                    .replace("{{WIDTH_HALF}}", String.valueOf(width / 2))
                    .replace("{{HEIGHT_HALF}}", String.valueOf(height / 2))
                    .replace("{{INDENT}}", String.valueOf(20));
            levelInfo = objectMapper.readValue(text, LevelInfoJSON.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return LevelFromUser.from(levelInfo,stage);
    }
}
