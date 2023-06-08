package com.example.myapplication.logic.model;

import com.example.myapplication.repository.entity.CampaignEntity;

public class Campaign {
    public long id;
    public String name;

    public Campaign(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Campaign fromEntity(CampaignEntity entity){
        return new Campaign(entity.id, entity.name);
    }
}
