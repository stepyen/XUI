package com.stepyen.xui.widget.banner.transform;

import android.view.View;
import com.nineoldandroids.view.ViewHelper;

import androidx.viewpager.widget.ViewPager;

/**
 * 侧滑逐渐消失切换
 */
public class FadeSlideTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {

        ViewHelper.setTranslationX(page, 0);

        if (position <= -1.0F || position >= 1.0F) {
            ViewHelper.setAlpha(page, 0.0F);
        } else if (position == 0.0F) {
            ViewHelper.setAlpha(page, 1.0F);
        } else {
            // position is between -1.0F & 0.0F OR 0.0F & 1.0F
            ViewHelper.setAlpha(page, 1.0F - Math.abs(position));
        }
    }
}
