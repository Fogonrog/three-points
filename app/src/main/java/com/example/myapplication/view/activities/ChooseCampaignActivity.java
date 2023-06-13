package com.example.myapplication.view.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.media.tv.TvView;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.logic.CampaignService;
import com.example.myapplication.logic.LevelService;
import com.example.myapplication.logic.model.Campaign;
import com.example.myapplication.logic.model.LevelFromUser;
import com.example.myapplication.repository.database.AppDatabase;
import com.example.myapplication.repository.entity.CampaignEntity;
import com.example.myapplication.serialization.LevelInfoJSON;
import com.example.myapplication.serialization.Parser;
import com.example.myapplication.view.adapter.CampaignAdapter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public final class ChooseCampaignActivity extends AppCompatActivity {
    public static final int REQUEST_KEY = 1;
    private static SharedPreferences prefs;
    private static int highestLevel;
    private AppDatabase db;
    private CampaignService campaignService;
    private LevelService levelService;
    private Parser parser;


    public static void saveProgress(int level) {
        if (level > highestLevel) {
            highestLevel = level;
            prefs.edit().putInt("highestLevel", highestLevel).apply();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_campaign_activity);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        prefs = getSharedPreferences("checkout", MODE_PRIVATE);
        highestLevel = prefs.getInt("highestLevel", 0);
        var display = this.getWindowManager().getDefaultDisplay();
        var point = new Point();
        display.getSize(point);
        var width = point.x;
        var height = point.y;
        parser = new Parser(width, height);

        db = AppDatabase.getDatabase(this);
        campaignService = new CampaignService(db);
        levelService = new LevelService(db, parser);
        AppDatabase.execute(() -> {
            if (!campaignService.isCampaignExists("Туториал")) {
                var campaign = Campaign.fromEntity(new CampaignEntity(-1, "Туториал"));
                campaignService.createCampaign(campaign);
                for (int i = 1; i <= 5; i++) {
                    var string = readFileInAssets("level-" + i + ".json");
                    var info = new LevelInfoJSON(i, "Туториал");
                    var draft = LevelFromUser.from(info, string);
                    levelService.createLevel(draft);
                }
            }
        });

        var campaigns = getCampaigns();
        var adapter = new CampaignAdapter(this, campaigns);
        var lv = (ListView) findViewById(R.id.campaign_list);
        lv.setOnItemClickListener((parent, view, position, id) -> {
            String name = ((Campaign) parent.getItemAtPosition(position)).getName();
            var intent = new Intent(ChooseCampaignActivity.this, ChooseLevelActivity.class);
            intent.putExtra("campaign", name);
            startActivity(intent);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        });
        lv.setAdapter(adapter);

        var btnBack = findViewById(R.id.back);
        btnBack.setOnClickListener(v -> {
            var intent = new Intent(ChooseCampaignActivity.this, MenuActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.left_in, R.anim.right_out);
        });

        var testBtn = findViewById(R.id.testButton);
        testBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("application/json");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(Intent.createChooser(intent, "Выберите файл"), REQUEST_KEY);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_KEY && data != null) {
            Uri uri = data.getData();
            InputStream inputStream;
            try {
                inputStream = getContentResolver().openInputStream(uri);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                String jsonString = new BufferedReader(new InputStreamReader(inputStream))
                        .lines().collect(Collectors.joining("\n"));
                var level = parser.parseLevel(jsonString);
                var campaignName = level.getCampaignName();
                AppDatabase.execute(() -> {
                    if (!campaignService.isCampaignExists(campaignName) && campaignName != null) {
                        var campaign = Campaign.fromEntity(new CampaignEntity(-777, campaignName));
                        campaignService.createCampaign(campaign);
                        if (!levelService.isLevelExists(level.getNumber(), level.getCampaignName()) && level.getNumber() != 0) {
                            levelService.createLevel(level);
                        }
                    }
                });
            }catch (Exception e) {
                Toast.makeText(this, "Упс... , кажется в вашем файле есть ошибка", Toast.LENGTH_SHORT).show();
            }
            recreate();
        }
    }

    private List<Campaign> getCampaigns() {
        CompletableFuture<List<Campaign>> future = new CompletableFuture<>();
        AppDatabase.execute(() -> {
            var campaigns = campaignService.getAllCampaigns();
            future.complete(campaigns);
        });
        return future.join();
    }

    public String readFileInAssets(String name) {
        byte[] buffer;
        InputStream is;
        try {
            is = this.getAssets().open(name);
            int size = is.available();
            buffer = new byte[size];
            is.read(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new String(buffer);
    }

    public void onBackPressed() {
    }
}

