package com.example.myapplication.expressions;

import androidx.annotation.NonNull;

public abstract class UnaryOperation implements Function {

    private final Combine combine;
    private final String symbol;
    private Function function;
    public UnaryOperation(Function function, Combine combine, String symbol) {
        this.function = function;
        this.combine = combine;
        this.symbol = symbol;
    }

    protected final String getSymbol() {
        return symbol;
    }

    protected final Function getFunction() {
        return function;
    }

    protected final void setFunction(Function function) {
        this.function = function;
    }

    @NonNull
    @Override
    public final Function clone() throws CloneNotSupportedException {
        return (Function) super.clone();
    }

    @Override
    public final float evaluate(float x) {
        return combine.apply(function.evaluate(x));
    }

    @Override
    public abstract String asString();

    @FunctionalInterface
    public interface Combine {
        float apply(float func);
    }
}
