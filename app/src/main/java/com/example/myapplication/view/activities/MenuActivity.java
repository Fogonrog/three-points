package com.example.myapplication.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.logic.CampaignService;
import com.example.myapplication.logic.model.Campaign;
import com.example.myapplication.repository.database.AppDatabase;
import com.example.myapplication.repository.entity.CampaignEntity;
import com.example.myapplication.view.fragments.ExitFragment;

import java.util.List;
import java.util.UUID;

public final class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        var db = AppDatabase.getDatabase(this);
        var campaignService = new CampaignService(db);
        AppDatabase.execute(() -> {
            List<Campaign> list = campaignService.getAllCampaigns();
            System.out.println(list);
//            campaignService.createCampaign(new Campaign(UUID.randomUUID().toString()., "third_tempt"));
            var t3 = campaignService.isCampaignExists("third_tempt");
            var t2 = campaignService.isCampaignExists("second_tempt");
        });


        var btnPlay = findViewById(R.id.play);
        btnPlay.setOnClickListener(v -> {
            var intent = new Intent(MenuActivity.this, ChooseLevelActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        });

        var btnExit = findViewById(R.id.exit);
        var manager = getSupportFragmentManager();
        btnExit.setOnClickListener(v -> {
            var exitFragment = new ExitFragment();
            exitFragment.show(manager, "exitFragment");
        });

        var btnTutor = findViewById(R.id.tutor);
        btnTutor.setOnClickListener(v -> {
            var intent = new Intent(MenuActivity.this, TutorActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.left_in, R.anim.right_out);
        });
    }

    public void onBackPressed() {
    }
}
