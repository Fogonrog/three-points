package com.example.myapplication.Expressions;
public final class Num implements Function {
    private float number;

    public Num(float number) {
        this.number = number;
    }

    @Override
    public String asString(){
        if ((int)number == number) {
            return String.valueOf((int) number);
        }else {
            return String.valueOf(number);
        }
    }

    @Override
    public float evaluate(float argument) {
        return this.number;
    }
}