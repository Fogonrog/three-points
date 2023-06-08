package com.example.myapplication.repository.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.myapplication.repository.entity.CampaignEntity;

import java.util.List;

@Dao
public interface CampaignDao {
    @Insert
    public long insertCampaign(CampaignEntity campaignEntity);

    @Query("SELECT * FROM campaigns")
    public List<CampaignEntity> getAllCampaigns();

    @Query("SELECT * FROM campaigns WHERE id = :id")
    public CampaignEntity getCampaignById(long id);

    @Query("SELECT * FROM campaigns WHERE name = :name")
    public CampaignEntity getCampaignByName(String name);
}