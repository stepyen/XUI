package com.stepyen.xuidemo.fragment.components.textview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.stepyen.xui.widget.textview.supertextview.SuperTextView;
import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseFragment;
import com.stepyen.xutil.tip.ToastUtils;
import com.xuexiang.xpage.annotation.Page;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date：2019/6/28
 * author：stepyen
 * description：
 */
@Page(name = "SuperTextView")
public class SuperTextViewFragment extends BaseFragment {
    @BindView(R.id.ll_super_text_parent)
    LinearLayout mLlSuperTextParent;
    @BindView(R.id.stv_test_click)
    SuperTextView mStvTestClick;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_super_text_view;
    }

    @Override
    protected void initViews() {
        mStvTestClick.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClickListener(SuperTextView superTextView) {
                ToastUtils.toast("整个View");
            }
        }).setLeftTvClickListener(new SuperTextView.OnLeftTvClickListener() {
            @Override
            public void onClickListener() {
                ToastUtils.toast("左边文字");
            }
        });
    }

}
