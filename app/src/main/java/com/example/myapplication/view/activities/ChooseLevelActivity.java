package com.example.myapplication.view.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class ChooseLevelActivity extends AppCompatActivity {
    public static final int requestKey = 1;
    private static SharedPreferences prefs;
    private static int highestLevel;

    public static void saveProgress(int level) {
        if (level > highestLevel) {
            highestLevel = level;
            prefs.edit().putInt("highestLevel", highestLevel).apply();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_level_activity);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        prefs = getSharedPreferences("checkout", MODE_PRIVATE);
        highestLevel = prefs.getInt("highestLevel", 0);

        var btnLevel1 = findViewById(R.id.startLevel1);
        btnLevel1.setOnClickListener(v -> {
            var intent = new Intent(ChooseLevelActivity.this, LevelActivity.class);
            intent.putExtra("level", "level-1.json");
            startActivity(intent);
        });

        var btnLevel2 = findViewById(R.id.startLevel2);
        var btnLevel3 = findViewById(R.id.startLevel3);
        var btnLevel4 = findViewById(R.id.startLevel4);
        var btnLevel5 = findViewById(R.id.startLevel5);
        var listBtn = List.of(btnLevel2, btnLevel3, btnLevel4, btnLevel5);

        int numLevel = 1;
        for (View btn : listBtn) {
            var finalNumLevel = new AtomicInteger(numLevel);
            btn.setOnClickListener(v -> {
                if (finalNumLevel.get() <= highestLevel) {
                    var intent = new Intent(ChooseLevelActivity.this, LevelActivity.class);
                    intent.putExtra("level", "level-" + finalNumLevel.addAndGet(1) + ".json");
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Пройдите предыдущие уровни", Toast.LENGTH_SHORT).show();
                }
            });
            numLevel++;
        }

        var btnBack = findViewById(R.id.back);
        btnBack.setOnClickListener(v -> {
            var intent = new Intent(ChooseLevelActivity.this, MenuActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.left_in, R.anim.right_out);
        });

        var testBtn = findViewById(R.id.testButton);
        testBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(Intent.createChooser(intent, "Выберите файлы"), requestKey);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == requestKey && data != null) {
            Uri uri = data.getData();
            System.out.println(uri);
        }
    }

    public void onBackPressed() {
    }
}

