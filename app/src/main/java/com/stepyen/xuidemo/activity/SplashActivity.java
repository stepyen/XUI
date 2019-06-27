package com.stepyen.xuidemo.activity;

import android.content.Intent;

import com.stepyen.xui.activity.BaseSplashActivity;
import com.stepyen.xuidemo.R;

import java.util.Spliterator;

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
        finish();
    }
}
