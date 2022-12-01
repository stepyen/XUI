package com.stepyen.xuidemo.base;

import android.content.Intent;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stepyen.xui.utils.DensityUtils;
import com.stepyen.xui.utils.ResUtils;
import com.stepyen.xuidemo.R;

import androidx.core.content.ContextCompat;
import butterknife.ButterKnife;

/**
 * date：2019/6/26
 * author：stepyen
 * description：
 */
public abstract class BaseTestFragment extends BaseFragment {
    private LinearLayout mParentLl;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_test;
    }

    @Override
    protected void initViews() {
        mParentLl = findViewById(R.id.ll_parent);
        //在这里添加View
        addView();
//        ButterKnife.bind(this);
    }

    public void addView() {

    }


    public void addView(String title, final Class<?> cls) {
        addView(title, v -> {
            Intent intent = new Intent(getContext(), cls);
            startActivity(intent);
        }, 0);
    }


    public void addView(String title, final Class<?> cls, int marginTop) {
        addView(title, v -> {
            Intent intent = new Intent(getContext(), cls);
            startActivity(intent);
        }, marginTop);
    }

    public TextView addView(String title, View.OnClickListener clickListener) {
        return addView(title, clickListener, 0);
    }

    public TextView addView(String title, View.OnClickListener clickListener, int marginTop) {
        TextView tv = new TextView(getContext());
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setPadding(DensityUtils.dp2px(15), 0, 0, 0);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtils.dp2px(47));
        tv.setLayoutParams(lp);
        tv.setBackgroundColor(ResUtils.getColor(R.color.white));
        tv.setText(title);
        tv.setOnClickListener(clickListener);
        mParentLl.addView(tv);
        setLpMarginTop(tv, marginTop);

        // 加条线
        View lineView = new View(getContext());
        LinearLayout.LayoutParams lineLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtils.dp2px(ResUtils.getDimens(com.stepyen.xui.R.dimen.xui_config_size_line)));
        lineView.setLayoutParams(lineLp);
        lineView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.xui_config_color_line));
        mParentLl.addView(lineView);

        return tv;

    }

    public TextView addTextView(CharSequence txt, int marginTop) {
        return addTextView(txt,0,marginTop);
    }

    public TextView addTextView(CharSequence txt, int defStyleAttr, int marginTop) {
        TextView tv;
        if (defStyleAttr != 0) {
            tv = new TextView(getContext(), null, defStyleAttr);
        } else {
            tv = new TextView(getContext());
        }
        tv.setText(txt);
        mParentLl.addView(tv);
        setLpMarginTop(tv, marginTop);

        return tv;
    }

    private void setLpMarginTop(View view, int marginTop) {
        if (view == null) {
            return;
        }
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (lp != null) {
            lp.setMargins(DensityUtils.dp2px(getContext(), 15), DensityUtils.dp2px(getContext(), marginTop), lp.rightMargin, DensityUtils.dp2px(getContext(), 10));
            view.setLayoutParams(lp);
        }
    }

    public void addTextView(CharSequence txt) {
        addTextView(txt, 0);
    }


    public View addView(int layoutId) {
        View view = LayoutInflater.from(getContext()).inflate(layoutId, null);
        addView(view);

        return view;
    }

    public View addView(View view) {
        mParentLl.addView(view);
        return view;
    }

    public void onViewClick(View view) {

    }

    public void showToast(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }

}
