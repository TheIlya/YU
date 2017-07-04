package com.firebaseapp.glaring_inferno_6492.ilya.youruniverse;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by ilya on 30.06.17.
 */
public class ButtonOnClickListener implements View.OnClickListener {
    private Intent intent;
    private Activity activity;
    private boolean moveForward;

    public ButtonOnClickListener(Intent intent, Activity activity, boolean moveForward) {
        this.intent = intent;
        this.activity = activity;
        this.moveForward = moveForward;
    }

    @Override
    public void onClick(View v) {
        Animation touchAnimation = AnimationUtils.loadAnimation(activity, R.anim.button_touch_animation);
        v.startAnimation(touchAnimation);
        touchAnimation.setAnimationListener(new AnimationOnEndListener(v, intent, activity, moveForward));
    }
}