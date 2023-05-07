package com.example.myapplication;

import static com.example.myapplication.expressions.Functions.x;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.myapplication.expressions.Function;
import com.example.myapplication.graphics.Canva;
import com.example.myapplication.graphics.Colored;
import com.example.myapplication.graphics.Container;
import com.example.myapplication.graphics.FunctionGraph;
import com.example.myapplication.graphics.Line;
import com.example.myapplication.graphics.Point;

import java.util.List;

public final class Draw2D extends View {
    private static final float INDENT =  20F;
    private static boolean functionOnTheCanvas = false;
    private static Function function = x();
    private final Paint paint = new Paint();
    public Draw2D(Context context) {
        super(context);
    }

    public Draw2D(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public static void setFunction(Function function) {
        Draw2D.function = function;
    }

    public static void setFunctionOnTheCanvas(boolean functionOnTheCanvas) {
        Draw2D.functionOnTheCanvas = functionOnTheCanvas;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = getWidth();
        float height = getHeight();

        moveStartingPoint(canvas);
        initialCanvasPreparation(canvas);
        // TODO: IDEA swear at the fact that the code below is not optimized,
        //  need to fix it
        var mainCanvas = Canva.from(canvas, paint);
        var func = FunctionGraph.from(function);
        var redFunc = Colored.from(Color.RED, func);
        var axes = Container.from(List.of(
                Line.of(Point.of(0F, height / 2),
                        Point.of(0F, -height / 2)),
                Line.of(Point.of(width / 2, 0F),
                        Point.of(-width / 2, 0F)),
                Line.of(Point.of(INDENT, (height / 2 - INDENT)),
                        Point.of(0F, height / 2)),
                Line.of(Point.of(-INDENT, (height / 2 - INDENT)),
                        Point.of(0F, height / 2)),
                Line.of(Point.of((width / 2 - INDENT), -INDENT),
                        Point.of(width / 2, 0F)),
                Line.of(Point.of((width / 2 - INDENT), INDENT),
                        Point.of(width / 2, 0F))
        ));
        var blackAxes = Colored.from(Color.BLACK, axes);
        mainCanvas.draw(blackAxes);

        if (functionOnTheCanvas) {
            mainCanvas.draw(redFunc);
        }
    }

    private void initialCanvasPreparation(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        paint.setAntiAlias(true);
    }

    private void moveStartingPoint(Canvas canvas) {
        canvas.translate((float) getWidth() / 2, (float) getHeight() / 2);
        canvas.scale(1f, -1f);
    }
}
