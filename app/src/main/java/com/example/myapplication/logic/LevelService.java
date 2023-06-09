package com.example.myapplication.logic;

import static com.example.myapplication.serialization.Parser.parseStage;

import com.example.myapplication.logic.model.Level;
import com.example.myapplication.logic.model.LevelInfo;
import com.example.myapplication.repository.LevelRepository;
import com.example.myapplication.repository.database.AppDatabase;
import com.example.myapplication.repository.entity.LevelEntity;

import java.util.ArrayList;
import java.util.List;

public class LevelService {
    private final LevelRepository levelRepository;

    public LevelService(AppDatabase db) {
        levelRepository = new LevelRepository(db);
    }

    public List<Level> getAllLevelsByCampaignId(Long campaignId, float width, float height) {
        List<LevelEntity> entity = levelRepository.getAllLevelsByCampaignId(campaignId);
        List<Level> levels = new ArrayList<>();
        for (LevelEntity levelEntity : entity) {
            var info = LevelInfo.fromEntity(levelEntity);
            var stage = parseStage(levelEntity.getStage(), width, height);
            levels.add(new Level(info, stage));
        }
        return levels;
    }

    public LevelRepository getLevelRepository() {
        return levelRepository;
    }
}
