package com.example.myapplication.repository.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "campaigns")
public class CampaignEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;

    public CampaignEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
