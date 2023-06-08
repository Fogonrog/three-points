package com.example.myapplication.logic.model;

import com.example.myapplication.LevelInfoJSON;

public class LevelFromUser {
    public Stage stage;
    public String campaignName;
    public int number;

    public LevelFromUser(Stage stage, String campaignName, int number) {
        this.stage = stage;
        this.campaignName = campaignName;
        this.number = number;
    }

    public static LevelFromUser from(LevelInfoJSON levelInfo, Stage stage) {
        return new LevelFromUser(stage, levelInfo.getCampaignName(), levelInfo.getLevel());
    }
}
