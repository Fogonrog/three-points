package com.example.myapplication;

import static com.example.myapplication.Expressions.Functions.cos;
import static com.example.myapplication.Expressions.Functions.ctg;
import static com.example.myapplication.Expressions.Functions.n;
import static com.example.myapplication.Expressions.Functions.pow;
import static com.example.myapplication.Expressions.Functions.sin;
import static com.example.myapplication.Expressions.Functions.tg;
import static com.example.myapplication.Expressions.Functions.x;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.myapplication.Expressions.Argument;
import com.example.myapplication.Expressions.Function;
import com.example.myapplication.Expressions.Num;
import com.example.myapplication.Expressions.Power;

public class MiniDraw2D extends View {
    public static Function function = x;
    private Canvas canvas;
    private Paint paint;

    public MiniDraw2D(Context context) {
        super(context);
    }

    public MiniDraw2D(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(600, 600);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        this.paint = new Paint();

        initialСanvasPreparation();
        moveStartingPoin();
        drawAxes(Color.BLACK);
        drawFunction(Color.RED);
    }

    private void initialСanvasPreparation() {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        paint.setAntiAlias(true);
    }

    private void drawAxes(int color) {
        paint.setColor(color);

        for (int i = 30; i < getWidth()/2-58; i+=30) {
            canvas.drawLine(i,5,i,-5,paint);
        }
        for (int i = 30; i < getWidth()/2; i+=30) {
            canvas.drawLine(-i,5,-i,-5,paint);
        }
        for (int i = 30; i < getHeight()/2-58; i+=30) {
            canvas.drawLine(-5, i, 5, i, paint);
        }
        for (int i = 30; i < getHeight()/2; i+=30) {
            canvas.drawLine(-5, -i, 5, -i, paint);
        }
        canvas.drawLine(10,(getHeight()/2)-10,0,getHeight()/2,paint);
        canvas.drawLine(-10,(getHeight()/2)-10,0,getHeight()/2,paint);
        canvas.drawLine((getWidth()/2)-10,-10,(getWidth()/2),0,paint);
        canvas.drawLine((getWidth()/2)-10,10,(getWidth()/2),0,paint);
        canvas.drawRect(1,getHeight()/2,-1,-getHeight()/2,paint);
        canvas.drawRect(getWidth()/2,1,-getWidth()/2,-1,paint);
    }

    private void moveStartingPoin() {
        canvas.translate(getWidth()/2,getHeight()/2);
        canvas.scale(1f, -1f);
    }

    private void drawFunction(int color){
        paint.setColor(color);
        for (float i = 0F; i < 20; i += 0.001F) {
            canvas.drawCircle(i*10, (float) (function.evaluate(i)*10), 2, paint);
            canvas.drawCircle(-i*10, (float) (function.evaluate(-i)*10), 2, paint);
        }
    }
}
