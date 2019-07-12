package com.stepyen.xui.widget.textview;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
/**
 * 自定义跑马灯Textview类

 	可选
 *  android:marqueeRepeatLimit="marquee_forever"	重复模式,marquee_forever为设置为一直跑动
 *
 */
public class AutoMoveTextView extends AppCompatTextView {
	public AutoMoveTextView(Context context) {
		this(context, null);
	}

	public AutoMoveTextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public AutoMoveTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setEllipsize(TextUtils.TruncateAt.MARQUEE);
		setSingleLine();
	}

	@Override
	public boolean isFocused() {
		return true;
	}

}
