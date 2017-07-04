package com.firebaseapp.glaring_inferno_6492.ilya.youruniverse;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by ilya on 01.07.17.
 */
public class DrawThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private boolean isRunning;
    private YourUniverse yourUniverse;
    private UniverseView universeView;
    private LinkedList<Animation> animations;

    public DrawThread(SurfaceHolder surfaceHolder, UniverseView universeView){
        this.surfaceHolder = surfaceHolder;
        this.universeView = universeView;
        animations = new LinkedList<>();
    }

    public void increaseUniverseEnergy () {
        Paint paint = new Paint();
        paint.setColor(universeView.getResources().getColor(R.color.colorBack));
        if (yourUniverse != null) yourUniverse.increaseEnergy();
        else {
            Animation animation = new PoofAnimation(universeView.getWidth() / 2, universeView.getHeight() / 2, (universeView.getWidth() + universeView.getHeight()) /2, paint);
            animation.setOnEndListener(new OnEndListener() {
                @Override
                public void onEnd() {
                    Paint paint = new Paint();
                    paint.setColor(universeView.getResources().getColor(R.color.colorBack));
                    Animation shineInAnimation = new ShineInAnimation(universeView.getWidth() / 2, universeView.getHeight() / 2, paint, universeView.getWidth(), universeView.getHeight());
                    shineInAnimation.setOnEndListener(new OnEndListener() {
                        @Override
                        public void onEnd() {
                            yourUniverse = new YourUniverse(universeView.getWidth() / 2, universeView.getHeight() / 2, universeView);
                        }
                    });
                    animations.add(shineInAnimation);
                }
            });
            animations.add(animation);
        }
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
                yourUniverse.animate(canvas);

                for (Iterator<Animation> iterator = animations.iterator(); iterator.hasNext(); ) {
                    Animation animation = iterator.next();
                    if (!animation.animate(canvas)) iterator.remove();
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
