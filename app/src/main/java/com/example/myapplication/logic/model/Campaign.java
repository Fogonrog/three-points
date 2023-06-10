package com.example.myapplication.logic.model;

import com.example.myapplication.repository.entity.CampaignEntity;

import java.util.Optional;

public final class Campaign {
    private final long id;
    private final String name;

    public Campaign(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Optional<Campaign> fromEntity(CampaignEntity entity) {
        return Optional.of(new Campaign(entity.getId(), entity.getName()));
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
