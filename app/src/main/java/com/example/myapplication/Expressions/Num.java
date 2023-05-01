package com.example.myapplication.Expressions;
public final class Num implements Function {
    private float number;

    public Num(float number) {
        this.number = number;
    }

    @Override
    public String asString(){
        if ((int)number == number) {
            return "" + (int) number;
        }else {
            return "" + number;
        }
    }

    @Override
    public float evaluate(float argument) {
        return this.number;
    }
}