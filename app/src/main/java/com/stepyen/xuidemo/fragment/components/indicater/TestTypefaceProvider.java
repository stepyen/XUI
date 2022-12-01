package com.stepyen.xuidemo.fragment.components.indicater;

import android.graphics.Typeface;


import com.stepyen.xui.widget.tabbar.TabSegment;

import androidx.annotation.Nullable;

/**
 * date：2019/7/8
 * author：stepyen
 * description：
 */
public class TestTypefaceProvider implements TabSegment.TypefaceProvider {
    @Override
    public boolean isNormalTabBold() {
        return false;
    }

    @Override
    public boolean isSelectedTabBold() {
        return true;
    }

    @Nullable
    @Override
    public Typeface getTypeface() {
        return Typeface.DEFAULT;
    }
}
