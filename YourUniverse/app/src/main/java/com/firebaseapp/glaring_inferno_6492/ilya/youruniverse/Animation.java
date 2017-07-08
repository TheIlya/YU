package com.firebaseapp.glaring_inferno_6492.ilya.youruniverse;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by ilya on 03.07.17.
 */
public abstract class Animation {
    protected int counter, limit, counterMotion, x, y;
    protected Paint paint;

    public Animation(int x, int y, Paint paint) {
        counter = 0;
        this.x = x;
        this.y = y;
        this.paint = paint;
    }

    protected abstract void draw (Canvas canvas);

    protected boolean increaseCounter() {
        if (counter < limit) {
            counter += counterMotion;
            return true;
        } else {
            return false;
        }
    }

    public boolean animate (Canvas canvas) {
        draw(canvas);
        return increaseCounter();
    }
}
