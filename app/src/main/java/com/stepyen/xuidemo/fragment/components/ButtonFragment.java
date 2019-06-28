package com.stepyen.xuidemo.fragment.components;

import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/6/28
 * author：stepyen
 * description：
 */
@Page(name = "Button", extra = R.drawable.ic_widget_imageview)
public class ButtonFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_button;
    }

    @Override
    protected void initViews() {

    }
}
