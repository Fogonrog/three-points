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
import com.example.myapplication.graphics.Colored;
import com.example.myapplication.graphics.Container;
import com.example.myapplication.graphics.Drawable;
import com.example.myapplication.graphics.FunctionGraph;
import com.example.myapplication.graphics.Line;
import com.example.myapplication.graphics.Point;

import java.util.List;

public final class Draw2D extends View {
    private static final float INDENT =  20F;
    private static boolean functionOnTheCanvas = false;
    private static Function function = x();
    private final Paint paint = new Paint();
    private Canvas canvas;

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
        this.canvas = canvas;
        float width = (float) getWidth();
        float height = (float) getHeight();
        float halfWidth = width / 2;
        float halfHeight = height / 2;
        moveStartingPoint();
        initialCanvasPreparation();
        // TODO: IDEA swear at the fact that the code below is not optimized,
        //  need to fix it
        com.example.myapplication.graphics.Canvas mainCanvas = com.example
                .myapplication.graphics.Canvas.from(canvas, paint);
        Drawable axes = Container.from(List.of(
                Line.from(Point.from(0F, (halfHeight)),
                        Point.from(0F, (-halfHeight))),
                Line.from(Point.from(halfWidth, 0F),
                        Point.from(-halfWidth, 0F)),
                Line.from(Point.from(INDENT, (halfHeight - INDENT)),
                        Point.from(0F, halfHeight)),
                Line.from(Point.from(-INDENT, (halfHeight - INDENT)),
                        Point.from(0F, halfHeight)),
                Line.from(Point.from((halfWidth - INDENT), -INDENT),
                        Point.from(halfWidth, 0F)),
                Line.from(Point.from((halfWidth - INDENT), INDENT),
                        Point.from(halfWidth, 0F))
        ));
        Drawable func = FunctionGraph.from(function);
        Drawable redFunc = Colored.from(Color.RED, func);
        Drawable blackAxes = Colored.from(Color.BLACK, axes);
        mainCanvas.draw(blackAxes);

        if (functionOnTheCanvas) {
            mainCanvas.draw(redFunc);
        }
    }

    private void initialCanvasPreparation() {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        paint.setAntiAlias(true);
    }

    private void moveStartingPoint() {
        canvas.translate((float) getWidth() / 2, (float) getHeight() / 2);
        canvas.scale(1f, -1f);
    }
}
