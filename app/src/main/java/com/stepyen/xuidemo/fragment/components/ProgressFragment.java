package com.stepyen.xuidemo.fragment.components;

import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/7/4
 * author：stepyen
 * description：
 */
@Page(name = "进度条", extra = R.drawable.ic_widget_imageview)
public class ProgressFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_progress;
    }

    @Override
    protected void initViews() {

    }
}
