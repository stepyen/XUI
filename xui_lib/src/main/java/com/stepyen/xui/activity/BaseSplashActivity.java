package com.stepyen.xui.activity;

import android.os.Bundle;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.stepyen.xui.utils.Utils;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 基础启动页
 *
 * 配置  <style name="XUITheme.Launch"> 设置主题
 */
public abstract class BaseSplashActivity extends AppCompatActivity {
    /**
     * 默认启动页过渡时间
     */
    private static final int DEFAULT_SPLASH_DURATION_MILLIS = 2000;

    protected LinearLayout mWelcomeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        onCreateActivity();
    }
    private void initView() {
        mWelcomeLayout = new LinearLayout(this);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mWelcomeLayout.setLayoutParams(params);
        mWelcomeLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(mWelcomeLayout);
    }
    /**
     * activity启动后的初始化
     */
    protected abstract void onCreateActivity();

    /**
     * 启动页结束后的动作
     */
    protected abstract void onSplashFinished();

    /**
     * @return 启动页持续的时间
     */
     protected long getSplashDurationMillis() {
        return DEFAULT_SPLASH_DURATION_MILLIS;
    }

    /**
     * 开启过渡
     *
     * @param enableAlphaAnim 是否启用渐近动画
     */
    protected void startSplash(boolean enableAlphaAnim) {
        if (enableAlphaAnim) {
            startSplashAnim(new AlphaAnimation(0.2F, 1.0F));
        } else {
            startSplashAnim(new AlphaAnimation(1.0F, 1.0F));
        }
    }

    /**
     * 开启引导过渡动画
     *
     * @param anim
     */
    private void startSplashAnim(Animation anim) {
        Utils.checkNull(anim, "Splash Animation can not be null");
        anim.setDuration(getSplashDurationMillis());
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                onSplashFinished();
            }
        });
        mWelcomeLayout.startAnimation(anim);
    }

    @Override
    protected void onDestroy() {
        Utils.recycleBackground(mWelcomeLayout);
        super.onDestroy();
    }
}
