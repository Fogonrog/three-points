package com.example.myapplication.Expressions;

public class Num implements Function {
    public float number;
    public Num(float num) {
        this.number = num;
    }
    public float evaluate(float x) {
        return this.number;
    }
}