package com.example.myapplication.Expressions;

public class Num extends Expr {
    public float number;
    public Num(float num) {
        this.number = num;
    }
    @Override
    public float eval(float x) {
        return this.number;
    }
}