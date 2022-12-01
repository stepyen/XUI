package com.stepyen.xui.widget.textview;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import com.stepyen.xui.R;
import com.stepyen.xui.utils.ResUtils;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * date：2019-07-27
 * author：stepyen
 * description：点击Span所需配置
 */

public class ClickSpanTextView extends AppCompatTextView {

    public ClickSpanTextView(Context context) {
        this(context, null);
    }

    public ClickSpanTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClickSpanTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //去掉点击后的背景颜色为透明
        setHighlightColor(ResUtils.getColor(R.color.transparent));
        setMovementMethod(LinkMovementMethod.getInstance());
    }
}
