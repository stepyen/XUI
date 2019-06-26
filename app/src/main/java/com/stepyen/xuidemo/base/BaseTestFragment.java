package com.stepyen.xuidemo.base;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stepyen.xui.utils.DensityUtils;
import com.stepyen.xuidemo.R;

import butterknife.ButterKnife;

/**
 * date：2019/6/26
 * author：stepyen
 * description：
 */
public  abstract class BaseTestFragment extends BaseFragment {
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
        addView(title, v->{
            Intent intent = new Intent(getContext(), cls);
            startActivity(intent);
        }, 0);
    }


    public void addView(String title, final Class<?> cls, int marginTop) {
        addView(title, v->{
            Intent intent = new Intent(getContext(), cls);
            startActivity(intent);
        }, marginTop);
    }

    public void addView(String title, View.OnClickListener clickListener) {
        addView(title,clickListener,0 );
    }

    public void addView(String title, View.OnClickListener clickListener, int marginTop) {
        Button button = new Button(getContext());
        button.setText(title);
        button.setOnClickListener(clickListener);
        mParentLl.addView(button);
        setLpMarginTop(button, marginTop);
    }

    public TextView addTextView(CharSequence txt, int marginTop) {
        TextView tv = new TextView(getContext());
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
            lp.setMargins(0, DensityUtils.dp2px(mActivity, marginTop), 0, 0);
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
        Toast.makeText(mActivity, str, Toast.LENGTH_SHORT).show();
    }

}
