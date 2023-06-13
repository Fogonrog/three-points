package com.example.myapplication.repository.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = CampaignEntity.class, parentColumns = "id", childColumns = "campaignId"),
        tableName = "levels", indices = {@Index("campaignId")})
public final class LevelEntity {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private long campaignId;
    private final String campaignName;
    private final int number;
    private final String stage;
    private boolean done;

    public LevelEntity(long id, long campaignId, String campaignName, int number, String stage, boolean done) {
        this.id = id;
        this.campaignId = campaignId;
        this.campaignName = campaignName;
        this.number = number;
        this.stage = stage;
        this.done = done;
    }

    public void setId(long id) {
        this.id = id;
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
