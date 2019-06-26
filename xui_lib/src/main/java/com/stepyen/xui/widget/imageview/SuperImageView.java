package com.stepyen.xui.widget.imageview;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * date：2019/6/26
 * author：stepyen
 * description：封装 ImageView
 * 功能
 * 1、圆角、圆形、椭圆
 * 2、边框
 * 3、按下显示遮罩view
 */
public class SuperImageView extends AppCompatImageView {

    public SuperImageView(Context context) {
        this(context, null);
    }

    public SuperImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
