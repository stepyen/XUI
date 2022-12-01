
package com.stepyen.xui.widget.dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;

import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.stepyen.xui.R;
import com.stepyen.xui.utils.KeyboardUtils;
import com.stepyen.xui.utils.ResUtils;

import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatDialog;

/**
 * 基类Dialog
 * 触摸Dialog屏幕以外的区域，dialog消失同时隐藏键盘
 */
public class BaseDialog<T extends BaseDialog> extends AppCompatDialog {
    private View mContentView;
    protected Context mContext;

    public BaseDialog(Context context, int layoutId) {
        this(context, R.style.XUIDialog_Custom, layoutId);
    }

    public BaseDialog(Context context, View contentView) {
        this(context, R.style.XUIDialog_Custom, contentView);
    }

    public BaseDialog(Context context) {
        super(context, R.style.XUIDialog_Custom);
    }

    public BaseDialog(Context context, int theme, int layoutId) {
        super(context, theme);
        mContext = context;
        init(layoutId);
    }

    public BaseDialog(Context context, int theme, View contentView) {
        super(context, theme);
        mContext = context;
        init(contentView);
    }

    public void init(int layoutId) {
        View view = getLayoutInflater().inflate(layoutId, null);
        init(view);
    }

    private void init(View view) {
        setContentView(view);
        mContentView = view;
        setCanceledOnTouchOutside(true);
    }


    /**
     * 设置弹窗的宽和高
     *
     * @param width
     * @param height
     */
    public T setWindowSize(int width, int height) {
        // 获取对话框当前的参数值
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = width;
        p.height = height;
        getWindow().setAttributes(p);
        return (T) this;
    }

    public T setGravity(int gravity) {
        getWindow().setGravity(gravity);
        return (T) this;
    }

    /**
     * 底部对话框可以使用 R.style.Animation_Bottom_Rising
     *
     * @param resId
     * @return
     */
    public T setWindowAnimations(@StyleRes int resId) {
        getWindow().setWindowAnimations(resId);
        return (T) this;
    }

    /**
     * 设置背景模糊程度
     * @param amount 0-1,越到1越模糊
     * @return
     */
    public T setDimAmount(float amount) {
        getWindow().setDimAmount(amount);
        return (T) this;
    }

    @Override
    public <V extends View> V findViewById(int resId) {
        return mContentView.findViewById(resId);
    }

    public String getString(int resId) {
        return getContext().getResources().getString(resId);
    }

    public Drawable getDrawable(int resId) {
        return ResUtils.getDrawable(getContext(), resId);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        KeyboardUtils.dispatchTouchEvent(ev, this);
        return super.onTouchEvent(ev);
    }

}
