package com.example.myapplication.logic.model;

import com.example.myapplication.repository.entity.CampaignEntity;
import com.example.myapplication.repository.entity.LevelEntity;

public class LevelInfo {
    public long id;
    public long campaignId;
    public int number;
    public boolean done;

    public LevelInfo(long id, long campaignId, int number, boolean done) {
        this.id = id;
        this.campaignId = campaignId;
        this.number = number;
        this.done = done;
    }
    public static LevelInfo fromEntity(LevelEntity entity){
        return new LevelInfo(entity.id, entity.campaignId, entity.number, entity.done);
    }
}
