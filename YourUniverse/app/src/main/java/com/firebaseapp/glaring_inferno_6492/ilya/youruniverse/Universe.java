package com.firebaseapp.glaring_inferno_6492.ilya.youruniverse;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by ilya on 01.07.17.
 */
public abstract class Universe {
    protected final double motionQantum = 0.01;
    protected double radius, radiusMotion;
    protected int x, y, energy;
    protected Universe[] opponents, confederates;
    protected Paint paint;
    protected UniverseView universeView;

    public Universe(int x, int y, UniverseView universeView) {
        this.x = x;
        this.y = y;
        paint = new Paint();
        paint.setColor(Color.WHITE);
        radius = 0;
        radiusMotion = 10 * motionQantum;
        energy = 0;
        opponents = new Universe[0];
        confederates = new Universe[0];
        this.universeView = universeView;
    }

    public Universe(int x, int y, int energy, Paint paint, UniverseView universeView) {
        this.x = x;
        this.y = y;
        this.energy = energy;
        this.paint = new Paint(paint);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.universeView = universeView;
    }

    protected void draw (Canvas canvas) {
        canvas.drawCircle(x, y, Math.round(radius), paint);
    }

    public void animate (Canvas canvas) {
        draw(canvas);
        radius += radiusMotion;
        radiusMotion -= motionQantum;
        if (radius <= energy) radiusMotion = 70 * motionQantum;
    }

    public void increaseEnergy() {
        energy += 5;
    }
}
