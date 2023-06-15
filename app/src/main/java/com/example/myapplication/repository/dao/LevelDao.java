package com.example.myapplication.repository.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myapplication.repository.entity.LevelEntity;

import java.util.List;

@Dao
public interface LevelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertLevel(LevelEntity levelEntity);

    @Query("SELECT * FROM levels WHERE campaignId = :campaignId")
    List<LevelEntity> getAllLevelsByCampaignId(long campaignId);
    @Query("SELECT * FROM levels WHERE rowid = :id")
    LevelEntity getLevelById(long id);

    @Query("SELECT * FROM levels WHERE campaignName = :campaignName")
    List<LevelEntity> getAllLevelsByCampaignName(String campaignName);

    @Query("SELECT COUNT(*) FROM levels WHERE campaignName = :campaignName AND number = :number")
    int countLevelByNumber(int number, String campaignName);
}
