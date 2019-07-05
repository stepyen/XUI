package com.stepyen.xuidemo.fragment.utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date：2019/7/3
 * author：stepyen
 * description：
 */
@Page(name = "webview", extra = R.drawable.ic_widget_imageview)
public class WebViewFragment extends BaseFragment {
    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.tv)
    TextView mTv;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_webview;
    }

    @Override
    protected void initViews() {

    }

}
