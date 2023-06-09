package com.example.myapplication.repository;

import android.app.Application;

import com.example.myapplication.repository.dao.CampaignDao;
import com.example.myapplication.repository.dao.LevelDao;
import com.example.myapplication.repository.database.AppDatabase;
import com.example.myapplication.repository.entity.CampaignEntity;
import com.example.myapplication.repository.entity.LevelEntity;

import java.util.List;

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

    public long insertLevel(LevelEntity levelEntity) {
        if (campaignDao.getCampaignById(levelEntity.getCampaignId()) == null) {
            var id = levelEntity.getCampaignId();
            var name = levelEntity.getCampaignName();
            var campaignEntity = new CampaignEntity(id, name);
            campaignDao.insertCampaign(campaignEntity);
        }
        return levelDao.insertLevel(levelEntity);
    }
}
