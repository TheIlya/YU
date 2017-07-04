package com.firebaseapp.glaring_inferno_6492.ilya.youruniverse;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import java.util.LinkedList;

/**
 * Created by ilya on 03.07.17.
 */
public class ShineInAnimation extends Animation {
    private int w, h, faseCounter;
    private LinkedList<LightFall> lightFalls;

    class LightFall {
        Point firstUp, firstDown, secondUp, secondDown, fromUp, fromDown;

        LightFall (Point fromUp, Point fromDown) {
            this.fromUp = new Point(fromUp);
            this.fromDown = new Point(fromDown);
            firstUp = new Point(fromUp);
            secondUp = new Point(fromUp);
            firstDown = new Point(fromDown);
            secondDown = new Point(fromDown);
        }

        public void animate (Canvas canvas) {
            draw(canvas);
            move();
        }

        private void move() {
            switch (faseCounter) {
                case 0:
                    firstUp.x = x - (x - fromUp.x) / limit * (limit - counter);
                    firstUp.y = y - (y - fromUp.y) / limit * (limit - counter);
                    firstDown.x = x - (x - fromDown.x) / limit * (limit - counter);
                    firstDown.y = y - (y - fromDown.y) / limit * (limit - counter);
                    break;
                case 1:
                    if (firstUp.x == 0 || firstUp.x == w) {
                        secondUp.y -= h / limit / 10;
                        secondDown.y += h / limit / 10;
                        fromUp.y -= h / limit / 10;
                        fromDown.y += h / limit / 10;
                    }
                    if (firstUp.y == 0 || firstUp.y == h) {
                        secondUp.x -= w / limit / 10;
                        secondDown.x += w / limit / 10;
                        fromUp.x -= w / limit / 10;
                        fromDown.x += w / limit / 10;
                    }
                    break;
                case 2:
                    secondUp.x = x - (x - fromUp.x) / limit * (limit - counter);
                    secondUp.y = y - (y - fromUp.y) / limit * (limit - counter);
                    secondDown.x = x - (x - fromDown.x) / limit * (limit - counter);
                    secondDown.y = y - (y - fromDown.y) / limit * (limit - counter);
                    break;
            }
        }

        private void draw(Canvas canvas) {
            Path path = new Path();
            path.moveTo(firstUp.x, firstUp.y);
            path.lineTo(firstDown.x, firstDown.y);
            path.lineTo(secondDown.x, secondDown.y);
            path.lineTo(secondUp.x, secondUp.y);
            path.close();
            Paint p = new Paint();
            p.setColor(Color.WHITE);
            p.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawPath(path, p);
        }
    }

    public ShineInAnimation(int x, int y, Paint paint, int w, int h) {
        super(x, y, paint);
        this.w = w;
        this.h = h;
        faseCounter = 0;
        lightFalls = new LinkedList<>();
        counterMotion = 1;
        faseCounter = 0;
        limit = 20;
        for (int i = 0; i < 3; i++) {
            switch ((int) Math.round(Math.random() * 3)) {
                case 0:
                    lightFalls.add(new LightFall(new Point(0, (int) Math.round(Math.random() * this.h)), new Point(0, (int) Math.round(Math.random() * this.h))));
                    break;
                case 1:
                    lightFalls.add(new LightFall(new Point((int) Math.round(Math.random() * this.w), 0), new Point((int) Math.round(Math.random() * this.w), 0)));
                    break;
                case 2:
                    lightFalls.add(new LightFall(new Point(this.w, (int) Math.round(Math.random() * this.h)), new Point(this.w, (int) Math.round(Math.random() * this.h))));
                    break;
                case 3:
                    lightFalls.add(new LightFall(new Point((int) Math.round(Math.random() * this.w), this.h), new Point((int) Math.round(Math.random() * this.w), this.h)));
                    break;
            }
        }
        lightFalls.add(new LightFall(new Point(0, (int) Math.round(Math.random() * this.h)), new Point(0, (int) Math.round(Math.random() * this.h))));
        lightFalls.add(new LightFall(new Point((int) Math.round(Math.random() * this.w), 0), new Point((int) Math.round(Math.random() * this.w), 0)));
        lightFalls.add(new LightFall(new Point(this.w, (int) Math.round(Math.random() * this.h)), new Point(this.w, (int) Math.round(Math.random() * this.h))));
        lightFalls.add(new LightFall(new Point((int) Math.round(Math.random() * this.w), this.h), new Point((int) Math.round(Math.random() * this.w), this.h)));
    }

    @Override
    protected void draw(Canvas canvas) {
        for (LightFall lightFall : lightFalls) {
            lightFall.animate(canvas);
        }
        if (counter >= limit && faseCounter < 3) {
            counter = 0;
            faseCounter++;
        }
    }
}
