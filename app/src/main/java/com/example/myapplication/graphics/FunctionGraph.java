package com.example.myapplication.graphics;

import com.example.myapplication.expressions.Function;

public final class FunctionGraph implements Drawable {
    private static final float WIDTH_FOR_FUNCTION = 8F;
    private static final float WIDTH_FOR_OTHER = 1F;
    private static final float MAX_VALUE_X = 500F;
    private static final float STEP_X = 0.01F;
    private final Function function;

    public FunctionGraph(Function function) {
        this.function = function;
    }

    public static FunctionGraph from(Function function) {
        return new FunctionGraph(function);
    }
    @Override
    public void drawOn(Canvas canvas) {
        canvas.getPaint().setStrokeWidth(WIDTH_FOR_FUNCTION);
        for (float i = 0F; i < MAX_VALUE_X; i += STEP_X) {
            canvas.getCanvas().drawLine(i, (float) (function.evaluate(i)),
                    i + STEP_X, (float) (function.evaluate(i + STEP_X)),
                    canvas.getPaint());
            canvas.getCanvas().drawLine(-i, (float) (function.evaluate(-i)),
                    -i - STEP_X, (float) (function.evaluate(-i - STEP_X)),
                    canvas.getPaint());
        }
        canvas.getPaint().setStrokeWidth(WIDTH_FOR_OTHER);
    }
}
