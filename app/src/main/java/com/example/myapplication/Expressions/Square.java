package com.example.myapplication.Expressions;

public class Square extends Expr {
    public Expr function;
    public Square(Expr func) {
        this.function = func;
    }
    @Override
    public float eval(float x) {
        return (float) Math.pow(function.eval(x),2);
    }
}