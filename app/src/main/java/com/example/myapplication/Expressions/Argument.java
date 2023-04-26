package com.example.myapplication.Expressions;

public final class Argument implements Function {
    @Override
    public String asString(){
        return "x";
    }

    @Override
    public float evaluate(float argument) {
        return argument;
    }
}