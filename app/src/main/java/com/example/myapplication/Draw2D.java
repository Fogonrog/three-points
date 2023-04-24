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

public class Draw2D extends View {
    private final Paint paint = new Paint();
    private final Bitmap bitmap;
    private Canvas canvas;

    public Draw2D(Context context) {
        super(context);
        // Выводим значок из ресурсов
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


        int width = getWidth();
        int height = getHeight();
        moveStartingPoin(width,height);
        initialСanvasPreparation();

        //drawBackground(rotatedBitmap(bitmap),width,height);

        drawAxes(Color.BLACK,width,height);

        drawParabola(Color.RED);

    }

    private void drawBackground(Bitmap bitmap, int width, int height) {
        Rect dstRect = new Rect(-width/2, -height/2,width/2, height/2);
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    private Bitmap rotatedBitmap(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(180);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private void drawAxes(int color,int width,int height) {
        paint.setColor(color);
        canvas.drawRect(1,height/2,-1,-height/2,paint);
        canvas.drawRect(width/2,1,-width/2,-1,paint);
    }

    private void initialСanvasPreparation() {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        paint.setAntiAlias(true);
    }

    private void moveStartingPoin(int width,int height) {
        canvas.translate(width/2,height/2);
        canvas.scale(1f, -1f);
    }

    private void drawParabola(int color) {
        paint.setColor(color);
        for (float i = 0F; i < 20; i += 0.01F) {
            canvas.drawCircle(i*10, (float) (i*i), 3, paint);
            canvas.drawCircle(-i*10, (float) (i*i), 3, paint);
        }
    }
}