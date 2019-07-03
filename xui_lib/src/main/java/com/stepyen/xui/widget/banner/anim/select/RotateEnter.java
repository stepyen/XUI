package com.stepyen.xui.widget.banner.anim.select;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;
import com.stepyen.xui.widget.banner.anim.BaseAnimator;


public class RotateEnter extends BaseAnimator {
    public RotateEnter() {
        this.mDuration = 200;
    }

    @Override
    public void setAnimation(View view) {
        this.mAnimatorSet.playTogether(ObjectAnimator.ofFloat(view, "rotation", 0, 180));
    }
}
