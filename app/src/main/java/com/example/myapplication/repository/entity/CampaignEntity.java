package com.example.myapplication.repository.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "campaigns")
public final class CampaignEntity {
    @PrimaryKey(autoGenerate = true)
    private final long id;
    private final String name;

    public CampaignEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
