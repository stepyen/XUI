package com.stepyen.xuidemo.fragment.components.imageview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.stepyen.xui.TestActivity;
import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseFragment;
import com.stepyen.xutil.tip.ToastUtils;
import com.xuexiang.xpage.annotation.Page;

import java.security.Permission;
import java.security.Permissions;

/**
 * date：2019/6/26
 * author：stepyen
 * description：
 */
@Page(name = "圆角图片")
public class RadiusImageViewFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_radius_image_view;
    }

    @Override
    protected void initViews() {

    }

}
