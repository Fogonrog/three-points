package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.fragment.app.FragmentActivity;

public final class LevelActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
    public void onBackPressed() {}
}
