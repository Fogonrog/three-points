package com.example.myapplication.repository.entity;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = CampaignEntity.class, parentColumns = "id", childColumns = "campaignId"),
        tableName = "levels")
public class LevelEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public long campaignId;
    public String campaignName;
    public int number;
    public String stage;
    public boolean done;

    public LevelEntity(long id, long campaignId, String campaignName, int number, String stage, boolean done) {
        this.id = id;
        this.campaignId = campaignId;
        this.campaignName = campaignName;
        this.number = number;
        this.stage = stage;
        this.done = done;
    }
}
