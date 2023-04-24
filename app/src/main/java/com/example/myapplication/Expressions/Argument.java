package com.example.myapplication.Expressions;

public final class Argument extends UnaryOperation {
    @Override
    public String asString(){
        return "x";
    }
    @Override
    public float evaluate(float argument) {
        return argument;
    }
    public Argument() {
        super(null, (a) -> a, "x");
    }
}