package com.stepyen.xui.widget.banner.anim.select;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;
import com.stepyen.xui.widget.banner.anim.BaseAnimator;

public class ZoomInEnter extends BaseAnimator {

    public ZoomInEnter() {
        this.mDuration = 200;
    }

    @Override
    public void setAnimation(View view) {
        this.mAnimatorSet.playTogether(ObjectAnimator.ofFloat(view, "scaleX", 1.0F, 1.5F),
                ObjectAnimator.ofFloat(view, "scaleY", 1.0F, 1.5F));
    }
}
