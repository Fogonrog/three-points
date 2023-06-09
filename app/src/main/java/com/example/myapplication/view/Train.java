package com.example.myapplication.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.myapplication.view.fragment.LevelFragment;

import java.util.ArrayList;
import java.util.List;

public final class Train {
    private static final int MAX_VALUE_X = 500;
    private static final int MIN_VALUE_X = -100;
    private static final int LIFTING_SPEED = 400;
    private static final int DESCENDING_SPEED = 300;
    private final ImageView photo;
    private final Draw2D canvas;
    private final int level;
    private int width;
    private int height;

    public Train(Draw2D canvas, ImageView photo, int level) {
        this.canvas = canvas;
        this.photo = photo;
        this.level = level;
    }

    public void startAnimation() {
        var function = canvas.getFunction();
        var widthMlt = canvas.getWidthMlt();
        this.width = canvas.getWidth();
        this.height = canvas.getHeight();
        var currentAngle = 0.0;
        List<Animator> translateAnimators = new ArrayList<>();
        List<Animator> rotateAnimators = new ArrayList<>();
        photo.setVisibility(View.VISIBLE);
        var vto = photo.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int height = photo.getHeight();
                photo.setPivotY(height);
                photo.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });



        for (float i = MIN_VALUE_X; i < MAX_VALUE_X; i += 1) {
            if (convertY(function.evaluate(i) * widthMlt) <= height + 650
                    && convertY(function.evaluate(i) * widthMlt) >= -400
                    && convertX(i * widthMlt) >= -200
                    && convertX(i * widthMlt) <= width + 150) {
                var y1 = convertY(function.evaluate(i) * widthMlt);
                var y2 = convertY(function.evaluate(i + 1) * widthMlt);
                var x1 = convertX(i * widthMlt);
                var x2 = convertX((i + 1) * widthMlt);
                var angle = Math.atan2(y2 - y1, x2 - x1) * 180 / Math.PI;

                var rotAnimator = ObjectAnimator.ofFloat(photo,
                        "rotation", (float) currentAngle, (float) angle);
                var xAnimator = ObjectAnimator.ofFloat(photo, "translationX", x1, x2);
                var yAnimator = ObjectAnimator.ofFloat(photo, "translationY", y1, y2);
                xAnimator.setInterpolator(new LinearInterpolator());
                yAnimator.setInterpolator(new LinearInterpolator());

                var duration = (y1 < y2) ? (LIFTING_SPEED) : (DESCENDING_SPEED);
                xAnimator.setDuration(duration);
                yAnimator.setDuration(duration);
                rotAnimator.setDuration(duration);

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
        setAnimators.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                LevelFragment.showVictoryFragment(level);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        setAnimators.start();
    }

    private float convertX(float x) {
        return (float) width / 2 + x;
    }

    private float convertY(float y) {
        return (float) height / 2 - y;
    }

    public Draw2D getCanvas() {
        return canvas;
    }
}
