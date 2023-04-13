package com.example.myapplication.Expressions;

public class Sqrt extends Expr {
    public Expr function;
    public Sqrt(Expr func) {
        this.function = func;
    }
    @Override
    public float eval(float x) {
        return (float) Math.sqrt(function.eval(x));
    }
}