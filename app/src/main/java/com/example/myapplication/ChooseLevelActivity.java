package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ChooseLevelActivity extends AppCompatActivity {
    private static SharedPreferences prefs;
    private static int highestLevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_level_activity);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        prefs = getSharedPreferences("checkout", MODE_PRIVATE);
        highestLevel = prefs.getInt("highestLevel",0);

        var btnLevel1 = findViewById(R.id.startLevel1);
        btnLevel1.setOnClickListener(v -> {
            var intent = new Intent(ChooseLevelActivity.this, LevelActivity.class);
            intent.putExtra("level", "level-1.json");
            startActivity(intent);
        });
        var btnLevel2 = findViewById(R.id.startLevel2);
        btnLevel2.setOnClickListener(v -> {
            if(1 <= highestLevel) {
                var intent = new Intent(ChooseLevelActivity.this, LevelActivity.class);
                intent.putExtra("level", "level-2.json");
                startActivity(intent);
            } else {
                Toast.makeText(this, "Пройдите предыдущие уровни", Toast.LENGTH_SHORT).show();
            }
        });
        var btnLevel3 = findViewById(R.id.startLevel3);
        btnLevel3.setOnClickListener(v -> {
            if(2 <= highestLevel) {
                var intent = new Intent(ChooseLevelActivity.this, LevelActivity.class);
                intent.putExtra("level", "level-3.json");
                startActivity(intent);
            } else {
                Toast.makeText(this, "Пройдите предыдущие уровни", Toast.LENGTH_SHORT).show();
            }
        });
        var btnBack = findViewById(R.id.back);
        btnBack.setOnClickListener(v -> {
            var intent =new Intent(ChooseLevelActivity.this, MenuActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.left_in, R.anim.right_out);
        });
    }
    public static void saveProgress(int level) {
        if(level > highestLevel) {
            highestLevel = level;
            prefs.edit().putInt("highestLevel", highestLevel).apply();
        }
    }
    public void onBackPressed() {}
}

