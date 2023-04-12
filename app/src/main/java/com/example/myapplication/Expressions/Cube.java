package com.example.myapplication.Expressions;

public class Cube extends Expr {
    public Expr function;
    public Cube(Expr func) {
        this.function = func;
    }
    @Override
    public float eval(float x) {
        return (float) Math.pow(function.eval(x),3);
    }
}