package com.example.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public final class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        var btnPlay = findViewById(R.id.play);
        btnPlay.setOnClickListener(v -> {
            var intent = new Intent(MenuActivity.this, ChooseCampaignActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        });

        var btnTutor = findViewById(R.id.tutor);
        btnTutor.setOnClickListener(v -> {
            var intent = new Intent(MenuActivity.this, TutorActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.left_in, R.anim.right_out);
        });

        var btnAuthor = findViewById(R.id.author);
        btnAuthor.setOnClickListener(v -> {
            var intent = new Intent(MenuActivity.this, AuthorActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.up_in, R.anim.down_out);
        });
    }

    public void onBackPressed() {
    }
}
