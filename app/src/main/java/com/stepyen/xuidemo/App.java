package com.stepyen.xuidemo;

import android.app.Application;

import com.stepyen.xutil.XUtil;

/**
 * date：2019/6/24
 * author：stepyen
 * description：
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        XUtil.init(this);
    }
}
