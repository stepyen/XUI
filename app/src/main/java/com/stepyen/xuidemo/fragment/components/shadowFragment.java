package com.stepyen.xuidemo.fragment.components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stepyen.xui.widget.layout.XUILinearLayout;
import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date：2019/7/12
 * author：stepyen
 * description：
 */
@Page(name = "阴影", extra = R.drawable.ic_widget_imageview)
public class shadowFragment extends BaseFragment {


    @BindView(R.id.XUILinearLayout)
    XUILinearLayout mXUILinearLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shadow;
    }

    @Override
    protected void initViews() {

    }

}
