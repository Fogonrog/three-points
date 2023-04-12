package com.example.myapplication.Expressions;

public class Abs extends Expr {
    public Expr function;
    public Abs(Expr func) {
        this.function = func;
    }
    @Override
    public float eval(float x) {
        return Math.abs(function.eval(x));
    }
}