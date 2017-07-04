package com.firebaseapp.glaring_inferno_6492.ilya.youruniverse;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by ilya on 01.07.17.
 */
public class UniverseView extends SurfaceView implements SurfaceHolder.Callback, View.OnClickListener {
    private DrawThread drawThread;

    public UniverseView(Context context) {
        super(context);
        getHolder().addCallback(this);
        this.setOnClickListener(this);
    }

    public UniverseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        this.setOnClickListener(this);
    }

    public UniverseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getHolder().addCallback(this);
        this.setOnClickListener(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread(getHolder(), this);
        drawThread.setRunning(true);
        drawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        drawThread.setRunning(false);
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public void onClick(View v) {
        drawThread.increaseUniverseEnergy();
    }
}
