package com.example.myapplication.logic.model;

import com.example.myapplication.repository.entity.LevelEntity;

public final class LevelInfo {
    private final long id;
    private final long campaignId;
    private final int number;
    private final boolean done;

    public LevelInfo(long id, long campaignId, int number, boolean done) {
        this.id = id;
        this.campaignId = campaignId;
        this.number = number;
        this.done = done;
    }

    public static LevelInfo fromEntity(LevelEntity entity) {
        return new LevelInfo(entity.getId(), entity.getCampaignId(), entity.getNumber(), entity.isDone());
    }

    public long getId() {
        return id;
    }

    public long getCampaignId() {
        return campaignId;
    }

    public int getNumber() {
        return number;
    }

    public boolean isDone() {
        return done;
    }
}
