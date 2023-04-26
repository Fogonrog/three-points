package com.example.myapplication.Expressions;
public final class Num implements Function {
    private float number;

    public Num(float number) {
        this.number = number;
    }

    @Override
    public String asString(){
        return "" + number;
    }

    @Override
    public float evaluate(float number) {
        return number;
    }
}