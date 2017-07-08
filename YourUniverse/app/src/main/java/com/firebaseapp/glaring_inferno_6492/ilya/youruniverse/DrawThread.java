package com.firebaseapp.glaring_inferno_6492.ilya.youruniverse;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Created by ilya on 01.07.17.
 */
public class DrawThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private boolean isRunning, active;
    private LinkedList<Universe> universes = new LinkedList<>();
    private UniverseView universeView;
    private LinkedList<Animation> animations;

    public DrawThread(SurfaceHolder surfaceHolder, UniverseView universeView){
        this.surfaceHolder = surfaceHolder;
        this.universeView = universeView;
        animations = new LinkedList<>();
        universes = new LinkedList<>();
        active = true;
    }

    public void increaseUniverseEnergy () {
        if (active) {
            Paint paint = new Paint();
            paint.setColor(universeView.getResources().getColor(R.color.colorBack));
            if (universes.size() > 0 && universes.get(0) != null) {
                universes.get(0).increaseEnergy();
            } else {
                animations.add(new ShineInAnimation(universeView.getWidth() / 2, universeView.getHeight() / 2, paint, universeView.getWidth(), universeView.getHeight(), animations, this));
                active = false;
            }
        }
    }

    public void createUniverse (Universe u) {
        universes.add(u);
        active = true;
    }

    public void setRunning (boolean running) {
        isRunning = running;
    }

    @Override
    public void run() {
        Canvas canvas;
        while (isRunning) {
            canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas(null);
                canvas.drawColor(universeView.getResources().getColor(R.color.colorMain));
                for (Universe u : universes) {
                    u.animate(canvas);
                }
                Animation animation;
                try {
                    animation = animations.getFirst();
                } catch (NoSuchElementException nsee) {
                    animation = null;
                }
                while (true) {
                    if (animation == null) break;
                    if (!animation.animate(canvas)) animations.remove(animation);
                    try {
                        animation = animations.get(animations.indexOf(animation) + 1);
                    } catch (IndexOutOfBoundsException ioobe) {
                        animation = null;
                    }
                }
            } catch (NullPointerException n) {
            }
            finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
