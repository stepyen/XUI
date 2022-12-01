package com.stepyen.xui.widget.banner.transform;
import android.view.View;
import com.nineoldandroids.view.ViewHelper;

import androidx.viewpager.widget.ViewPager;

/**
 * 翻转切换
 */
public class FlowTransformer implements ViewPager.PageTransformer {

    @Override
    public void transformPage(View page, float position) {
        ViewHelper.setRotationY(page, position * -30f);
    }
}
