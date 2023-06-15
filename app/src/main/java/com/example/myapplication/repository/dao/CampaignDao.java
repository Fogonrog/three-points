package com.example.myapplication.repository.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.repository.entity.CampaignEntity;

import java.util.List;
import java.util.Optional;

@Dao
public interface CampaignDao {
    @Insert
    long insertCampaign(CampaignEntity campaignEntity);

    @Query("SELECT * FROM campaigns")
    List<CampaignEntity> getAllCampaigns();

    @Query("SELECT * FROM campaigns WHERE rowid = :id")
    Optional<CampaignEntity> getCampaignById(long id);

    @Query("SELECT * FROM campaigns WHERE name = :name")
    Optional<CampaignEntity> getCampaignByName(String name);

    @Query("SELECT COUNT(*) FROM campaigns WHERE name = :name")
    int countCampaignByName(String name);
}
