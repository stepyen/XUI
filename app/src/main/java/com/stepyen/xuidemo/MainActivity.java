package com.stepyen.xuidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.stepyen.xuidemo.activity.LayoutActivity;
import com.stepyen.xuidemo.base.BaseTestActivity;

/**
 *
 *
 *
 * 慢慢来，整理自己需要的
 */
public class MainActivity extends BaseTestActivity {

    @Override
    public void addView() {
//        addView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        addView("布局",v->{
            start(LayoutActivity.class);
        });
    }
}
