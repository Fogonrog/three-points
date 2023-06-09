package com.example.myapplication.repository.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.repository.entity.CampaignEntity;

import java.util.List;

@Dao
public interface CampaignDao {
    @Insert
    long insertCampaign(CampaignEntity campaignEntity);

    @Query("SELECT * FROM campaigns")
    List<CampaignEntity> getAllCampaigns();

    @Query("SELECT * FROM campaigns WHERE id = :id")
    CampaignEntity getCampaignById(long id);

    @Query("SELECT * FROM campaigns WHERE name = :name")
    CampaignEntity getCampaignByName(String name);
}
