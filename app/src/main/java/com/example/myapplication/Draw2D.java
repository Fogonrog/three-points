package com.example.myapplication;

import static com.example.myapplication.expressions.Functions.n;
import static com.example.myapplication.expressions.Functions.tg;
import static com.example.myapplication.expressions.Functions.x;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.myapplication.expressions.Function;
import com.example.myapplication.graphics.BrokenLine;
import com.example.myapplication.graphics.Canva;
import com.example.myapplication.graphics.Colored;
import com.example.myapplication.graphics.Container;
import com.example.myapplication.graphics.FunctionGraph;
import com.example.myapplication.graphics.Line;
import com.example.myapplication.graphics.Point;
import com.example.myapplication.graphics.Polygon;

import java.util.List;

public final class Draw2D extends View {

    private static final float a = 7.0f;
    private static final float b = 10.0f;

    private float widthMlt;
    private float heightMlt;
    private static final float INDENT = 20F;
    private static final float NUM_THREE = 3F;
    private static final float NUM_FOUR = 4F;
    private static final int LARGE_WIDTH = 8;

    private final boolean isBigCanvas;
    private Function function = x();

    private final Paint paint = new Paint();

    private Container axes;
    private Polygon leftCoast;
    private Line leftLine;
    private Line rightLine;
    private Polygon rightCoast;
    private FunctionGraph func;

    public void setFunction(Function function) {
        this.function = function;
        invalidate();
    }

    public Function getFunction() {
        return this.function;
    }

    public Draw2D(Context context) {
        this(context, null);
    }

    public Draw2D(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.isBigCanvas = getId() == R.id.canvas;


    }

    public boolean isRightFunction() {
        return rightLine.getJts().intersects(func.getJts()) && leftLine.getJts().intersects(func.getJts());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (axes == null) {
            float width = getWidth();
            float height = getHeight();
            axes = Container.from(List.of(
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
            leftLine = Line.of(Point.of(-width / 2, -INDENT),
                    Point.of(-width / NUM_FOUR - NUM_FOUR, -INDENT));
            rightLine = Line.of(Point.of(width / 2, -INDENT),
                    Point.of(width / NUM_FOUR - NUM_FOUR, -INDENT));
            leftCoast = Polygon.from(List.of(
                    Point.of(-width / 2, -INDENT),
                    Point.of(-width / NUM_FOUR - NUM_FOUR, -INDENT),
                    Point.of(-width / NUM_THREE, -height / NUM_THREE),
                    Point.of(-width / NUM_FOUR - NUM_FOUR, -INDENT),
                    Point.of(-width / NUM_THREE, -height / NUM_THREE),
                    Point.of(-width / NUM_FOUR - 2 * INDENT, -height / 2),
                    Point.of(-width / 2, -height / 2),
                    Point.of(-width / 2, -INDENT)));
            rightCoast = Polygon.from(List.of(
                    Point.of(width / 2, -INDENT),
                    Point.of(width / NUM_FOUR + NUM_FOUR, -INDENT),
                    Point.of(width / NUM_THREE, -height / NUM_THREE),
                    Point.of(width / NUM_FOUR + NUM_FOUR, -INDENT),
                    Point.of(width / NUM_THREE, -height / NUM_THREE),
                    Point.of(width / NUM_FOUR + 2 * INDENT, -height / 2),
                    Point.of(width / 2, -height / 2),
                    Point.of(width / 2, -INDENT)));

            // посчитаем масштаб
            float l = -width / NUM_FOUR - NUM_FOUR;
            float h = height / 2;

            // пусть мы считаем, что в точке l должнa быть координаты x = -a
            // тогда масштаб
            widthMlt = -l / a;
            // а в половине высоты должно быть условных единиц b
            heightMlt = widthMlt;

        }

        moveStartingPoint(canvas);
        initialCanvasPreparation(canvas);
        var mainCanvas = Canva.from(canvas, paint);
        var func = FunctionGraph.from(function, widthMlt, heightMlt);
        this.func = func;

        var blackLeftCoast = Colored.from(Color.BLACK, LARGE_WIDTH, leftCoast);
        var blackRightCoast = Colored.from(Color.BLACK, LARGE_WIDTH, rightCoast);
        var redFunc = Colored.from(Color.RED, LARGE_WIDTH, func);
        var blackAxes = Colored.from(Color.BLACK, 1, axes);

        mainCanvas.draw(blackAxes);
        if (isBigCanvas) {
            mainCanvas.draw(blackLeftCoast);
            mainCanvas.draw(blackRightCoast);
        }
        mainCanvas.draw(redFunc);
    }

    private void initialCanvasPreparation(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        paint.setAntiAlias(true);
    }

    private void moveStartingPoint(Canvas canvas) {
        canvas.translate((float) getWidth() / 2, (float) getHeight() / 2);
        canvas.scale(1f, -1f);
    }
}
