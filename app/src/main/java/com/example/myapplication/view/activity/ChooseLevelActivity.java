package com.example.myapplication.view.activity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.logic.LevelService;
import com.example.myapplication.logic.Preferences;
import com.example.myapplication.logic.model.Level;
import com.example.myapplication.repository.database.AppDatabase;
import com.example.myapplication.serialization.Parser;
import com.example.myapplication.view.adapter.LevelAdapter;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class ChooseLevelActivity extends AppCompatActivity {
    private static Preferences prefs;
    private AppDatabase db;
    private LevelService levelService;

    public static void saveProgress(String campaignName, int level) {
        prefs.saveProgress(campaignName, level);
    }

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
        prefs = new Preferences(this, campaign);

        var levels = getLevels(campaign);
        levels.sort(Comparator.comparingInt(o -> o.getInfo().getNumber()));
        var adapter = new LevelAdapter(this, levels);
        var lv = (ListView) findViewById(R.id.level_list);
        lv.setOnItemClickListener((parent, view, position, id) -> {
            var number = ((Level) parent.getItemAtPosition(position)).getInfo().getNumber() - 1;
            if (prefs.isHeightLevel(campaign, number) | number == 0) {
                var levelId = ((Level) parent.getItemAtPosition(position)).getInfo().getId();
                var intent = new Intent(ChooseLevelActivity.this, LevelActivity.class);
                intent.putExtra("level", levelId);
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.completePreviousLevels, Toast.LENGTH_SHORT).show();
            }
        });
        lv.setAdapter(adapter);

        var btnBack = findViewById(R.id.back);
        btnBack.setOnClickListener(v -> {
            var intent = new Intent(ChooseLevelActivity.this, ChooseCampaignActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.left_in, R.anim.right_out);
        });
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

