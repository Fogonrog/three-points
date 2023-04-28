package com.example.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.myapplication.Expressions.Function;

public class Draw2D extends View {
    public static boolean functionOnTheLayout = false;
    public static Function function;
    private Paint paint = new Paint();
    private Bitmap bitmap;
    private Canvas canvas;

    public Draw2D(Context context) {
        super(context);
        Resources res = this.getResources();
        bitmap = BitmapFactory.decodeResource(res, R.drawable.first);
    }

    public Draw2D(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Resources res = this.getResources();
        bitmap = BitmapFactory.decodeResource(res, R.drawable.first);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;

        moveStartingPoint();
        initialСanvasPreparation();
        drawAxes(Color.BLACK);
        drawFunction(Color.RED);
    }

    private void drawFunction(int color){
        if (functionOnTheLayout) {
            paint.setColor(color);
            for (float i = 0F; i < 30; i += 0.001F) {
                canvas.drawCircle(i * 10, (float) (function.evaluate(i) * 10), 2, paint);
                canvas.drawCircle(-i * 10, (float) (function.evaluate(-i) * 10), 2, paint);
            }
        }
    }

    private void drawBackground(Bitmap bitmap, int width, int height) {
        Rect dstRect = new Rect(width / -2, height / -2,width / 2, height / 2);
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    private Bitmap rotatedBitmap(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(180);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
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
        canvas.drawLine(20,(getHeight()/2)-20,0,getHeight()/2,paint);
        canvas.drawLine(-20,(getHeight()/2)-20,0,getHeight()/2,paint);
        canvas.drawLine((getWidth()/2)-20,-20,(getWidth()/2),0,paint);
        canvas.drawLine((getWidth()/2)-20,20,(getWidth()/2),0,paint);
        canvas.drawRect(1,getHeight()/2,-1,-getHeight()/2,paint);
        canvas.drawRect(getWidth()/2,0.5F,-getWidth()/2,-0.5F,paint);
    }

    private void initialСanvasPreparation() {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        paint.setAntiAlias(true);
    }

    private void moveStartingPoint() {
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.scale(1f, -1f);
    }
}