package com.example.myapplication.repository.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.repository.entity.LevelEntity;

import java.util.List;

@Dao
public interface LevelDao {
    @Insert
    long insertLevel(LevelEntity levelEntity);

    @Query("SELECT * FROM levels WHERE campaignId = :campaignId")
    List<LevelEntity> getAllLevelsByCampaignId(long campaignId);
    @Query("SELECT * FROM levels WHERE id = :id")
    LevelEntity getLevelById(long id);

    @Query("SELECT * FROM levels WHERE campaignName = :campaignName")
    List<LevelEntity> getAllLevelsByCampaignName(String campaignName);
}
