package com.example.myapplication.logic.model;

import com.example.myapplication.serialization.LevelInfoJSON;

public final class LevelFromUser {
    private Stage stage;
    private String campaignName;
    private int number;

    public LevelFromUser(Stage stage, String campaignName, int number) {
        this.stage = stage;
        this.campaignName = campaignName;
        this.number = number;
    }

    public static LevelFromUser from(LevelInfoJSON levelInfo, Stage stage) {
        return new LevelFromUser(stage, levelInfo.getCampaignName(), levelInfo.getLevel());
    }

    public Stage getStage() {
        return stage;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public int getNumber() {
        return number;
    }
}
