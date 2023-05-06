package com.example.myapplication.expressions;

public abstract class UnaryOperation implements Function {

    @FunctionalInterface
    public interface Combine {
        float apply(float func);
    }

    private final Function function;
    private final Combine combine;
    private final String symbol;

    public UnaryOperation(
            Function function,
            Combine combine,
            String symbol
    ) {
        this.function = function;
        this.combine = combine;
        this.symbol = symbol;
    }

    public final String getSymbol() {
        return symbol;
    }

    public final Function getFunction() {
        return function;
    }

    @Override
    public final float evaluate(float x) {
        return combine.apply(
                function.evaluate(x)
        );
    }

    @Override
    public abstract String asString();
}
