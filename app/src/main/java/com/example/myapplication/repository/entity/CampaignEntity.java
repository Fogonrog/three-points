package com.example.myapplication.repository.entity;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
@Entity(tableName = "campaigns", indices = {@Index(value = {"name"}, unique = true)})
public final class CampaignEntity {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;

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
