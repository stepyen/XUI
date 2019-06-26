package com.stepyen.xuidemo.fragment.components;

import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/6/26
 * author：stepyen
 * description：
 */
@Page(name = "自定义键盘", extra = R.drawable.ic_widget_imageview)
public class KeyBoardViewFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_key_board_view;
    }

    @Override
    protected void initViews() {

    }
}
