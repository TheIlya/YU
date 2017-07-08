package com.firebaseapp.glaring_inferno_6492.ilya.youruniverse;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.LinkedList;

/**
 * Created by ilya on 03.07.17.
 */
public class PoofAnimation extends Animation {
    DrawThread thread;

    public PoofAnimation(int x, int y, int radius, Paint paint, DrawThread thread) {
        super(x, y, paint);
        counterMotion = radius / 100;
        limit = radius;
        this.thread = thread;
    }

    @Override
    protected void draw(Canvas canvas) {
        Paint paint = new Paint(this.paint);
        if (counter < limit) paint.setAlpha(255 - counter * 255 / limit);
        else {
            thread.createUniverse(new Universe(x, y, paint));
            paint.setAlpha(0);
        }
        canvas.drawCircle(x, y, counter, paint);
    }
}
