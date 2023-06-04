package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

public final class TutorActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor_activity);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        var btnBack = findViewById(R.id.back);
        btnBack.setOnClickListener(v -> {
                var intent =new Intent(TutorActivity.this, MenuActivity.class);
                startActivity(intent);
                overridePendingTransition( R.anim.right_in, R.anim.left_out);
        });
    }
    public void onBackPressed() {}
}

