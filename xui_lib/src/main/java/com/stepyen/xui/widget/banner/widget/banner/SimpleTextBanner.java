package com.stepyen.xui.widget.banner.widget.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.stepyen.xui.R;
import com.stepyen.xui.widget.banner.widget.banner.base.BaseIndicatorBanner;

/**
 * 简单的文字轮播
 */
public class SimpleTextBanner extends BaseIndicatorBanner<String, SimpleTextBanner> {
    public SimpleTextBanner(Context context) {
        super(context);
    }

    public SimpleTextBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleTextBanner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onTitleSelect(TextView tv, int position) {
    }

    @Override
    public View onCreateItemView(int position) {
        View inflate = View.inflate(mContext, R.layout.xui_adapter_simple_text, null);
        TextView tv = inflate.findViewById(R.id.tv);
        tv.setText(mDatas.get(position));
        return inflate;
    }

    //解决内存泄漏的问题
    @Override
    protected void onDetachedFromWindow() {
        pauseScroll();
        super.onDetachedFromWindow();
    }
}
