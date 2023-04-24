package com.example.myapplication.Expressions;

public final class Num extends UnaryOperation {
    private float number;
    @Override
    public String asString(){
        return "" + number;
    }

    @Override
    public float evaluate(float x) {
        return number;
    }

    public Num(float number) {
        super(null, (a) -> a, "");
        this.number = number;
    }
}