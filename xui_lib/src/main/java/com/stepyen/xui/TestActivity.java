package com.stepyen.xui;

import android.os.Bundle;

import android.widget.LinearLayout;


import com.stepyen.xui.utils.ResUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * date：2019/6/28
 * author：stepyen
 * description：
 */
public class TestActivity extends AppCompatActivity {
    protected LinearLayout mWelcomeLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        mWelcomeLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mWelcomeLayout.setLayoutParams(params);
        mWelcomeLayout.setOrientation(LinearLayout.VERTICAL);
        mWelcomeLayout.setBackgroundColor(ResUtils.getColor(R.color.green));
        setContentView(mWelcomeLayout);
    }
}
