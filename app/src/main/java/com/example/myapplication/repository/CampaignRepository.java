package com.example.myapplication.repository;

import android.app.Application;

import com.example.myapplication.repository.dao.CampaignDao;
import com.example.myapplication.repository.database.AppDatabase;
import com.example.myapplication.repository.entity.CampaignEntity;

import java.util.List;

public class CampaignRepository {
    private final CampaignDao campaignDao;

    public CampaignRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        campaignDao = db.campaignDao();
    }

    public List<CampaignEntity> getAllCampaigns() {
        return campaignDao.getAllCampaigns();
    }

    public CampaignEntity getCampaignById(long id) {
        return campaignDao.getCampaignById(id);
    }

    public CampaignEntity getCampaignByName(String id) {
        return campaignDao.getCampaignByName(id);
    }

    public long insertCampaign(CampaignEntity campaignEntity) {
        return campaignDao.insertCampaign(campaignEntity);
    }
}
