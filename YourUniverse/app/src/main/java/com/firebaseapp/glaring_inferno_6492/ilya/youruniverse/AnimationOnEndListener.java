package com.firebaseapp.glaring_inferno_6492.ilya.youruniverse;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;

/**
 * Created by ilya on 30.06.17.
 */
public class AnimationOnEndListener implements Animation.AnimationListener {
    private View view;
    private Intent intent;
    private Activity activity;
    private boolean moveForward;

    public AnimationOnEndListener(View view, Intent intent, Activity activity, boolean moveForward) {
        this.view = view;
        this.intent = intent;
        this.activity = activity;
        this.moveForward = moveForward;
    }

    @Override
    public void onAnimationStart(Animation animation) {
        view.setEnabled(false);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        view.setEnabled(true);
        activity.startActivity(intent);
        if (moveForward) activity.overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        else activity.overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
