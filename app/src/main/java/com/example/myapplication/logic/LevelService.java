package com.example.myapplication.logic;

import com.example.myapplication.logic.model.Level;
import com.example.myapplication.logic.model.LevelInfo;
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

    public List<Level> getAllLevelsByCampaignId(Long campaignId) {
        List<LevelEntity> entity = levelRepository.getAllLevelsByCampaignId(campaignId);
        List<Level> levels = new ArrayList<>();
        for (LevelEntity levelEntity : entity) {
            var info = LevelInfo.fromEntity(levelEntity);
            var stage = parser.parseStage(levelEntity.getStage());
            levels.add(new Level(info, stage));
        }
        return levels;
    }
}
