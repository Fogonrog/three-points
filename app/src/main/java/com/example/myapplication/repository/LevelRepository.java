package com.example.myapplication.repository;

import android.app.Application;

import com.example.myapplication.logic.model.LevelFromUser;
import com.example.myapplication.repository.dao.CampaignDao;
import com.example.myapplication.repository.dao.LevelDao;
import com.example.myapplication.repository.database.AppDatabase;
import com.example.myapplication.repository.entity.CampaignEntity;
import com.example.myapplication.repository.entity.LevelEntity;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public final class LevelRepository {
    private final LevelDao levelDao;
    private final CampaignDao campaignDao;

    public LevelRepository(AppDatabase db) {
        levelDao = db.levelDao();
        campaignDao = db.campaignDao();
    }

    public List<LevelEntity> getAllLevelsByCampaignId(long campaignId) {
        return levelDao.getAllLevelsByCampaignId(campaignId);
    }

    public List<LevelEntity> getAllLevelsByCampaignName(String name) {
        return levelDao.getAllLevelsByCampaignName(name);
    }

    public long insertLevel(LevelFromUser level) {
        var random = new Random();
        if (campaignDao.getCampaignByName(level.getCampaignName()) == null) {
            var name = level.getCampaignName();
            var campaignEntity = new CampaignEntity(random.nextLong(), name);
            campaignDao.insertCampaign(campaignEntity);
        }
        var id = random.nextLong();
        var campaignId = campaignDao.getCampaignByName(level.getCampaignName()).getId();
        var campaignName = level.getCampaignName();
        var number = level.getNumber();
        var stage = level.getStage().toString();
        var levelEntity = new LevelEntity(id, campaignId, campaignName, number, stage, false);

        return levelDao.insertLevel(levelEntity);
    }
}