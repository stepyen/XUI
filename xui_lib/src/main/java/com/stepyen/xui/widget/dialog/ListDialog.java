package com.stepyen.xui.widget.dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;

import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stepyen.xui.R;
import com.stepyen.xui.utils.DensityUtils;
import com.stepyen.xui.utils.ResUtils;

import androidx.annotation.ColorInt;

/**
 * date：2019/7/29
 * author：stepyen
 * description：列表对话框
 * 上面是列表，下面有个取消选项
 *
 */
public class ListDialog extends BaseDialog<ListDialog> {
    private final LinearLayout mLlParent;
    private final TextView mtvCancle;

    /**
     * item的高度
     */
    private int mItemHeight = ResUtils.getDimensionPixelSize(R.dimen.xui_config_size_item);
    /**
     * item项的字体大小
     */
    private int mItemTextSize = ResUtils.getDimensionPixelSize(R.dimen.xui_config_size_title);
    /**
     * item项的字体颜色
     */
    private int mItemTextColor = ResUtils.getColor(R.color.black);

    public ListDialog(Context context) {
        super(context, R.layout.dialog_list);
        mLlParent = findViewById(R.id.ll_parent);
        mtvCancle = findViewById(R.id.tv_cancel);
    }

    public ListDialog setItemParentViewBg(Drawable drawable) {
        mLlParent.setBackgroundDrawable(drawable);
        return this;
    }
    public ListDialog setItemTextSize(int textSize) {
        mItemTextSize = textSize;  // px
        return this;
    }

    public ListDialog setItemTextColor(@ColorInt int color) {
        mItemTextColor = color;
        return this;
    }

    public ListDialog setCancleVisibility(boolean isVisibility) {
        mtvCancle.setVisibility(isVisibility ? View.VISIBLE : View.GONE);
        mtvCancle.setOnClickListener(v -> {
            dismiss();
        });
        return this;
    }

    public ListDialog setCancleBg(Drawable drawable) {
        mtvCancle.setBackgroundDrawable(drawable);
        return this;
    }

    public ListDialog setCancleText(CharSequence charSequence) {
        mtvCancle.setText(charSequence);
        return this;
    }

    public ListDialog setCancleTextColor(int color) {
        mtvCancle.setTextColor(color);
        return this;
    }

    public ListDialog setCancleTextSize(int textSize) {
        mtvCancle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        return this;
    }


    public ListDialog addView(View view, View.OnClickListener clickListener, boolean addLine) {

        if (view != null) {
            if (clickListener != null) {
                view.setOnClickListener(clickListener);
            }
            mLlParent.addView(view);
            if (addLine) {
                View line = new View(mContext);
                line.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ResUtils.getDimensionPixelSize(R.dimen.xui_config_size_line)));
                line.setBackgroundColor(ResUtils.getColor(R.color.xui_config_color_line));
                mLlParent.addView(line);
            }
        }
        return this;
    }

    public ListDialog addTextView(CharSequence charSequence, View.OnClickListener clickListener, boolean addLine) {
        if (!TextUtils.isEmpty(charSequence)) {
            TextView tv = new TextView(mContext);
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(mItemTextColor);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mItemTextSize);
            tv.setText(charSequence);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, mItemHeight);
            tv.setLayoutParams(lp);
            addView(tv, clickListener, addLine);
        }
        return this;
    }

    public ListDialog addTextView(CharSequence charSequence, View.OnClickListener clickListener) {
        addTextView(charSequence, clickListener, true);
        return this;
    }


}
