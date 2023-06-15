package com.example.myapplication.repository;

import com.example.myapplication.logic.model.Campaign;
import com.example.myapplication.repository.dao.CampaignDao;
import com.example.myapplication.repository.database.AppDatabase;
import com.example.myapplication.repository.entity.CampaignEntity;
import com.example.myapplication.repository.entity.LevelEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public final class CampaignRepository {
    private final CampaignDao campaignDao;

    public CampaignRepository(AppDatabase db) {
        campaignDao = db.campaignDao();
    }

    public List<Campaign> getAllCampaigns() {
        var entities = campaignDao.getAllCampaigns();
        List<Campaign> campaigns = new ArrayList<>();
        for (CampaignEntity entity : entities) {
            campaigns.add(Campaign.fromEntity(entity));
        }
        return campaigns;
    }

    public Campaign getCampaignById(long id) {
        return campaignDao.getCampaignById(id).map(Campaign::fromEntity).get();
    }

    public  Campaign getCampaignByName(String name) {
        return campaignDao.getCampaignByName(name).map(Campaign::fromEntity).get();
    }

    public long insertCampaign(Campaign campaign) {
        var random = new Random();
        var id = random.nextLong();
        var name = campaign.getName();
        var campaignEntity = new CampaignEntity(id, name);
        return campaignDao.insertCampaign(campaignEntity);
    }

    public boolean isCampaignExists(String name) {
        return campaignDao.countCampaignByName(name) > 0;
    }
}
