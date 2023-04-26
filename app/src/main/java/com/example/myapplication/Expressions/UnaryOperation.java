package com.example.myapplication.Expressions;

public abstract class UnaryOperation implements Function {

    @FunctionalInterface
    public interface Combine {
        float apply(float func);
    }

    protected final Function function;
    protected final Combine combine;
    protected final String symbol;

    public UnaryOperation(
            Function function,
            Combine combine,
            String symbol
    ) {
        this.function = function;
        this.combine = combine;
        this.symbol = symbol;
    }

    @Override
    public float evaluate(float x) {
        return combine.apply(
                function.evaluate(x)
        );
    }

    @Override
    public String asString() {
        return function.asString();
    }
}