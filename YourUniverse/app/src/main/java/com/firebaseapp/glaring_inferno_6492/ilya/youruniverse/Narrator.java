package com.firebaseapp.glaring_inferno_6492.ilya.youruniverse;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by ilya on 11.07.17.
 */
public class Narrator {
    private String story[];
    private float time;
    private final float timeMax = 300;

    public Narrator (String[] story) {
        this.story = story;
        time = timeMax * 3;
    }

    public boolean animate (Canvas canvas, int progress, int x, int y, Paint paint) {
        Paint localPaint = new Paint(paint);
        if (time < timeMax) localPaint.setAlpha(Math.round(255 / timeMax * time));
        else {
            if (time > timeMax * 2) localPaint.setAlpha(Math.round(255 / timeMax * (timeMax * 3 - time)));
            else localPaint.setAlpha(255);
        }
        canvas.drawText(story[progress] + " " + time, x, y, localPaint);
        if (time > 0) {
            time--;
            return true;
        } else return false;
    }
}