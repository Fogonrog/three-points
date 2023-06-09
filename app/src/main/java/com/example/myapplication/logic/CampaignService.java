package com.example.myapplication.logic;

import com.example.myapplication.logic.model.Campaign;
import com.example.myapplication.repository.CampaignRepository;
import com.example.myapplication.repository.database.AppDatabase;

import java.util.List;

public final class CampaignService {
    private final CampaignRepository campaignRepository;

    public CampaignService(AppDatabase db) {
        campaignRepository = new CampaignRepository(db);
    }

    public boolean isCampaignExists(String campaignName) {
        return campaignRepository.getCampaignByName(campaignName) != null;
    }

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.getAllCampaigns();
    }

    public long createCampaign(Campaign draft) {
        return campaignRepository.insertCampaign(draft);
    }

    public CampaignRepository getCampaignRepository() {
        return campaignRepository;
    }
}
