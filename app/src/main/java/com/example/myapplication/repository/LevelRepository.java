package com.example.myapplication.repository;

import android.app.Application;

import com.example.myapplication.repository.dao.CampaignDao;
import com.example.myapplication.repository.dao.LevelDao;
import com.example.myapplication.repository.database.AppDatabase;
import com.example.myapplication.repository.entity.CampaignEntity;
import com.example.myapplication.repository.entity.LevelEntity;

import java.util.List;

public class LevelRepository {
    private final LevelDao levelDao;
    private final CampaignDao campaignDao;

    public LevelRepository(Application application) {
        var db = AppDatabase.getDatabase(application);
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
        if (campaignDao.getCampaignById(levelEntity.campaignId) == null) {
            var id = levelEntity.campaignId;
            var name = levelEntity.campaignName;
            var campaignEntity = new CampaignEntity(id, name);
            campaignDao.insertCampaign(campaignEntity);
        }
        return levelDao.insertLevel(levelEntity);
    }
}
