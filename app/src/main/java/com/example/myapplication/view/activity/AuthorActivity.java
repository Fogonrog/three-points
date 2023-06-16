package com.example.myapplication.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public final class AuthorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.author);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        var btnBack = findViewById(R.id.back);
        btnBack.setOnClickListener(v -> {
            var intent = new Intent(AuthorActivity.this, MenuActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.down_in, R.anim.up_out);
        });
    }

    public void onBackPressed() {
    }
}

