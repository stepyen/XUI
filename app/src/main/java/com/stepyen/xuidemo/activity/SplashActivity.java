package com.stepyen.xuidemo.activity;

import android.content.Intent;

import com.stepyen.xui.activity.BaseSplashActivity;

/**
 * date：2019/6/27
 * author：stepyen
 * description：
 */
public class SplashActivity extends BaseSplashActivity {

    @Override
    protected void onCreateActivity() {
        startSplash(false);
    }

    @Override
    protected void onSplashFinished() {
        startActivity(new Intent(this, MainActivity.class));
//        startActivity(new Intent(this, TestActivity.class));
        finish();
    }
}
