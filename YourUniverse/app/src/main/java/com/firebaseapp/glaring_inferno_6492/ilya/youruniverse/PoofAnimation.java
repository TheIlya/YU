package com.firebaseapp.glaring_inferno_6492.ilya.youruniverse;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by ilya on 03.07.17.
 */
public class PoofAnimation extends Animation {
    public PoofAnimation(int x, int y, int radius, Paint paint) {
        super(x, y, paint);
        counterMotion = radius / 40;
        limit = radius;
    }

    @Override
    protected void draw(Canvas canvas) {
        Paint paint = new Paint(this.paint);
        if (counter < limit) paint.setAlpha(255 - counter * 255 / limit);
        else paint.setAlpha(0);
        canvas.drawCircle(x, y, counter, paint);
    }
}
