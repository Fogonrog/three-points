package com.example.myapplication.repository.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.repository.dao.CampaignDao;
import com.example.myapplication.repository.dao.LevelDao;
import com.example.myapplication.repository.entity.CampaignEntity;
import com.example.myapplication.repository.entity.LevelEntity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Database(entities = {CampaignEntity.class, LevelEntity.class},
        version = 3,
        exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase instance;
    private static final Executor EXECUTOR = Executors.newSingleThreadExecutor();

    public static AppDatabase getDatabase(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "app_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }

    public static void execute(Runnable runnable) {
        EXECUTOR.execute(runnable);
    }

    public abstract CampaignDao campaignDao();

    public abstract LevelDao levelDao();
}
