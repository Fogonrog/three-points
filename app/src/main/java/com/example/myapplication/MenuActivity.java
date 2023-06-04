package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        var btnPlay = findViewById(R.id.play);
        btnPlay.setOnClickListener(v -> {
            var intent = new Intent(MenuActivity.this, ChooseLevelActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.right_in,R.anim.left_out);
        });

        var btnExit = findViewById(R.id.exit);
        var manager = getSupportFragmentManager();
        btnExit.setOnClickListener(v -> {
//            var exitFragment = new ExitFragment();
//            exitFragment.show(manager,"exitFragment");
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
            finish();
        });

        var btnTutor = findViewById(R.id.tutor);
        btnTutor.setOnClickListener(v -> {
            var intent = new Intent(MenuActivity.this, TutorActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.left_in, R.anim.right_out);
        });
    }
    public void onBackPressed() {}
}
