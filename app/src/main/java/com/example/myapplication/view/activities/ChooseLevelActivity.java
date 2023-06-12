package com.example.myapplication.view.activities;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.logic.LevelService;
import com.example.myapplication.logic.model.Campaign;
import com.example.myapplication.logic.model.Level;
import com.example.myapplication.repository.database.AppDatabase;
import com.example.myapplication.serialization.Parser;
import com.example.myapplication.view.adapter.LevelAdapter;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class ChooseLevelActivity extends AppCompatActivity {
    private AppDatabase db;
    private LevelService levelService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_level_activity);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        var display = this.getWindowManager().getDefaultDisplay();
        var point = new Point();
        display.getSize(point);
        var width = point.x;
        var height = point.y;
        var campaign = this.getIntent().getStringExtra("campaign");

        var parser = new Parser(width, height);
        db = AppDatabase.getDatabase(this);
        levelService = new LevelService(db, parser);

        var levels = getLevels(campaign);
        System.out.println(levels);

//      Cортировка
        var adapter = new LevelAdapter(this, levels);
        var lv = (ListView) findViewById(R.id.level_list);
        lv.setOnItemClickListener((parent, view, position, id) -> {
            var level = ((Level) parent.getItemAtPosition(position)).getInfo().getId();
            var intent = new Intent(ChooseLevelActivity.this, LevelActivity.class);
            intent.putExtra("level", level);
            startActivity(intent);
        });
        lv.setAdapter(adapter);
    }
    private List<Level> getLevels(String campaignName) {
        CompletableFuture<List<Level>> future = new CompletableFuture<>();
        AppDatabase.execute(() -> {
            var levels = levelService.getAllLevelsByCampaignName(campaignName);
            future.complete(levels);
        });
        return future.join();
    }
    public void onBackPressed() {
    }
}

