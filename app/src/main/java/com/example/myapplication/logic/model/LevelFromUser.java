package com.example.myapplication.logic.model;

import com.example.myapplication.serialization.LevelInfoJSON;

public final class LevelFromUser {
    private Stage stage;
    private String strStage;
    private final String campaignName;
    private final int number;

    public LevelFromUser(String strStage, String campaignName, int number) {
        this.strStage = strStage;
        this.campaignName = campaignName;
        this.number = number;
    }
    public LevelFromUser(Stage stage, String campaignName, int number) {
        this.stage = stage;
        this.campaignName = campaignName;
        this.number = number;
    }

    public static LevelFromUser from(LevelInfoJSON levelInfo, String stage) {
        return new LevelFromUser(stage, levelInfo.getCampaignName(), levelInfo.getLevel());
    }

    public static LevelFromUser from(LevelInfoJSON levelInfo, Stage stage) {
        return new LevelFromUser(stage, levelInfo.getCampaignName(), levelInfo.getLevel());
    }

    public String getStage() {
        return strStage;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public int getNumber() {
        return number;
    }
}
