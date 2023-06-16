package com.example.myapplication.logic;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public final class Preferences {
    private static SharedPreferences prefs;

    public Preferences(Context context, String campaignName) {
        prefs = context.getSharedPreferences("checkout", MODE_PRIVATE);
        prefs.getInt(campaignName, 0);
    }

    public void saveProgress(String campaignName, int level) {
        if (level > prefs.getInt(campaignName, 0)) {
            prefs.edit().putInt(campaignName, level).apply();
        }
    }

    public boolean isHeightLevel(String campaignName, int level) {
        return prefs.getInt(campaignName, 0) >= level;
    }
}
