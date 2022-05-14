package com.example.coronareport.helperClasses;

import android.animation.AnimatorSet;
import android.content.Context;
import android.view.animation.AccelerateInterpolator;

public class Render {
    Context context;
    Long du = 1000L;
    AnimatorSet animatorSet;

    public Render(Context context) {
        this.context = context;
    }

    public void setAnimation(AnimatorSet animatorSet) {
        this.animatorSet = animatorSet;
    }

    public void setDuration(Long duration) {
        this.du = duration;
    }

    public void start() {
        animatorSet.setDuration(du);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.start();
    }

}
