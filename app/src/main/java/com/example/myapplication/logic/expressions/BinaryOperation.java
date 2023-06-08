package com.example.myapplication.logic.expressions;

import androidx.annotation.NonNull;

public abstract class BinaryOperation implements Function {

    private final Function right;
    private final String symbol;
    private final Combine combine;
    private Function left;

    public BinaryOperation(Function left,
                           Function right,
                           Combine combine,
                           String symbol) {
        this.left = left;
        this.right = right;
        this.combine = combine;
        this.symbol = symbol;
    }

    protected final Function left() {
        return left;
    }

    protected final Function right() {
        return right;
    }

    protected final String symbol() {
        return symbol;
    }

    public final void setFunction(Function function) {
        this.left = function;
    }

    @NonNull
    @Override
    public final Function clone() throws CloneNotSupportedException {
        return (Function) super.clone();
    }

    @Override
    public final float evaluate(float x) {
        return combine.apply(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public abstract String asString();

    @FunctionalInterface
    public interface Combine {
        float apply(float left, float right);
    }
}
