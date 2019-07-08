package com.stepyen.xuidemo.fragment.components;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stepyen.xui.widget.tabbar.TabSegment;
import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;
import butterknife.BindView;

/**
 * date：2019/7/8
 * author：stepyen
 * description：
 */

@Page(name = "指示器", extra = R.drawable.ic_widget_imageview)
public class IndicaterFragment extends BaseFragment {
    @BindView(R.id.contentViewPager)
    ViewPager mContentViewPager;
    @BindView(R.id.tabSegment)
    TabSegment mTabSegment;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_indicater;
    }

    @Override
    protected void initViews() {

    }

}