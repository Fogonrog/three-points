package com.example.myapplication.repository.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.repository.dao.CampaignDao;
import com.example.myapplication.repository.dao.LevelDao;
import com.example.myapplication.repository.entity.CampaignEntity;
import com.example.myapplication.repository.entity.LevelEntity;

@Database(entities = {CampaignEntity.class, LevelEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CampaignDao campaignDao();
    public abstract LevelDao levelDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "app_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}