package com.example.myapplication.logic;

import com.example.myapplication.logic.model.Level;
import com.example.myapplication.logic.model.LevelFromUser;
import com.example.myapplication.logic.model.LevelInfo;
import com.example.myapplication.logic.model.Stage;
import com.example.myapplication.repository.LevelRepository;
import com.example.myapplication.repository.database.AppDatabase;
import com.example.myapplication.repository.entity.LevelEntity;
import com.example.myapplication.serialization.Parser;

import java.util.ArrayList;
import java.util.List;

public class LevelService {
    private final LevelRepository levelRepository;
    private final Parser parser;

    public LevelService(AppDatabase db, Parser parser) {
        this.levelRepository = new LevelRepository(db);
        this.parser = parser;
    }

    public Level getLevelById(long campaignId) {
        var entity = levelRepository.getLevelById(campaignId);
        var info = LevelInfo.fromEntity(entity);
        var stage = Stage.fromEntity(entity, parser);
        return new Level(info, stage);
    }

    public List<Level> getAllLevelsByCampaignId(long campaignId) {
        List<LevelEntity> entity = levelRepository.getAllLevelsByCampaignId(campaignId);
        List<Level> levels = new ArrayList<>();
        for (LevelEntity levelEntity : entity) {
            var info = LevelInfo.fromEntity(levelEntity);
            var stage = parser.parseStage(levelEntity.getStage());
            levels.add(new Level(info, stage));
        }
        return levels;
    }

    public List<Level> getAllLevelsByCampaignName(String name) {
        List<LevelEntity> entity = levelRepository.getAllLevelsByCampaignName(name);
        List<Level> levels = new ArrayList<>();
        for (LevelEntity levelEntity : entity) {
            var info = LevelInfo.fromEntity(levelEntity);
            var stage = parser.parseStage(levelEntity.getStage());
            levels.add(new Level(info, stage));
        }
        return levels;
    }

    public long createLevel(LevelFromUser draft) {
        return levelRepository.insertLevel(draft);
    }

    public boolean isLevelExists(int number, String campaignName) {
        return levelRepository.isLevelExists(number, campaignName);
    }
}
