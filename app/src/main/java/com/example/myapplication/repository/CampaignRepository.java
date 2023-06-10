package com.example.myapplication.repository;

import com.example.myapplication.logic.model.Campaign;
import com.example.myapplication.repository.dao.CampaignDao;
import com.example.myapplication.repository.database.AppDatabase;
import com.example.myapplication.repository.entity.CampaignEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class CampaignRepository {
    private final CampaignDao campaignDao;

    public CampaignRepository(AppDatabase db) {
        campaignDao = db.campaignDao();
    }

    public List<Campaign> getAllCampaigns() {
        var entities = campaignDao.getAllCampaigns();
        List<Campaign> campaigns = new ArrayList<>();
        for (CampaignEntity entity : entities) {
            campaigns.add(Campaign.fromEntity(entity).get());
        }
        return campaigns;
    }

    public Optional<Campaign> getCampaignById(long id) {
        return Campaign.fromEntity(campaignDao.getCampaignById(id));
    }

    public  Campaign getCampaignByName(String name) {
        return Campaign.fromEntity(campaignDao.getCampaignByName(name)).get();
    }

    public long insertCampaign(Campaign campaign) {
        return campaignDao.insertCampaign(new CampaignEntity(campaign.getId(), campaign.getName()));
    }

    public boolean isCampaignExists(String name) {
        return campaignDao.countCampaignByName(name) > 0;
    }
}
