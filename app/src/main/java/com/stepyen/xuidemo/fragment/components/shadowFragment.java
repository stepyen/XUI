package com.stepyen.xuidemo.fragment.components;

import com.stepyen.xui.utils.ResUtils;
import com.stepyen.xui.widget.spinner.MaterialSpinner;
import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseFragment;
import com.stepyen.xutil.tip.ToastUtils;
import com.xuexiang.xpage.annotation.Page;

import butterknife.BindView;

/**
 * date：2019/7/12
 * author：stepyen
 * description：
 */
@Page(name = "阴影", extra = R.drawable.ic_widget_imageview)
public class shadowFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shadow;
    }

    @Override
    protected void initViews() {

    }

}
