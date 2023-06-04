package com.example.myapplication;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

public class Train {
    private final ImageView photo;
    private final Draw2D canvas;
    private int width;
    private int height;

    public Train(Draw2D canvas, ImageView photo) {
        this.canvas = canvas;
        this.photo = photo;
    }
    public void startAnimation() {
        var function = canvas.getFunction();
        var widthMlt = canvas.getWidthMlt();
        this.width = canvas.getWidth();
        this.height = canvas.getHeight();
        var currentAngle = 0.0;
        photo.setVisibility(View.VISIBLE);
        List<Animator> translateAnimators = new ArrayList<>();
        List<Animator> rotateAnimators = new ArrayList<>();

        for (float i = -500F; i < 500F; i += 1) {
            if (convertY(function.evaluate(i)* widthMlt)<=height+550 && convertY(function.evaluate(i)* widthMlt)>=-400 && convertX(i * widthMlt)>=-200 &&  convertX(i * widthMlt)<=width+300) {
                var y1 = convertY(function.evaluate(i) * widthMlt);
                var y2 =  convertY(function.evaluate(i + 1) * widthMlt);
                var x1 =  convertX(i * widthMlt);
                var x2 = convertX((i + 1) * widthMlt);
                var angle = Math.atan2(y2 - y1, x2 - x1) * 180 / Math.PI;

                if(y1>y2) {
                    x1-=100;
                    x2-=100;
                    y1-=50;
                    y2-=50;
                }
                var rotAnimator = ObjectAnimator.ofFloat(photo, "rotation", (float) currentAngle, (float) angle);
                var xAnimator = ObjectAnimator.ofFloat(photo, "translationX", x1, x2);
                var yAnimator = ObjectAnimator.ofFloat(photo, "translationY", y1, y2);
                xAnimator.setInterpolator(new LinearInterpolator());
                yAnimator.setInterpolator(new LinearInterpolator());

                if (y1 < y2) {
                    xAnimator.setDuration(400);
                    yAnimator.setDuration(400);
                    rotAnimator.setDuration(400);
                } else {
                    xAnimator.setDuration(300);
                    yAnimator.setDuration(300);
                    rotAnimator.setDuration(300);
                }

                var animatorSet = new AnimatorSet();
                animatorSet.playTogether(xAnimator, yAnimator);
                translateAnimators.add(animatorSet);
                rotateAnimators.add(rotAnimator);

                currentAngle = angle;
            }
        }
        var setTranslateAnimators = new AnimatorSet();
        setTranslateAnimators.playSequentially(translateAnimators);

        var setRotateAnimators = new AnimatorSet();
        setRotateAnimators.playSequentially(rotateAnimators);

        var setAnimators = new AnimatorSet();
        setAnimators.playTogether(setTranslateAnimators, setRotateAnimators);
        setAnimators.start();
    }

    private float convertX(float x) {
        return (float) width/2 + x;
    }
    private float convertY(float y) {
        return (float) height/2 - y;
    }

    public Draw2D getCanvas() {
        return canvas;
    }
}
