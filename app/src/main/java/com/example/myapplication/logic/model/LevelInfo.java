package com.example.myapplication.logic.model;

import com.example.myapplication.repository.entity.LevelEntity;
import com.example.myapplication.serialization.LevelInfoJSON;

public final class LevelInfo {
    private final long id;
    private final long campaignId;
    private final int number;
    private final boolean done;
    private final String campaignName;

    public LevelInfo(long id, long campaignId, String campaignName, int number, boolean done) {
        this.id = id;
        this.campaignId = campaignId;
        this.campaignName = campaignName;
        this.number = number;
        this.done = done;
    }

    public static LevelInfo fromEntity(LevelEntity entity) {
        return new LevelInfo(entity.getId(),
                entity.getCampaignId(),
                entity.getCampaignName(),
                entity.getNumber(),
                entity.isDone());
    }

    public static LevelInfo fromJSON(LevelInfoJSON infoJSON) {
        return new LevelInfo(-1, -1, infoJSON.getCampaignName(), infoJSON.getLevel(), false);
    }

    public long getId() {
        return id;
    }

    public long getCampaignId() {
        return campaignId;
    }
    public String getCampaignName() {
        return campaignName;
    }

    public int getNumber() {
        return number;
    }

    public boolean isDone() {
        return done;
    }
}
