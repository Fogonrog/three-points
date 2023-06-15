package com.example.myapplication.serialization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class LevelInfoJSON {
    private final String campaignName;
    private final int level;

    @JsonCreator
    public LevelInfoJSON(@JsonProperty("level") int level,
                         @JsonProperty("campaign") String campaignName) {
        this.level = level;
        this.campaignName = campaignName;
    }
    public int getLevel() {
        return level;
    }
    public String getCampaignName() {
        return campaignName;
    }
}
