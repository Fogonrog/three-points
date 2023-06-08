package com.example.myapplication.logic;

import android.app.Application;

import androidx.lifecycle.Transformations;

import com.example.myapplication.logic.model.Campaign;
import com.example.myapplication.repository.CampaignRepository;
import com.example.myapplication.repository.entity.CampaignEntity;

import java.util.ArrayList;
import java.util.List;

public class CampaignService {
    CampaignRepository campaignRepository;

    public CampaignService(Application application) {
        campaignRepository = new CampaignRepository(application);
    }

    public boolean isCampaignExists(String campaignName) {
        return campaignRepository.getCampaignByName(campaignName) != null;
    }

    public List<Campaign> getAllCampaigns() {
        List<CampaignEntity> entities = campaignRepository.getAllCampaigns();
        List<Campaign> campaigns = new ArrayList<>();
        for (CampaignEntity entity : entities) {
            campaigns.add(Campaign.fromEntity(entity));
        }
        return campaigns;
    }

    public long createCampaign(Campaign draft) {
        CampaignEntity entity = new CampaignEntity(draft.id, draft.name);
        return campaignRepository.insertCampaign(entity);
    }
}
