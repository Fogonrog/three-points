package com.example.myapplication.Expressions;

public class Expr {
    public Expr function;

    public Expr(Expr func) {
        this.function = func;
    }

    public Expr() {
    }

    public float eval(float x) {
        return function.eval(x);
    }
}