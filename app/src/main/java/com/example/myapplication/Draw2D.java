package com.example.myapplication;

import static com.example.myapplication.expressions.Functions.x;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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

import java.util.List;

public final class Draw2D extends View {
    private static final float INDENT = 20F;
    private static final float NUM_THREE = 3F;
    private static final float NUM_FOUR = 4F;
    private static final int LARGE_WIDTH = 8;
    private static boolean functionOnTheCanvas = false;
    private static boolean userOnTheCanvas = true;
    private static boolean correctFunction = false;
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

    public static void setUserOnTheCanvas(boolean userOnTheCanvas) {
        Draw2D.userOnTheCanvas = userOnTheCanvas;
    }

    public static boolean getCorrectFunction() {
        return correctFunction;
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
        var leftCoast = BrokenLine.of(List.of(
                Point.of(-width / 2, -INDENT),
                Point.of(-width / NUM_FOUR - NUM_FOUR, -INDENT),
                Point.of(-width / NUM_THREE, -height / NUM_THREE),
                Point.of(-width / NUM_FOUR - NUM_FOUR, -INDENT),
                Point.of(-width / NUM_THREE, -height / NUM_THREE),
                Point.of(-width / NUM_FOUR - 2 * INDENT, -height / 2),
                Point.of(-width / 2, -height / 2)));
        var rightCoast = BrokenLine.of(List.of(
                Point.of(width / 2, -INDENT),
                Point.of(width / NUM_FOUR + NUM_FOUR, -INDENT),
                Point.of(width / NUM_THREE, -height / NUM_THREE),
                Point.of(width / NUM_FOUR + NUM_FOUR, -INDENT),
                Point.of(width / NUM_THREE, -height / NUM_THREE),
                Point.of(width / NUM_FOUR + 2 * INDENT, -height / 2),
                Point.of(width / 2, -height / 2)));

        var blackLeftCoast = Colored.from(Color.BLACK, LARGE_WIDTH, leftCoast);
        var blackRightCoast = Colored.from(Color.BLACK, LARGE_WIDTH, rightCoast);
        var redFunc = Colored.from(Color.RED, LARGE_WIDTH, func);
        var blackAxes = Colored.from(Color.BLACK, 1, axes);

//        var circle = new Path();
//        circle.addCircle(-width / 2, -INDENT- 10,100, Path.Direction.CW);
//        paint.setColor(Color.GREEN);
//        canvas.drawPath(circle, paint);
//
//        var mPath = new Path();
//        mPath.moveTo(-50, -50);
//        mPath.lineTo(-150, -50);
//        mPath.lineTo(-150, -100);            отладочный код
//        mPath.lineTo(-250, -100);
//        mPath.lineTo(-250, -150);
//        mPath.lineTo(-350, -150);
//        mPath.lineTo(-350, -200);
//        mPath.lineTo(-450, -200);
//        canvas.drawPath(mPath, paint);

        mainCanvas.draw(blackAxes);
        if (userOnTheCanvas) {
            mainCanvas.draw(blackLeftCoast);
            mainCanvas.draw(blackRightCoast);
        }
        if (functionOnTheCanvas) {
            mainCanvas.draw(redFunc);
        }

        // собственно вся проверка ниже
        Region region1 = new Region();
        region1.setPath(leftCoast.getPath(), new Region(-10000,-10000,10000,10000));
        Region region2 = new Region();
        region2.setPath(func.getPath(),  new Region(-10000,-10000,10000,10000));
        System.out.println(region1.op(region2, Region.Op.INTERSECT));
    }

    public static boolean intersect(Path path1, Path path2, int width, int height){
        Region region1 = new Region();
        region1.setPath(path1, new Region(-width / 2, -height / 2, width / 2, height / 2));
        Region region2 = new Region();
        region2.setPath(path2, new Region(-width / 2, -height / 2, width / 2, height / 2));
        return region1.op(region2, Region.Op.INTERSECT);
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
