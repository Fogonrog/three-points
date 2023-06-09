package com.example.myapplication.repository.entity;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = CampaignEntity.class, parentColumns = "id", childColumns = "campaignId"),
        tableName = "levels")
public final class LevelEntity {
    @PrimaryKey(autoGenerate = true)
    private final long id;
    private final long campaignId;
    private final String campaignName;
    private final int number;
    private final String stage;
    private final boolean done;

    public LevelEntity(long id, long campaignId, String campaignName, int number, String stage, boolean done) {
        this.id = id;
        this.campaignId = campaignId;
        this.campaignName = campaignName;
        this.number = number;
        this.stage = stage;
        this.done = done;
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

    public String getStage() {
        return stage;
    }

    public boolean isDone() {
        return done;
    }
}
