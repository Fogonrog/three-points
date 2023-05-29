package com.example.myapplication;

import com.example.myapplication.expressions.Function;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties
public final class Funci {
    private List<Function> functions;

    public Funci() {
    }

    public List<Function> getFunctions() {
        return functions;
    }
}
