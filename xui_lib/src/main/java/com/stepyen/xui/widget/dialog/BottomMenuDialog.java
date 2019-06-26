package com.stepyen.xui.widget.dialog;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stepyen.xui.R;
import com.stepyen.xui.utils.DensityUtils;
import com.stepyen.xui.utils.ResUtils;
import com.stepyen.xui.utils.shape.ShapeBuilder;
import com.stepyen.xui.utils.shape.ShapeListBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * date：2019/6/26
 * author：stepyen
 * description：底部弹窗
 */
public class BottomMenuDialog extends Dialog {
    private final int STATE_TOP = 1;
    private final int STATE_MIDDLE = 2;
    private final int STATE_BOTTOM = 3;
    private final int STATE_SINGLE = 4;

    private Context mContext;
    /**
     * 圆角
     */
    private int mRadius = 8;
    /**
     * item项 文本大小
     */
    private int mItemTextSize = 16;
    /**
     * item项 文本颜色
     */
    private int mItemTextColor = ResUtils.getColor(R.color.black);

    /**
     * 取消项 文本大小
     */
    private int mCancelTextSize =  16;
    /**
     * 取消项 文本颜色
     */
    private int mCancelTextColor = ResUtils.getColor(R.color.green);

    /**
     * 取消项 文本
     */
    private String mCancelText = "取消";

    private List<MenuBean> mMenuBeanList = new ArrayList<>();
    private LinearLayout mLlMenu;
    private TextView mTvCancel;

    public BottomMenuDialog(Context context) {
        this(context, R.style.Dialog_NoTitle_NoShadow);
    }

    public BottomMenuDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    protected BottomMenuDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Window window = getWindow();
        window.setWindowAnimations(R.style.Animation_Bottom_Rising);
        window.setGravity(Gravity.BOTTOM);
        window.getDecorView().setPadding(0, 0, 0, 0);

        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(attributes);
    }

    private void init(Context context) {
        mContext = context;
    }


    public BottomMenuDialog radius(int radius) {
        this.mRadius = radius;
        return this;
    }

    public BottomMenuDialog canceledOnTouchOutside(boolean cancel) {
        setCanceledOnTouchOutside(cancel);
        return this;
    }

    public BottomMenuDialog itemTextSize(int itemTextSize) {
        this.mItemTextSize = itemTextSize;
        return this;
    }

    public BottomMenuDialog itemTextColor(int itemTextColor) {
        this.mItemTextColor = itemTextColor;
        return this;
    }

    public BottomMenuDialog addMenu(String title, OnMenuClickListener onClickListener) {
        mMenuBeanList.add(new MenuBean(title, onClickListener));
        return this;
    }

    @Override
    public void show() {
        super.show();
    }


    /**
     * 构建
     *
     * @return
     */
    public BottomMenuDialog builder() {

        View rootView = LayoutInflater.from(mContext).inflate(R.layout.dialg_bottom_menu, null);
        mLlMenu = rootView.findViewById(R.id.ll_menu);
        mTvCancel = rootView.findViewById(R.id.tv_cancel);
        //设置menu
        int size = mMenuBeanList.size();
        for (int i = 0; i < size; i++) {
            final MenuBean bean = mMenuBeanList.get(i);

            int state = getState(size, i);
            TextView tv = getItemView(state, bean);
            mLlMenu.addView(tv);

            //不是最后一个就加条线
            if (i != size - 1) {
                View lineView = new View(mContext);
                LinearLayout.LayoutParams lineLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtils.dp2px(ResUtils.getDimens(R.dimen.xui_config_line)));
                lineView.setLayoutParams(lineLp);
                lineView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.xui_config_line));
                mLlMenu.addView(lineView);
            }
        }

        //设置取消
        mTvCancel.setTextSize(mCancelTextSize);
        mTvCancel.setTextColor(mCancelTextColor);
        mTvCancel.setText(mCancelText);
        mTvCancel.setBackground(getBackDrawable(mContext, STATE_SINGLE, mRadius));
        mTvCancel.setOnClickListener(v -> {
            dismiss();
        });
        setContentView(rootView);
        return this;
    }


    private TextView getItemView(int state, final MenuBean bean) {
        TextView tv = new TextView(mContext);
        LinearLayout.LayoutParams tvLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtils.dp2px(44));
        tv.setLayoutParams(tvLp);
        tv.setText(bean.mTitle);
        tv.setTextSize(mItemTextSize);
        tv.setTextColor(mItemTextColor);
        tv.setGravity(Gravity.CENTER);
        tv.setOnClickListener(v -> {
            if (bean.mOnClickListener != null)
                bean.mOnClickListener.onClick(this);
        });

        tv.setBackground(getBackDrawable(mContext, state, mRadius));
        return tv;
    }

    private int getState(int size, int i) {
        if (i == 0) {
            return size == 1 ? STATE_SINGLE : STATE_TOP;
        }

        if (i == size - 1) {
            return STATE_BOTTOM;
        }

        return STATE_MIDDLE;
    }


    private Drawable getBackDrawable(Context context, int what, int r) {
        ShapeBuilder defaultShape = ShapeBuilder.create(context)
                .solid(R.color.white);

        ShapeBuilder pressShape = ShapeBuilder.create(context)
                .solid(R.color.xui_config_color_pressed);

        switch (what) {
            case STATE_TOP:
                defaultShape.radius(r, r, 0, 0);
                pressShape.radius(r, r, 0, 0);
                break;
            case STATE_MIDDLE:
                defaultShape.radius(0);
                pressShape.radius(0);
                break;
            case STATE_BOTTOM:
                defaultShape.radius(0, 0, r, r);
                pressShape.radius(0, 0, r, r);
                break;
            case STATE_SINGLE:
                defaultShape.radius(r);
                pressShape.radius(r);
                break;
        }

        Drawable defaultDrawable = defaultShape.build();
        Drawable pressDrawable = pressShape.build();

        return ShapeListBuilder.create(defaultDrawable)
                .addShape(pressDrawable, android.R.attr.state_pressed)
                .build();

    }


    private static class MenuBean {
        private String mTitle;
        private OnMenuClickListener mOnClickListener;

        public MenuBean(String title, OnMenuClickListener onClickListener) {
            mTitle = title;
            mOnClickListener = onClickListener;
        }
    }

    public interface OnMenuClickListener {
        void onClick(BottomMenuDialog dialog);
    }
}
